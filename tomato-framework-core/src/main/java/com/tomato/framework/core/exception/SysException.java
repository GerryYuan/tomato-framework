package com.tomato.framework.core.exception;

import com.tomato.framework.core.common.ExceptionCodeConst;
import lombok.Getter;

public class SysException extends RuntimeException {

    private static final long serialVersionUID = 3116483353040779859L;

    @Getter
    private int errorCode = ExceptionCodeConst.SYS_EXCEPTION_CODE;

    public SysException(String msg) {
        super(msg);
    }

    public SysException(String msg, Throwable cause) {
        super(msg, cause);
    }


    public SysException(Throwable cause) {
        super(cause);
    }

}