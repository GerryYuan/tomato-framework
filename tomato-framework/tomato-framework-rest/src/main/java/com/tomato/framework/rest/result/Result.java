package com.tomato.framework.rest.result;

import java.io.Serializable;

import lombok.Data;

/**
 * 输出客户端实体类
 * 
 *
 * @author yuanguohua
 * @version  1.0 2015年8月6日
 * @since   com.tomato
 */
@Data
public class Result<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 8886624063083045314L;

	public Result() {
	
	}
	
	public Result(T data) {
		this.setData(data);
	}
	
	private int success;
	
	private String message;
	
	private T data;

}
