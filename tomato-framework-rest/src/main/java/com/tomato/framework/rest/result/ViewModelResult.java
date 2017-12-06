package com.tomato.framework.rest.result;

import com.tomato.framework.core.util.EmptyUtils;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

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

	protected ViewModelResult() {
		super.setMessage("执行成功");
	}

	protected ViewModelResult(String... message) {
		super.setSuccess(-1);
		if (EmptyUtils.isEmpty(message)) {
			super.setMessage("执行失败");
		} else {
			super.setMessage(message[0]);
		}
	}

	protected ViewModelResult(int status, String... message) {
		super.setSuccess(status);
		if (EmptyUtils.isEmpty(message)) {
			super.setMessage("执行失败");
		} else {
			super.setMessage(message[0]);
		}
	}

	protected ViewModelResult(T result) {
		super.setMessage("执行成功");
		super.setData(result);
	}

	protected ViewModelResult(T result, int status, String... message) {
		super.setSuccess(status);
		if (EmptyUtils.isEmpty(message)) {
			super.setMessage("执行失败");
		} else {
			super.setMessage(message[0]);
		}
		super.setData(result);
	}

	public static <T> ViewModelResult<T> no(T result, int status, String message) {
		return new ViewModelResult<T>(result, status, message);
	}

	public static <T> ViewModelResult<T> no(String... message) {
		return new ViewModelResult<T>(message);
	}

	public static <T> ViewModelResult<T> no(int status, String... message) {
		return new ViewModelResult<T>(status, message);
	}

	public static <T> ViewModelResult<T> ok(T result) {
		return new ViewModelResult<T>(result);
	}

	public static <T> ViewModelResult<T> ok() {
		return new ViewModelResult<T>();
	}

	public static <T> Result<T> toResult(T result) {
		return new Result<T>(result);
	}

}
