package com.tomato.framework.plugin.cache.serializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.tomato.framework.core.util.EmptyUtils;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;

import java.nio.charset.Charset;

/**
 * @author Created by gerry
 */
public class FastJson4RedisSerializer implements RedisSerializer<Object> {

    @Override
    public byte[] serialize(Object t) throws SerializationException {
        if (EmptyUtils.isEmpty(t)) {
            return new byte[0];
        }
        return JSON.toJSONString(t, SerializerFeature.WriteMapNullValue).getBytes(Charset.defaultCharset());
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (EmptyUtils.isEmpty(bytes)) {
            return null;
        }
        return JSON.parse(bytes);
    }
}
