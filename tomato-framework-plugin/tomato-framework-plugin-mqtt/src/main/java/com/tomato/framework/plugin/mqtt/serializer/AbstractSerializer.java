package com.tomato.framework.plugin.mqtt.serializer;

import com.alibaba.fastjson.JSONObject;
import com.tomato.framework.plugin.mqtt.msg.MsgContextHeader;
import org.springframework.messaging.MessageHeaders;

public abstract class AbstractSerializer implements Serializer {
    
    @Override
    public MsgContextHeader deserializeHeader(MessageHeaders messageHeaders) {
        JSONObject jsonObject = new JSONObject(messageHeaders);
        return jsonObject.toJavaObject(MsgContextHeader.class);
    }
}
