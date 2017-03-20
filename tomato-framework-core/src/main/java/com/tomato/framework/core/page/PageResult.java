package com.tomato.framework.core.page;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
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

}
