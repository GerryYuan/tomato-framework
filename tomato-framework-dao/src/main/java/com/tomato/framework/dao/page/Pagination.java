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

    @JSONField(serialize = false)
    private boolean isFetchTotal = true;

    private int offset = 1;

    private int limit = 20;

    private int total;

    // 返回结果集
    private List<T> rows;

    private String sort;

    private String order;

}
