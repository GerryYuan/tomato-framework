package com.tomato.framework.plugin.cache.expire;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.concurrent.TimeUnit;

/**
 * 本地缓存过期类型
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class LocalCacheExpire extends CacheExpire {

    private CacheExpire.ExpireType expireType = CacheExpire.ExpireType.WRITE;

    public LocalCacheExpire(long duration, TimeUnit unit) {
        super(duration, unit);
    }

    public LocalCacheExpire(long duration, TimeUnit unit, CacheExpire.ExpireType expireType) {
        super(duration, unit);
        this.expireType = expireType;
    }

}
