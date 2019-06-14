package com.tomato.framework.plugin.websocket.config;

import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Created by gerry
 * @version 1.0, 2017-04-05-16:11
 * @since com.hujiang 1.0.0
 */
@Component
public class SystemWebSocketHandler implements WebSocketHandler {
    private static final List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();
    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        System.out.println("链接webSocket成功");
        sessions.add(session);
        session.sendMessage(new TextMessage("服务端链接成功！"));
    }

    @Override
    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {
//        TextMessage returnMessage = new TextMessage(wss.getId() + ":" + wsm.getPayload());
        TextMessage returnMessage = new TextMessage("用户"+wss.getId()+"说:" + wsm.getPayload());
        System.out.println(returnMessage.toString());
//        wss.sendMessage(returnMessage);
        for (WebSocketSession session : sessions) {
            session.sendMessage(returnMessage);
        }
    }

    @Override
    public void handleTransportError(WebSocketSession wss, Throwable thrwbl) throws Exception {
        if(wss.isOpen()){
            wss.close();
        }
        sessions.remove(wss);
        System.out.println("WebSocket出错！");
    }

    @Override
    public void afterConnectionClosed(WebSocketSession wss, CloseStatus cs) throws Exception {
        sessions.remove(wss);
        System.out.println("WebSocket关闭！");
    }

    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}