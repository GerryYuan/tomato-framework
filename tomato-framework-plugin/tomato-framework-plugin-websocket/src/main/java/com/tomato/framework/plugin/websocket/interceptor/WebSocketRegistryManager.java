package com.tomato.framework.plugin.websocket.interceptor;

import com.google.common.collect.LinkedHashMultimap;
import com.google.common.collect.Multimap;
import org.springframework.util.Assert;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

/**
 * @author Created by gerry
 * @version 1.0, 2017-04-05-16:47
 * @since com.hujiang 1.0.0
 */
public class WebSocketRegistryManager {

    private static final Multimap<WebSocketHandler, String> pathLinkedHashMultimap = LinkedHashMultimap.create();

    private static final Multimap<WebSocketHandler, HandshakeInterceptor> interceptorLinkedHashMultimap = LinkedHashMultimap.create();

    public static void register(WebSocketHandler webSocketHandler, String... paths) {
        Assert.notEmpty(paths, "Paths must not be empty");
        for (String path : Arrays.asList(paths)) {
            pathLinkedHashMultimap.put(webSocketHandler, path);
        }
    }

    public static void register(WebSocketHandler webSocketHandler, HandshakeInterceptor handshakeInterceptor, String... paths) {
        Assert.notEmpty(paths, "Paths must not be empty");
        for (String path : Arrays.asList(paths)) {
            pathLinkedHashMultimap.put(webSocketHandler, path);
            interceptorLinkedHashMultimap.put(webSocketHandler, handshakeInterceptor);
        }
    }

    public static void register(WebSocketHandler webSocketHandler, List<HandshakeInterceptor> handshakeInterceptors, String... paths) {
        Assert.notEmpty(paths, "Paths must not be empty");
        for (String path : Arrays.asList(paths)) {
            pathLinkedHashMultimap.put(webSocketHandler, path);
            for (HandshakeInterceptor handshakeInterceptor : handshakeInterceptors) {
                interceptorLinkedHashMultimap.put(webSocketHandler, handshakeInterceptor);
            }
        }
    }

    public static Set<WebSocketHandler> handlers() {
        return pathLinkedHashMultimap.keySet();
    }

    public static String[] paths(WebSocketHandler webSocketHandler) {
        return pathLinkedHashMultimap.get(webSocketHandler).stream().toArray(String[]::new);
    }

    public static HandshakeInterceptor[] interceptors(WebSocketHandler webSocketHandler) {
        return interceptorLinkedHashMultimap.get(webSocketHandler).stream().toArray(HandshakeInterceptor[]::new);
    }

}
