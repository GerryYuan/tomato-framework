package com.tomato.framework.plugin.cache.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * Created by gerry
 */
@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = {"com.tomato.framework.plugin.cache"})
public class CacheConfig {
}
