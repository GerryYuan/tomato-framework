package com.tomato.framework.core.exception;

import java.text.MessageFormat;

/**
 * Created by gerry
 */
public class UtilException extends RuntimeException {

    private static final long serialVersionUID = 8247610319171014183L;

    public UtilException(Throwable e) {
        super(e);
    }

    public UtilException(String message) {
        super(message);
    }

    public UtilException(String messageTemplate, Object... params) {
        super(MessageFormat.format(messageTemplate, params));
    }

    public UtilException(String message, Throwable throwable) {
        super(message, throwable);
    }

    public UtilException(Throwable throwable, String messageTemplate, Object... params) {
        super(MessageFormat.format(messageTemplate, params), throwable);
    }

}
