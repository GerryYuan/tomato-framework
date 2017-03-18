package com.tomato.framework.rest.result.page;

import com.tomato.framework.rest.result.ViewModelResult;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

public class PageResult<T> implements Serializable {

	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = -6722719642632697152L;

	// 根据页面显示的条数计算总页数
	@Getter
	@Setter
	private int page;

	@Getter
	@Setter
	private int pageSize;

	// 根据传入的数据库查询数据库中的信息的条数
	@Getter
	@Setter
	private int total;

	// 返回结果集
	@Getter
	@Setter
	private List<T> rows;

	@Getter
	@Setter
	private boolean hasNext;

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
