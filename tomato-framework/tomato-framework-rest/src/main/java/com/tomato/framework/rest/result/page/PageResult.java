package com.tomato.framework.rest.result.page;

import java.io.Serializable;
import java.util.List;

import com.tomato.framework.rest.result.ViewModelResult;

public class PageResult<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6722719642632697152L;

	// 根据页面显示的条数计算总页数
	private int page;

	private int pageSize;

	// 根据传入的数据库查询数据库中的信息的条数
	private int total;

	// 返回结果集
	private List<T> rows;

	private boolean hasNext;

	public boolean isHasNext() {
		return hasNext;
	}

	public void setHasNext(boolean hasNext) {
		this.hasNext = hasNext;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public List<T> getRows() {
		return rows;
	}

	public void setRows(List<T> rows) {
		this.rows = rows;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public static <T, E> PageResult<T> buildPageResult(List<T> result, int rows, Pagination<E> pagination) {
		PageResult<T> pageResult = new PageResult<T>();
		pageResult.setRows(result);
		pageResult.setTotal(rows);
		pageResult.setPage(pagination.getPage());
		pageResult.setPageSize(pagination.getPageSize());
		pageResult.setHasNext((rows + pagination.getPageSize() - 1) / pagination.getPageSize() > pagination.getPage());
		return pageResult;
	}

	public static <T> ViewModelResult<T> toViewModelResult(T result) {
		return new ViewModelResult<T>(result);
	}
}
