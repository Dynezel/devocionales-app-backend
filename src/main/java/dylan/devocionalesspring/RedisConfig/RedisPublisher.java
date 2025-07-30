package dylan.devocionalesspring.RedisConfig;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisPublisher {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void publicar(String canal, Object mensaje) {
        redisTemplate.convertAndSend(canal, mensaje);
    }
}
