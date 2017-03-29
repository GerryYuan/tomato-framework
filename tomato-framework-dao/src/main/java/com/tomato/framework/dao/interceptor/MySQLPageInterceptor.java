package com.tomato.framework.dao.interceptor;

import org.jfaster.mango.binding.BoundSql;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-28-21:55
 */
public class MySQLPageInterceptor extends AbstractPageInterceptor {
    @Override
    void handleTotal(BoundSql boundSql) {
        String sql = boundSql.getSql();
        sql = "SELECT COUNT(1) FROM (" + sql + ") aliasForPage";
        boundSql.setSql(sql);
    }

    @Override
    void handlePage(int pageNum, int pageSize, BoundSql boundSql) {
        pageNum = pageNum == 0 ? 1 : pageNum;
        int startRow = (pageNum - 1) * pageSize;
        String sql = boundSql.getSql();
        sql = sql + " limit ?, ?";
        boundSql.setSql(sql);
        boundSql.addArg(startRow);
        boundSql.addArg(pageSize);
    }
}
