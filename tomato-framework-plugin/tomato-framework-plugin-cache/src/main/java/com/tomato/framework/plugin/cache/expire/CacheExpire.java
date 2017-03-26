package com.tomato.framework.plugin.cache.expire;

import lombok.Data;

import java.util.concurrent.TimeUnit;

/**
 * @author Created by gerry
 */
@Data
public class CacheExpire {

    private long duration;

    private TimeUnit unit;

    public CacheExpire(long duration, TimeUnit unit) {
        this.duration = duration;
        this.unit = unit;
    }

    public enum ExpireType {
        ACCESS, WRITE
    };

}
