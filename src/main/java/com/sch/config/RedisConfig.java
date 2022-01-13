package com.sch.config;

;
import com.alibaba.fastjson.support.spring.FastJsonRedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @Auth: Gao
 * @Date: 2022/1/13 14:14
 */

@Configuration
public class RedisConfig {

    @Bean
    //@Qualifier 放在方法上可以指定注入相同名字bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        //配置客户端jedis/lettuce
        RedisTemplate<String, Object> template = new RedisTemplate();
        template.setConnectionFactory(redisConnectionFactory);

        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();

        FastJsonRedisSerializer fastJsonRedisSerializer = new FastJsonRedisSerializer(Object.class);

        template.setKeySerializer(stringRedisSerializer);
        template.setHashKeySerializer(stringRedisSerializer);
        template.setValueSerializer(fastJsonRedisSerializer);
        template.setHashValueSerializer(fastJsonRedisSerializer);

        //保存
        template.afterPropertiesSet();
        return template;
    }
}
