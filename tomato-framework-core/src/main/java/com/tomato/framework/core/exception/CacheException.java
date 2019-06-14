package com.tomato.framework.core.exception;

import com.tomato.framework.core.common.ExceptionCodeConst;
import lombok.Getter;

public class CacheException extends RuntimeException {

    private static final long serialVersionUID = 3116483353040779859L;

    @Getter
    private int errorCode = ExceptionCodeConst.CACHE_CODE;

    public CacheException(String msg) {
        super(msg);
    }

    public CacheException(String msg, Throwable cause) {
        super(msg, cause);
    }


    public CacheException(Throwable cause) {
        super(cause);
    }

}