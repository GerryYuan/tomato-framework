package com.tomato.framework.rest.result;

import com.tomato.framework.core.util.EmptyUtils;

/**
 * 返回客户端结构实体映射
 * 
 * 
 * @author yuanguohua
 * @version 1.0 2015年8月6日
 * @since com.tomato
 */
public class ViewModelResult<T> extends Result<T> {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -2751613606306765518L;

	public ViewModelResult() {
		super.setMessage("执行成功");
	}

	public ViewModelResult(String... message) {
		super.setSuccess(-1);
		if (EmptyUtils.isEmpty(message)) {
			super.setMessage("执行失败");
		} else {
			super.setMessage(message[0]);
		}
	}

	public ViewModelResult(int status, String... message) {
		super.setSuccess(status);
		if (EmptyUtils.isEmpty(message)) {
			super.setMessage("执行失败");
		} else {
			super.setMessage(message[0]);
		}
	}

	public ViewModelResult(T result) {
		super.setMessage("执行成功");
		super.setData(result);
	}

	public ViewModelResult(T result, int status, String... message) {
		super.setSuccess(status);
		if (EmptyUtils.isEmpty(message)) {
			super.setMessage("执行失败");
		} else {
			super.setMessage(message[0]);
		}
		super.setData(result);
	}

}
