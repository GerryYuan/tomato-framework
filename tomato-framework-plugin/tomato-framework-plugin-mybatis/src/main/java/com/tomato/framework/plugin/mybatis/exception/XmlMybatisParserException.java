package com.tomato.framework.plugin.mybatis.exception;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-15:41
 */
public class XmlMybatisParserException extends RuntimeException{
    
    public XmlMybatisParserException(String msg) {
        super(msg);
    }
    
    public XmlMybatisParserException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public XmlMybatisParserException(Throwable cause) {
        super(cause);
    }
    
}
