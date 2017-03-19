package com.tomato.framework.core.exception;

import com.tomato.framework.core.common.ExceptionCodeConst;
import lombok.Getter;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-19-18:45
 * @since com.hujiang 1.0.0
 */
public class DataSourceException extends RuntimeException{

    @Getter
    private int errorCode = ExceptionCodeConst.DATASOURCE_EXCEPTION_CODE;

    public DataSourceException(String msg) {
        super(msg);
    }

    public DataSourceException(String msg, Throwable cause) {
        super(msg, cause);
    }


    public DataSourceException(Throwable cause) {
        super(cause);
    }

}
