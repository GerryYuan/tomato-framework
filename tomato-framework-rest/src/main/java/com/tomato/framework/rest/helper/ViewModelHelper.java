package com.tomato.framework.rest.helper;

import com.tomato.framework.rest.result.Result;
import com.tomato.framework.rest.result.ViewModelResult;

/**
 * 返回客户端实体帮助类
 * 
 * 
 * @author yuanguohua
 * @version 1.0 2015年8月6日
 * @since com.yeshj
 */
public class ViewModelHelper {

	public static <T> ViewModelResult<T> NOViewModelResult(T result,int status, String message) {
		return new ViewModelResult<T>(result, status, message);
	}

	public static <T> ViewModelResult<T> NOViewModelResult(String... message) {
		return new ViewModelResult<T>(message);
	}

	public static <T> ViewModelResult<T> NOViewModelResult(int status, String... message) {
		return new ViewModelResult<T>(status, message);
	}
	
	public static <T> ViewModelResult<T> OKViewModelResult(T result) {
		return new ViewModelResult<T>(result);
	}

	public static <T> ViewModelResult<T> OKViewModelResult() {
		return new ViewModelResult<T>();
	}

	public static <T> Result<T> toResult(T result) {
		return new Result<T>(result);
	}

}
