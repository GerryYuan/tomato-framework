package com.tomato.framework.core.exception;

import com.tomato.framework.core.common.ExceptionCodeConst;
import lombok.Getter;

/**
 * Created by gerry
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
