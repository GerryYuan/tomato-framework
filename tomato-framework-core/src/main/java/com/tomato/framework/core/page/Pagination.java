package com.tomato.framework.core.page;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 封装分页查询条件
 * 
 *
 * @author yuanguohua
 * @version 1.0 2015年8月28日
 * @since com.yeshj
 */
public class Pagination implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9145184175412861272L;

	@Getter
	private int pageNo = 1;

	public void setPageNo(int pageNo) {
		pageNo = pageNo <= 0 ? 1 : pageNo;
		this.pageNo = pageNo;
	}

	@Getter
	private int limit = 20;

	public void setLimit(int limit) {
		limit = limit <= 0 ? 1 : limit;
		this.limit = limit;
	}

	@Getter
	@Setter
	private int rows;

	@Getter
	@Setter
	private String sort;

	@Getter
	@Setter
	private String order;

}
