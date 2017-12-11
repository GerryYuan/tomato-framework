package com.tomato.framework.dao.page;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 封装分页查询条件
 *
 * @author yuanguohua
 * @version 1.0 2015年8月28日
 * @since com.yeshj
 */
@Data
public class Pagination<T> implements Serializable {

    // 返回结果集
    private List<T> results;

    private String sidx;//根据字段排序

    private String sord;//正序 倒序

    private int page;//页码

    private int rows;//每页条数

    private int total;//总页数

    private int records;//总条数

}
