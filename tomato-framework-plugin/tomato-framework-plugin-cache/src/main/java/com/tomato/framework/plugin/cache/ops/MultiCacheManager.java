package com.tomato.framework.plugin.cache.ops;

import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.plugin.cache.expire.LocalCacheExpire;
import com.tomato.framework.plugin.cache.handler.AbstractLocalCacheInstanceHandler;
import com.tomato.framework.plugin.cache.handler.ILocalCacheInstanceHandler;
import com.tomato.framework.plugin.cache.handler.SimpleLocalInstanceHandler;

import java.util.concurrent.TimeUnit;
import java.util.function.Function;

/**
 * @author Created by gerry
 */
public class MultiCacheManager {

    private static volatile MultiCacheManager instance;

    public static MultiCacheManager getInstance() {
        if (instance == null) {
            synchronized (MultiCacheManager.class) {
                if (instance == null) {
                    instance = new MultiCacheManager();
                }
            }
        }
        return instance;
    }

    /**
     * 缓存
     * @param redisKey
     * @param valueLoader
     * @param localTimeout  一级缓存时间
     * @param remoteTimeout 二级缓存时间
     * @return
     */
    public <V> V vget(String redisKey, Function<String, V> valueLoader, Integer localTimeout, Integer remoteTimeout) {
        ILocalCacheInstanceHandler localHandler = new AbstractLocalCacheInstanceHandler() {
            @Override
            public LocalCacheExpire expire() {
                return new LocalCacheExpire(localTimeout, TimeUnit.SECONDS);
            }

            @Override
            public boolean allowNullValues() {
                return false;
            }
        };
        //一级缓存有数据，直接返回，没有数据到二级缓存查，二级缓存有则吧数据放到一级缓存，没有则从数据库拿，然后放到一二级缓存
        LocalCacheOps<V> localCacheOpsExt = LocalCacheManager.<V>getInstance().getCacheOps(localHandler);
        V value = localCacheOpsExt.get(redisKey);
        if (EmptyUtils.isNotEmpty(value)) {
            return value;
        }
        value = RemoteCacheManager.<V>getInstance().vget(redisKey, valueLoader, remoteTimeout);
        if (EmptyUtils.isNotEmpty(value)) {
            localCacheOpsExt.put(redisKey, value);
        }
        return value;
    }

    /**
     *
     * @param redisKey
     * @param valueLoader
     * @param remoteTimeout
     * @param <V>
     * @return
     */
    public <V> V vget(String redisKey, Function<String, V> valueLoader, Integer remoteTimeout) {
        //一级缓存有数据，直接返回，没有数据到二级缓存查，二级缓存有则吧数据放到一级缓存，没有则从数据库拿，然后放到一二级缓存
        LocalCacheOps<V> localCacheOpsExt = LocalCacheManager.<V>getInstance().getCacheOps(new SimpleLocalInstanceHandler());
        V value = localCacheOpsExt.get(redisKey);
        if (EmptyUtils.isNotEmpty(value)) {
            return value;
        }
        value = RemoteCacheManager.<V>getInstance().vget(redisKey, valueLoader, remoteTimeout);
        if (EmptyUtils.isNotEmpty(value)) {
            localCacheOpsExt.put(redisKey, value);
        }
        return value;
    }
}
