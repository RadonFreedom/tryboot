package fre.shown.tryboot.service.redis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Radon Freedom
 * created at 2019.05.05 下午5:51
 */

@Service
public class RedisService {

    private final RedisTemplate<String, Object> redisTemplate;

    public RedisService(@Qualifier("redisTemplate") RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
        Set<String> keys = redisTemplate.keys("*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }

    public void setById(Long id, Object value) {
        String key = value.getClass().getName() + id;
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    public void set(String key, Object value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @SuppressWarnings("unchecked")
    public <T> T getById(Long id, Class<T> clazz) {
        String key = clazz.getName() + id;
        return (T) redisTemplate.opsForValue().get(key);
    }

    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    public <T> Boolean hasKey(Long id, Class<T> clazz) {
        String key = clazz.getName() + id;
        return redisTemplate.hasKey(key);
    }

    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 根据key的前缀删除所有键值对
     *
     * @param keyPrefix 键前缀
     */
    public void deleteKeysByPrefix(String keyPrefix) {
        Set<String> keys = redisTemplate.keys(keyPrefix + "*");
        if (keys != null) {
            redisTemplate.delete(keys);
        }
    }
}
