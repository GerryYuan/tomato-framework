package com.tomato.framework.plugin.cache.config;

import com.tomato.framework.plugin.cache.ops.RemoteCacheManager;
import com.tomato.framework.plugin.cache.serializer.FastJson4RedisSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-24-18:02
 * @since com.hujiang 1.0.0
 */
@Configuration
public class CacheConfiguration {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        return new JedisConnectionFactory();
    }

    @Bean
    public <T> RedisTemplate<String, T> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, T> template = new RedisTemplate<String, T>();
        template.setConnectionFactory(jedisConnectionFactory());
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new FastJson4RedisSerializer());
        return template;
    }

    @Bean
    public <V> RemoteCacheManager<V> remoteCacheManager() {
        return new RemoteCacheManager<V>(redisTemplate(jedisConnectionFactory()));
    }

}
