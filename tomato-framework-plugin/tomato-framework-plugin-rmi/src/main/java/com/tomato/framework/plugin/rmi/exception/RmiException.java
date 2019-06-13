package com.tomato.framework.plugin.rmi.exception;

import com.tomato.framework.core.common.ExceptionCodeConst;
import com.tomato.framework.core.exception.SysException;
import lombok.Getter;

public class RmiException extends SysException {
    
    @Getter
    private int errorCode = ExceptionCodeConst.RMI_CODE;
    
    public RmiException(String msg) {
        super(msg);
    }
    
    public RmiException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public RmiException(Throwable cause) {
        super(cause);
    }
}
