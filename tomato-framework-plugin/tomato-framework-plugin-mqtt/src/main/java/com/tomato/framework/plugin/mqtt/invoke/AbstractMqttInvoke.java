package com.tomato.framework.plugin.mqtt.invoke;

import com.tomato.framework.plugin.mqtt.msg.MsgContext;
import org.springframework.beans.factory.InitializingBean;

public abstract class AbstractMqttInvoke<T> implements MqttInvoke<T>, InitializingBean {
    
    public abstract String[] listenTopics();
    
    @Override
    public void afterPropertiesSet() throws Exception {
        MqttInvokePlugin.getInstance().store(listenTopics(), this);
    }
    
    public abstract void handler(T msg);
    
    @Override
    public void invoke(MsgContext<T> msgContext) {
        handler(msgContext.getMsg());
    }
}
