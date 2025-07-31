package dylan.devocionalesspring.RedisConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import dylan.devocionalesspring.dto.MensajeDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisSubscriber implements MessageListener {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Override
    public void onMessage(Message message, byte[] pattern) {
        try {
            String json = new String(message.getBody());
            System.out.println("Mensaje recibido en RedisSubscriber: " + json);

            // Parseo
            ObjectMapper mapper = new ObjectMapper();
            mapper.findAndRegisterModules();
            mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            MensajeDTO mensajeDTO = mapper.readValue(json, MensajeDTO.class);

            String emisorId = mensajeDTO.getEmisor().getIdUsuario().toString();
            String receptorId = mensajeDTO.getReceptor().getIdUsuario().toString();

            System.out.println("Enviando mensaje a usuarios: emisorId=" + emisorId + ", receptorId=" + receptorId);

            simpMessagingTemplate.convertAndSendToUser(emisorId, "/queue/messages", mensajeDTO);
            simpMessagingTemplate.convertAndSendToUser(receptorId, "/queue/messages", mensajeDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
