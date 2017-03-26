package com.tomato.framework.plugin.cache.ops;

import com.tomato.framework.core.util.EmptyUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-26-10:42
 * @since com.hujiang 1.0.0
 */
@Slf4j
public class RemoteCacheManager<V> implements RemoteCacheValueOps<V>, RemoteCacheValueAdvanceOps<V> {

    private RedisTemplate<String, V> redisTemplate;

    public RemoteCacheManager(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public void vset(String key, V value) {
        redisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void vset(String key, V value, long timeout, TimeUnit unit) {
        redisTemplate.opsForValue().set(key, value, timeout, unit);
    }

    @Override
    public Boolean vsetIfAbsent(String key, V value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    @Override
    public void vmultiSet(Map<? extends String, ? extends V> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    @Override
    public Boolean vmultiSetIfAbsent(Map<? extends String, ? extends V> map) {
        return redisTemplate.opsForValue().multiSetIfAbsent(map);
    }

    @Override
    public V vget(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public V vgetAndSet(String key, V value) {
        return redisTemplate.opsForValue().getAndSet(key, value);
    }

    @Override
    public List<V> vmultiGet(Collection<String> keys) {
        return redisTemplate.opsForValue().multiGet(keys);
    }

    @Override
    public Long vincrement(String key, long delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Double vincrement(String key, double delta) {
        return redisTemplate.opsForValue().increment(key, delta);
    }

    @Override
    public Integer vappend(String key, String value) {
        return redisTemplate.opsForValue().append(key, value);
    }

    @Override
    public String vget(String key, long start, long end) {
        return redisTemplate.opsForValue().get(key, start, end);
    }

    @Override
    public void vset(String key, V value, long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    @Override
    public Long vsize(String key) {
        return redisTemplate.opsForValue().size(key);
    }

    @Override
    public Boolean vsetBit(String key, long offset, boolean value) {
        return redisTemplate.opsForValue().setBit(key, offset, value);
    }

    @Override
    public Boolean vgetBit(String key, long offset) {
        return redisTemplate.opsForValue().getBit(key, offset);
    }

    @Override
    public V vget(String key, Function<String, V> valueLoader, long timeout) {
        ValueOperations<String, V> valueOperations = redisTemplate.opsForValue();
        V value = null;
        try {
            value = valueOperations.get(key);
            if (EmptyUtils.isNotEmpty(value)) {
                return value;
            }
            if (EmptyUtils.isEmpty(valueLoader)) {
                return value;
            }
        } catch (Exception e) {
            log.error("deserialize key [{}] object error, maybe object property is miss or order changed, return null.", key, e);
        }
        value = valueLoader.apply(key);
        if (EmptyUtils.isEmpty(value)) {
            return value;
        }
        valueOperations.set(key, value);
        if (timeout > 0) {
            redisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
        return value;
    }

    @Override
    public V vget(String key, Function<String, V> valueLoader) {
        return vget(key, valueLoader, 0);
    }

}
