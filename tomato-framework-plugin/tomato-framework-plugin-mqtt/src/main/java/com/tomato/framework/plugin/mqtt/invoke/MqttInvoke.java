package com.tomato.framework.plugin.mqtt.invoke;

import com.tomato.framework.plugin.mqtt.msg.MsgContext;

/**
 * @author yuanguohua
 */
public interface MqttInvoke<T> {
    
    void invoke(MsgContext<T> msgContext);
    
}
