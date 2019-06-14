package com.tomato.framework.plugin.mqtt.serializer;

import com.tomato.framework.plugin.mqtt.exception.MqttSerializerException;
import java.nio.charset.Charset;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class PlainTextSerializer extends AbstractSerializer {

    private Charset charset;

    @Override
    public byte[] serialize(Object object) {
        if (object == null) {
            return null;
        }
        return object.toString().getBytes(charset);
    }

    @Override
    public <T> T deserialize(byte[] content, Class<T> targetClass) {
        if (targetClass == String.class || targetClass == Object.class) {
            return (T) new String(content, charset);
        }
        throw new MqttSerializerException("消息类型不是String 或 Object，不能使用PlainTextSerializer");
    }
    
}
