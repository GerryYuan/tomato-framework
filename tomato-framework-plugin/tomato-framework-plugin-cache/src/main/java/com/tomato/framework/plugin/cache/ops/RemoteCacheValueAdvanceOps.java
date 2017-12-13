package com.tomato.framework.plugin.cache.ops;

import java.util.concurrent.Callable;
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
     * @param callable
     * @param timeout(s)
     * @return
     */
    V vget(String key, Callable<V> callable, long timeout);

    /**
     * 获取V，高级版
     *
     * @param key
     * @param callable
     * @return
     */
    V vget(String key, Callable<V> callable);

}
