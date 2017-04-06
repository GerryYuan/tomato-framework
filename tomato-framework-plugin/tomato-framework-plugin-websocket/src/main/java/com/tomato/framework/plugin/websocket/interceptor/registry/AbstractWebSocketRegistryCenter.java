package com.tomato.framework.plugin.websocket.interceptor.registry;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

@Slf4j
public abstract class AbstractWebSocketRegistryCenter implements WebSocketRegistryCenter {

    @Override
    public void afterPropertiesSet() throws Exception {
        register();
    }

//    private static final List<WebSocketSession> sessions = new ArrayList<WebSocketSession>();

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        if(log.isDebugEnabled()){
            log.info("客户端[{}]，连接webSocket成功", session.getId());
        }
//        sessions.add(session);
//        session.sendMessage(new TextMessage("服务端链接成功！"));
        onOpen(session);
    }

    public abstract void onOpen(WebSocketSession session);

    @Override
    public void handleMessage(WebSocketSession wss, WebSocketMessage<?> wsm) throws Exception {
//        TextMessage returnMessage = new TextMessage("用户" + wss.getId() + "说:" + wsm.getPayload());
//        for (WebSocketSession session : sessions) {
//            session.sendMessage(returnMessage);
//        }
        onMessage(wss, wsm);
    }

    public abstract void onMessage(WebSocketSession session, WebSocketMessage<?> wsm);

    @Override
    public void handleTransportError(WebSocketSession session, Throwable thrwbl) throws Exception {
        if (session.isOpen()) {
            session.close();
        }
//        sessions.remove(wss);
//        System.out.println("WebSocket出错！");
        onError(session, thrwbl);
    }

    public abstract void onError(WebSocketSession session, Throwable throwable);

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus cs) throws Exception {
//        sessions.remove(wss);
//        System.out.println("WebSocket关闭！");
        onClosed(session, cs);
    }

    public abstract void onClosed(WebSocketSession session, CloseStatus closeStatus);


    @Override
    public boolean supportsPartialMessages() {
        return false;
    }
}
