package com.tomato.framework.rest.result.page;

import java.io.Serializable;

/**
 * 封装分页查询条件
 * 
 *
 * @author yuanguohua
 * @version 1.0 2015年8月28日
 * @since com.yeshj
 */
public class Pagination<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 9145184175412861272L;

	// 当前页
	private int currentPage = 1;
	// 页面要显示信息条数
	private int pageSize = 10;

	private int page;

	private int rows;

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
		if (page > 0) {
			this.currentPage = page;
		}
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
		if (rows > 0) {
			this.pageSize = rows;
		}
	}

	private T condition;

	public int getCurrentResult() {
		return (this.currentPage - 1) * this.pageSize;
	}

	public T getCondition() {
		return condition;
	}

	public void setCondition(T condition) {
		this.condition = condition;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

}
