package com.tomato.framework.dao.page;

import lombok.Data;
import org.jfaster.mango.plugin.page.Page;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询条件
 * 
 *
 * @author yuanguohua
 * @version 1.0 2015年8月28日
 * @since com.yeshj
 */
@Data
public class Pagination<T> implements Serializable {

	private int pageNum;

	private int pageSize;

	private int total;

	// 返回结果集
	private List<T> rows;

	private String sort;

	private String order;

	public static <T> Pagination<T> build(List<T> rows, Page page){
		Pagination<T> pagination = new Pagination<T>();
		BeanUtils.copyProperties(page, pagination);
		pagination.setRows(rows);
		return pagination;
	}

}
