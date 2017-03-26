package com.tomato.framework.plugin.cache.ops;

import java.util.function.Function;

/**
 * 缓存高级版
 * @param <V>
 */
public interface RemoteCacheValueAdvanceOps<V> {

    /**
     * 获取V，高级版
     *
     * @param key
     * @param valueLoader
     * @param timeout(s)
     * @return
     */
    V vget(String key, Function<String, V> valueLoader, long timeout);

    /**
     * 获取V，高级版
     *
     * @param key
     * @param valueLoader
     * @return
     */
    V vget(String key, Function<String, V> valueLoader);

}
