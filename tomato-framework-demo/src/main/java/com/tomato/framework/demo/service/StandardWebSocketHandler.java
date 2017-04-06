package com.tomato.framework.demo.service;

import com.tomato.framework.plugin.websocket.interceptor.WebSocketRegistryManager;
import com.tomato.framework.plugin.websocket.interceptor.registry.AbstractWebSocketRegistryCenter;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

/**
 * @author Created by gerry
 * @version 1.0, 2017-04-05-17:56
 * @since com.hujiang 1.0.0
 */
@Service
public class StandardWebSocketHandler extends AbstractWebSocketRegistryCenter {
    @Override
    public void register() {
        WebSocketRegistryManager.register(this, "/websocket");
    }

    @Override
    public void onOpen(WebSocketSession session) {
        System.out.println(session.getId() + "连接成功");
    }

    @Override
    public void onMessage(WebSocketSession session, WebSocketMessage<?> wsm) {
        System.out.println(wsm.getPayload() + "获取到消息");
    }

    @Override
    public void onError(WebSocketSession session, Throwable throwable){
        System.out.println("消息异常");
    }

    @Override
    public void onClosed(WebSocketSession session, CloseStatus closeStatus) {
        System.out.println(session.getId() + "消息关闭");

    }
}
