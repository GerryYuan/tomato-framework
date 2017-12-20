package com.tomato.framework.plugin.cache.manager;

import com.tomato.framework.core.config.SpringApplicationContext;
import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.plugin.cache.ops.RemoteCacheAdvanceOps;
import com.tomato.framework.plugin.cache.ops.RemoteCacheOps;
import java.util.concurrent.Callable;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Created by gerry
 */
@Slf4j
public class RemoteCacheManager<V> implements RemoteCacheOps<V>, RemoteCacheAdvanceOps<V> {

    private RedisTemplate<String, V> redisTemplate;

    private static volatile RemoteCacheManager instance;

    public static <V> RemoteCacheManager<V> getInstance() {
        if (instance == null) {
            synchronized (RemoteCacheManager.class) {
                if (instance == null) {
                    instance = new RemoteCacheManager<>(
                        SpringApplicationContext.getBean("redisTemplate", RedisTemplate.class));
                }
            }
        }
        return instance;
    }

    private RemoteCacheManager(RedisTemplate<String, V> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private ValueOperations<String, V> getValueOps() {
        return redisTemplate.opsForValue();
    }

    @Override
    public void vset(String key, V value) {
        getValueOps().set(key, value);
    }

    @Override
    public void vset(String key, V value, long timeout, TimeUnit unit) {
        getValueOps().set(key, value, timeout, unit);
    }

    @Override
    public Boolean vsetIfAbsent(String key, V value) {
        return getValueOps().setIfAbsent(key, value);
    }

    @Override
    public void vmultiSet(Map<? extends String, ? extends V> map) {
        getValueOps().multiSet(map);
    }

    @Override
    public Boolean vmultiSetIfAbsent(Map<? extends String, ? extends V> map) {
        return getValueOps().multiSetIfAbsent(map);
    }

    @Override
    public V vget(String key) {
        return getValueOps().get(key);
    }

    @Override
    public V vgetAndSet(String key, V value) {
        return getValueOps().getAndSet(key, value);
    }

    @Override
    public List<V> vmultiGet(Collection<String> keys) {
        return getValueOps().multiGet(keys);
    }

    @Override
    public Long vincrement(String key, long delta) {
        return getValueOps().increment(key, delta);
    }

    @Override
    public Double vincrement(String key, double delta) {
        return getValueOps().increment(key, delta);
    }

    @Override
    public Integer vappend(String key, String value) {
        return getValueOps().append(key, value);
    }

    @Override
    public String vget(String key, long start, long end) {
        return getValueOps().get(key, start, end);
    }

    @Override
    public void vset(String key, V value, long offset) {
        getValueOps().set(key, value, offset);
    }

    @Override
    public Long vsize(String key) {
        return getValueOps().size(key);
    }

    @Override
    public Boolean vsetBit(String key, long offset, boolean value) {
        return getValueOps().setBit(key, offset, value);
    }

    @Override
    public Boolean vgetBit(String key, long offset) {
        return getValueOps().getBit(key, offset);
    }

    @Override
    public V vget(String key, Callable<V> callable, long timeout) {
        ValueOperations<String, V> valueOperations = getValueOps();
        V value = null;
        try {
            value = valueOperations.get(key);
            if (EmptyUtils.isNotEmpty(value)) {
                return value;
            }
            if (EmptyUtils.isEmpty(callable)) {
                return value;
            }
        } catch (Exception e) {
            log.error("deserialize key [{}] object error, maybe object property is miss or order changed, return null.",
                key, e);
        }
        try {
            value = callable.call();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
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
    public V vget(String key, Callable<V> callable) {
        return vget(key, callable, 0);
    }

}
