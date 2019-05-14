package fre.shown.tryboot.service.redis;

import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Radon Freedom
 * created at 2019.05.05 下午5:51
 */

@Service
public class RedisService {

    private final RedisTemplate<String, Object> DORedisTemplate;
    private final StringRedisTemplate stringRedisTemplate;

    public RedisService(RedisConnectionFactory redisConnectionFactory, StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
        this.DORedisTemplate = new RedisTemplate<>();
        DORedisTemplate.setConnectionFactory(redisConnectionFactory);
        DORedisTemplate.setValueSerializer(RedisSerializer.json());
        DORedisTemplate.afterPropertiesSet();
    }

    public void setDOById(Long id, Object value) {
        String key = value.getClass().getName() + id;
        DORedisTemplate.opsForValue().set(key, value);
    }

    public void setDO(String key, Object value) {
        DORedisTemplate.opsForValue().set(key, value);
    }
    @SuppressWarnings("unchecked")
    public <T> T getDOById(Long id, Class<T> clazz) {
        String key = clazz.getName() + id;
        return (T) DORedisTemplate.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T getDO(String key, Class<T> clazz) {
        return (T) DORedisTemplate.opsForValue().get(key);
    }

    public <T> Boolean hasDO(Long id, Class<T> clazz) {
        String key = clazz.getName() + id;
        return DORedisTemplate.hasKey(key);
    }

    public Boolean hasDO(String key) {
        return DORedisTemplate.hasKey(key);
    }

    public void deleteDO(String key) {
        DORedisTemplate.delete(key);
    }

    /**
     * 根据key的前缀删除所有键值对
     *
     * @param keyPattern 键前缀
     */
    public void deleteDOByPrefix(String keyPattern) {
        Set<String> keys = DORedisTemplate.keys("*" + keyPattern + "*");
        if (keys != null) {
            DORedisTemplate.delete(keys);
        }
    }

    public void setString(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setString(String key, String value, long timeout, TimeUnit unit) {
        stringRedisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    public Boolean hasString(String key) {
        return stringRedisTemplate.hasKey(key);
    }

    public void deleteString(String key) {
        stringRedisTemplate.delete(key);
    }

    public void deleteStringByPrefix(String keyPattern) {
        Set<String> keys = stringRedisTemplate.keys("*" + keyPattern + "*");
        if (keys != null) {
            stringRedisTemplate.delete(keys);
        }
    }
}
