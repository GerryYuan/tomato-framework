package com.tomato.framework.plugin.pagehelper.pager;

import com.tomato.framework.core.page.Pagination;
import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.plugin.pagehelper.binding.BindingSQL;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-20-22:20
 * @since com.hujiang 1.0.0
 */
public enum OraclePager implements DBPager {

    INSTANCE;

    @Override
    public void parse(Pagination pagination, BindingSQL bindingSQL) {
        String sql = bindingSQL.getSql();
        int page = pagination.getPageNo();
        int rows = pagination.getLimit();
        if(EmptyUtils.isNotEmpty(pagination.getSort())){
            sql = sql + " order by " + pagination.getSort();
            if(EmptyUtils.isEmpty(pagination.getOrder())){
                sql = sql + " " + pagination.getOrder();
            }
        }
        String pageSql = "SELECT * FROM ( SELECT B.* , ROWNUM RN FROM (" + sql + ") B WHERE ROWNUM <= "
                + (page * rows) + " ) WHERE RN > " + (page - 1) * rows;
        bindingSQL.setSql(pageSql);
    }
}
