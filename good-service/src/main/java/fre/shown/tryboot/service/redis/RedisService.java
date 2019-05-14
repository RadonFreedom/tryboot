package fre.shown.tryboot.service.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * @author Radon Freedom
 * created at 2019.05.05 下午5:51
 */

@Service
public class RedisService {

    private final RedisTemplate<String, Object> stringObjectRedisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public RedisService(RedisConnectionFactory redisConnectionFactory, StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.stringObjectRedisTemplate = new RedisTemplate<>();
        stringObjectRedisTemplate.setConnectionFactory(redisConnectionFactory);
        stringObjectRedisTemplate.setValueSerializer(RedisSerializer.json());
        stringObjectRedisTemplate.afterPropertiesSet();
    }

    public void setDOById(Long id, Object value) {
        String key = value.getClass().getName() + id;
        stringObjectRedisTemplate.opsForValue().set(key, value);
    }

    public void setDO(String key, Object value) {
        stringObjectRedisTemplate.opsForValue().set(key, value);
    }

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @SuppressWarnings("unchecked")
    public <T> T getDOById(Long id, Class<T> clazz) {
        String key = clazz.getName() + id;
        return (T) stringObjectRedisTemplate.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getDO(String key, Class<T> clazz) {
        return (T) stringObjectRedisTemplate.opsForValue().get(key);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public <T> Boolean hasKey(Long id, Class<T> clazz) {
        String key = clazz.getName() + id;
        return stringObjectRedisTemplate.hasKey(key);
    }

    public Boolean hasKey(String key) {
        return stringObjectRedisTemplate.hasKey(key);
    }

    public void deleteKey(String key) {
        stringObjectRedisTemplate.delete(key);
    }

    /**
     * 根据key的前缀删除所有键值对
     *
     * @param keyPattern 键前缀
     */
    public void deleteKeysByPrefix(String keyPattern) {
        Set<String> keys = stringObjectRedisTemplate.keys("*" + keyPattern + "*");
        if (keys != null) {
            stringObjectRedisTemplate.delete(keys);
        }
    }
}
