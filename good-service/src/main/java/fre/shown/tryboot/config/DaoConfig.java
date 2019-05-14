package fre.shown.tryboot.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * @author Radon Freedom
 * created at 2019.04.19 上午9:34
 */


@Configuration
@MapperScan("fre.shown.tryboot.dao")
public class DaoConfig {

    @Bean
    public RedisTemplate<String, Object> DORedisTemplate(RedisConnectionFactory redisConnectionFactory) {

        RedisTemplate<String, Object> DORedisTemplate = new RedisTemplate<>();
        DORedisTemplate.setConnectionFactory(redisConnectionFactory);
        DORedisTemplate.setValueSerializer(RedisSerializer.json());
        return DORedisTemplate;
    }
}
