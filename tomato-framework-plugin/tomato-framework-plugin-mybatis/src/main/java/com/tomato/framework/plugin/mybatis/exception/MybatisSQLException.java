package com.tomato.framework.plugin.mybatis.exception;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-16:13
 */
public class MybatisSQLException extends RuntimeException {
    
    public MybatisSQLException(String msg) {
        super(msg);
    }
    
    public MybatisSQLException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public MybatisSQLException(Throwable cause) {
        super(cause);
    }
    
    
}
