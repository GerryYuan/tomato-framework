package com.tomato.framework.plugin.mqtt.serializer;

import com.alibaba.fastjson.JSON;
import com.tomato.framework.plugin.mqtt.exception.MqttSerializerException;
import java.nio.charset.Charset;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MqttFastJsonSerializer extends AbstractSerializer {
    
    private Charset charset;
    
    @Override
    public byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        try {
            String json = JSON.toJSONString(object);
            return json.getBytes(charset);
        } catch (Exception e) {
            throw new MqttSerializerException("序列化到JSON发生异常", e);
        }
    }
    
    @Override
    public <T> T deserialize(byte[] content, Class<T> targetClass) {
        if (content == null) {
            return null;
        }
        try {
            String object = new String(content, charset);
            return JSON.parseObject(object, targetClass);
        } catch (Exception e) {
            throw new MqttSerializerException("反序列化JSON异常", e);
        }
    }
    
}
