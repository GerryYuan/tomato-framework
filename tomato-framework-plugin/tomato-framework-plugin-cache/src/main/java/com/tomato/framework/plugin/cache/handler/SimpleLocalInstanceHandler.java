package com.tomato.framework.plugin.cache.handler;

import com.tomato.framework.plugin.cache.common.CacheTimeoutConst;
import com.tomato.framework.plugin.cache.expire.LocalCacheExpire;

import java.util.concurrent.TimeUnit;

/**
 * @author Created by gerry
 */
public class SimpleLocalInstanceHandler extends AbstractLocalCacheInstanceHandler {

    @Override
    public LocalCacheExpire expire() {
        return new LocalCacheExpire(CacheTimeoutConst.ONE_HOUR, TimeUnit.SECONDS);
    }

    @Override
    public boolean allowNullValues() {
        return false;
    }
}
