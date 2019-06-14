package com.tomato.framework.plugin.websocket.interceptor.registry;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.web.socket.WebSocketHandler;

public interface WebSocketRegistryCenter extends InitializingBean, WebSocketHandler{

	void register();
	
}
