# Etapa de construcción
FROM eclipse-temurin:17 AS build
WORKDIR /app
COPY src .
RUN ./mvnw package -DskipTests

# Etapa final
FROM eclipse-temurin:17-jdk
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]