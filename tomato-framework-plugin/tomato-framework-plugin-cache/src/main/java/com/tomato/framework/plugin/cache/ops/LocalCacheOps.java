package com.tomato.framework.plugin.cache.ops;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheStats;
import com.google.common.collect.ImmutableMap;
import com.tomato.framework.core.exception.CacheException;
import com.tomato.framework.core.exception.SysException;
import com.tomato.framework.core.util.EmptyUtils;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ExecutionException;
import java.util.function.Function;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by gerry
 */
@Slf4j
public class LocalCacheOps<V> {

    private Cache<String, V> cache;

    public LocalCacheOps(Cache<String, V> cache) {
        this.cache = cache;
    }

    public V getIfPresent(String key) {
        return cache.getIfPresent(key);
    }

    public V get(String key, Callable<? extends V> callable) throws ExecutionException {
        try {
            V v = cache.getIfPresent(key);
            if (EmptyUtils.isNotEmpty(v)) {
                return v;
            }
            v = callable.call();
            if (EmptyUtils.isEmpty(v)) {
                return v;
            }
            cache.put(key, v);
            return v;
        } catch (Exception e) {
            throw new CacheException(e.getMessage(), e);
        }
    }

    public V get(String key) {
        try {
            return cache.getIfPresent(key);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
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

}
