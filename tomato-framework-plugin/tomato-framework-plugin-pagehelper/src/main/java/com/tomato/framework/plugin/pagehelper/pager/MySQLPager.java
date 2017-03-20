package com.tomato.framework.plugin.pagehelper.pager;

import com.tomato.framework.core.page.Pagination;
import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.plugin.pagehelper.binding.BindingSQL;

import java.util.List;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-20-22:26
 * @since com.hujiang 1.0.0
 */
public enum MySQLPager implements DBPager {
    INSTANCE;

    @Override
    public void parse(Pagination pagination, BindingSQL bindingSQL) {
        String sql = bindingSQL.getSql();
        List<Object> args = bindingSQL.getArgs();
        if (EmptyUtils.isNotEmpty(pagination.getSort())) {
            sql = sql + " order by " + pagination.getSort();
            if (EmptyUtils.isNotEmpty(pagination.getOrder())) {
                sql = sql + " " + pagination.getOrder();
            }
        }
        sql = sql + " limit ?, ?";
        bindingSQL.setSql(sql);
        args.add(pagination.getPageNo());
        args.add(pagination.getLimit());
    }
}
