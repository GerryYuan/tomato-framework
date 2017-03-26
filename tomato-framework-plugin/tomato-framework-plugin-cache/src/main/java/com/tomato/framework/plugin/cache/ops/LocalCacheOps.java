package com.tomato.framework.plugin.cache.ops;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;
import com.google.common.collect.ImmutableMap;
import com.tomato.framework.core.util.EmptyUtils;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;

/**
 * Created by gerry
 *
 * @param <V>
 */
public class LocalCacheOps<V> {
    private Cache<String, V> cache;

    public LocalCacheOps(Cache<String, V> cache) {
        this.cache = cache;
    }

    public V getIfPresent(String key) {
        return cache.getIfPresent(key);
    }

    public V get(String key, Callable<? extends V> valueLoader) throws ExecutionException {
        return cache.get(key, valueLoader);
    }

    public V get(String key) {
        try {
            return cache.get(key, new Callable<V>() {
                public V call() {
                    return null;
                }
            });
        } catch (Exception e) {
            return null;
        }
    }

    public ImmutableMap<String, V> getAllPresent(Iterable<String> keys) {
        return cache.getAllPresent(keys);
    }

    public void put(String key, V value) {
        cache.put(key, value);
    }

    public void putAll(Map<String, V> m) {
        cache.putAll(m);
    }

    public void invalidate(String key) {
        cache.invalidate(key);
    }

    public void invalidateAll(Iterable<String> keys) {
        cache.invalidateAll(keys);
    }

    public void invalidateAll() {
        cache.invalidateAll();
    }

    public long size() {
        return cache.size();
    }

    public CacheStats stats() {
        return cache.stats();
    }

    public ConcurrentMap<String, V> asMap() {
        return cache.asMap();
    }

    public void cleanUp() {
        cache.cleanUp();
    }

    public V get(String key, Function<String, V> valueLoader) {
        V value = get(key);
        if (EmptyUtils.isNotEmpty(value)) {
            return value;
        }
        value = valueLoader.apply(key);
        if (EmptyUtils.isEmpty(value)) {
            return value;
        }
        put(key, value);
        return value;
    }


}
