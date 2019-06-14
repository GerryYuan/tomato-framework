package com.tomato.framework.plugin.cache.manager;

import com.google.common.cache.CacheBuilder;
import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.plugin.cache.expire.LocalCacheExpire;
import com.tomato.framework.plugin.cache.handler.ILocalCacheInstanceHandler;
import com.tomato.framework.plugin.cache.handler.SimpleLocalInstanceHandler;

import com.tomato.framework.plugin.cache.ops.LocalCacheOps;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;

/**
 * Created by gerry
 *
 * @param <V>
 */
public class LocalCacheManager<V> {

    private final ConcurrentMap<String, LocalCacheOps<V>> cacheMap = new ConcurrentHashMap<>();

    private static volatile LocalCacheManager instance;

    public static <V> LocalCacheManager<V> getInstance() {
        if (instance == null) {
            synchronized (LocalCacheManager.class) {
                if (instance == null) {
                    instance = new LocalCacheManager<V>();
                }
            }
        }
        return instance;
    }

    public LocalCacheOps<V> getCacheOps(ILocalCacheInstanceHandler handler) {
        String cacheName = handler.getInstanceName();
        LocalCacheExpire localCacheExpire = handler.expire();
        return getCacheOps(cacheName, cacheKey -> {
            CacheBuilder cacheBuilder = CacheBuilder.newBuilder();
            if (localCacheExpire.getExpireType() == LocalCacheExpire.ExpireType.ACCESS) {
                cacheBuilder.expireAfterAccess(localCacheExpire.getDuration(), localCacheExpire.getUnit());
            } else if (localCacheExpire.getExpireType() == LocalCacheExpire.ExpireType.WRITE) {
                cacheBuilder.expireAfterWrite(localCacheExpire.getDuration(), localCacheExpire.getUnit());
            }
            return createLocalCacheOps(cacheKey, cacheBuilder);
        });
    }


    public LocalCacheOps<V> getCacheOps() {
        return getCacheOps(new SimpleLocalInstanceHandler());
    }

    private LocalCacheOps<V> createLocalCacheOps(String cacheName, CacheBuilder cacheBuilder) {
        LocalCacheOps<V> localCacheOps = new LocalCacheOps<>(cacheBuilder.build());
        cacheMap.put(cacheName, localCacheOps);
        return localCacheOps;
    }

    private LocalCacheOps<V> getCacheOps(String cacheName, Function<String, LocalCacheOps<V>> valueLoader) {
        if (getCacheNames().contains(cacheName)) {
            return cacheMap.get(cacheName);
        }
        LocalCacheOps<V> localCacheOps = valueLoader.apply(cacheName);
        if (EmptyUtils.isEmpty(localCacheOps)) {
            return localCacheOps;
        }

        cacheMap.put(cacheName, localCacheOps);
        return localCacheOps;
    }

    private Collection<String> getCacheNames() {
        return Collections.unmodifiableSet(cacheMap.keySet());
    }
}
