package com.tomato.framework.plugin.websocket.config;

import com.tomato.framework.plugin.websocket.interceptor.WebSocketRegistryManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

/**
 * Created by gerry
 */
@Slf4j
@Configuration
@EnableWebSocket
@ComponentScan(basePackages = {"com.tomato.framework.plugin.websocket"})
public class WebSocketConfig implements WebSocketConfigurer {

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        WebSocketRegistryManager.handlers().forEach(handler -> {
            String[] paths = WebSocketRegistryManager.paths(handler);
            org.springframework.web.socket.server.HandshakeInterceptor[] handshakeInterceptors = WebSocketRegistryManager.interceptors(handler);
            registry.addHandler(handler, paths).setAllowedOrigins("*").addInterceptors(handshakeInterceptors);
            log.info("springboot register websocket url -> {} success.", paths);
        });
    }

//    @Bean
//    public WebSocketHandler systemWebSocketHandler() {
//        return new SystemWebSocketHandler();
//    }

}
