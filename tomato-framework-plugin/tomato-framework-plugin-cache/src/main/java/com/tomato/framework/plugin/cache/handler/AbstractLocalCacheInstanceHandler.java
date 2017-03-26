package com.tomato.framework.plugin.cache.handler;

/**
 * @author Created by gerry
 */
public abstract class AbstractLocalCacheInstanceHandler implements ILocalCacheInstanceHandler{

    @Override
    public String getInstanceName() {
        return this.getClass().getName();
    }

}
