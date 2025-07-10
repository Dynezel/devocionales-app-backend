# Etapa de construcción
FROM eclipse-temurin:17-jdk AS build
WORKDIR /app

# Copia los archivos necesarios para construir el proyecto
COPY pom.xml .
COPY src ./src

# Empaqueta el proyecto (sin correr los tests)
RUN mvn -ntp clean package -DskipTests

# Etapa final: imagen liviana
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia el .jar generado desde la etapa anterior
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto por defecto de Spring Boot
EXPOSE 8080

# Comando para iniciar la app
ENTRYPOINT ["java", "-jar", "app.jar"]