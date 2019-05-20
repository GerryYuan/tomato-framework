package com.tomato.framework.plugin.mqtt.invoke;

import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractMqttInvoke implements MqttInvoke, InitializingBean {
    
    public abstract String[] listenTopics();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        MqttInvokePlugin.getInstance().store(listenTopics(), this);
    }
}
