package dylan.devocionalesspring.controladores;

import dylan.devocionalesspring.RedisConfig.RedisPublisher;
import dylan.devocionalesspring.dto.MensajeDTO;
import dylan.devocionalesspring.dto.UsuarioDTO;
import dylan.devocionalesspring.entidades.Mensaje;
import dylan.devocionalesspring.servicios.MensajeServicio;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;

import java.util.Map;

@Controller
public class ChatWebSocketController {

    @Autowired
    private MensajeServicio mensajeServicio;

    @Autowired
    private RedisPublisher redisPublisher;

    @Autowired
    private RedisConnectionFactory connectionFactory;

    @PostConstruct
    public void checkRedisConnection() {
        System.out.println("Conectado a Redis en: " + connectionFactory.getConnection().ping());
    }

    @MessageMapping("/chat.send")
    public void enviarMensajeWebSocket(@Payload Map<String, String> payload) {
        Long emisorId = Long.parseLong(payload.get("emisorId"));
        Long receptorId = Long.parseLong(payload.get("receptorId"));
        String contenido = payload.get("contenido");

        Mensaje mensaje = mensajeServicio.enviarMensaje(emisorId, receptorId, contenido);

        MensajeDTO mensajeDTO = new MensajeDTO(
                mensaje.getId(),
                mensaje.getContenido(),
                mensaje.getFechaEnvio(),
                new UsuarioDTO(mensaje.getEmisor()),
                new UsuarioDTO(mensaje.getReceptor())
        );

        // Publicar mensaje en Redis (las instancias activas lo reenviarán por STOMP)
        redisPublisher.publicar("chat-mensajes", mensajeDTO);
    }
}
