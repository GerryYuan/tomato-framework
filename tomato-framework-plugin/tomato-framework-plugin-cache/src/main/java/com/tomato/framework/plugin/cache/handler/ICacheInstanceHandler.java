package com.tomato.framework.plugin.cache.handler;

import com.tomato.framework.plugin.cache.expire.CacheExpire;

/**
 *
 */
public interface ICacheInstanceHandler {

    <T extends CacheExpire> T expire();

    boolean allowNullValues();

}
