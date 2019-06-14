package com.tomato.framework.plugin.cache.config;

import com.google.common.collect.Sets;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

/**
 * @author Created by gerry
 */
@Configuration
public class CacheClusterConfiguration {
    
    @Bean
    public JedisCluster getJedisCluster(@Autowired RedisProperties redisProperties) {
        String[] serverArray = redisProperties.getClusterNodes().split(",");
        Set<HostAndPort> nodes = Sets.newHashSet();
        for (String ipPort : serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(), Integer.valueOf(ipPortPair[1].trim())));
        }
        return new JedisCluster(nodes, redisProperties.getCommandTimeout());
    }
    
}
