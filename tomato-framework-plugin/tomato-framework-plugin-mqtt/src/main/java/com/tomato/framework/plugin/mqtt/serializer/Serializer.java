package com.tomato.framework.plugin.mqtt.serializer;

import com.tomato.framework.plugin.mqtt.msg.MsgContextHeader;
import org.springframework.messaging.MessageHeaders;

public interface Serializer {
    
    byte[] serialize(Object object);
    
    <T> T deserialize(byte[] content, Class<T> targetClass);
    
    MsgContextHeader deserializeHeader(MessageHeaders messageHeaders);
}
