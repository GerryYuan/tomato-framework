package com.tomato.framework.rest.result;

import com.tomato.framework.core.util.EmptyUtils;
import java.io.Serializable;

import lombok.Data;

/**
 * 输出客户端实体类
 *
 * @author yuanguohua
 * @version 1.0 2015年8月6日
 * @since com.tomato
 */
@Data
public class Result<T> implements Serializable {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 8886624063083045314L;

    private int success;

    private String message;

    private T data;
    
    protected Result() {
        this.setMessage("执行成功");
    }
    
    protected Result(String message) {
        this.setSuccess(-1);
        if (EmptyUtils.isEmpty(message)) {
            this.setMessage("执行失败");
        } else {
            this.setMessage(message);
        }
    }
    
    protected Result(int status, String message) {
        this.success = status;
        if (EmptyUtils.isEmpty(message)) {
            this.message = "执行失败";
        } else {
            this.message = message;
        }
    }
    
    protected Result(T result) {
        this.setMessage("执行成功");
        this.setData(result);
    }
    
    protected Result(T result, int status, String message) {
        this.setSuccess(status);
        if (EmptyUtils.isEmpty(message)) {
            this.setMessage("执行失败");
        } else {
            this.setMessage(message);
        }
        this.setData(result);
    }
    
    public static <T> Result<T> no(T result, int status, String message) {
        return new Result<T>(result, status, message);
    }
    
    public static <T> Result<T> no(String message) {
        return new Result<T>(message);
    }
    
    public static <T> Result<T> no(int status, String message) {
        return new Result<T>(status, message);
    }
    
    public static <T> Result<T> ok(T result) {
        return new Result<T>(result);
    }
    
    public static <T> Result<T> ok() {
        return new Result<T>();
    }
    
    public static <T> Result<T> toResult(T result) {
        return new Result<T>(result);
    }
}
