package fre.shown.tryboot.service;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

/**
 * @author Radon Freedom
 * created at 2019.05.05 下午5:51
 */

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;


    public RedisService(RedisConnectionFactory redisConnectionFactory) {
        this.redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setValueSerializer(RedisSerializer.json());
        redisTemplate.afterPropertiesSet();
    }

    public void set(Long id, Object value) {
        String key = value.getClass().getName() + "_" + id;
        redisTemplate.opsForValue().set(key, value);
    }

    public <T> T get(Long id, Class<T> clazz) {
        String key = clazz.getName() + "_" + id;
        return (T) redisTemplate.opsForValue().get(key);
    }
}
