package com.tomato.framework.dao.interceptor;

import com.google.common.collect.Lists;
import com.tomato.framework.core.page.Pagination;
import com.tomato.framework.plugin.pagehelper.binding.BindingSQL;
import com.tomato.framework.plugin.pagehelper.pager.MySQLPager;
import lombok.extern.slf4j.Slf4j;
import org.jfaster.mango.binding.BoundSql;
import org.jfaster.mango.interceptor.Parameter;
import org.jfaster.mango.interceptor.QueryInterceptor;

import java.util.List;

/**
 * 分页拦截器
 *
 * @author gerry
 * @version 1.0, 2016年9月29日下午6:44:46
 */
@Slf4j
public class PageInterceptor extends QueryInterceptor {

    @Override
    public void interceptQuery(BoundSql boundSql, List<Parameter> parameters) {
        for (Parameter parameter : parameters) {
            Object object = parameter.getValue();
            if (object instanceof Pagination) {
                Pagination pagination = (Pagination) object;
                BindingSQL bindingSQL = new BindingSQL(boundSql.getSql(), Lists.newArrayList());
                MySQLPager.INSTANCE.parse(pagination, bindingSQL);
                boundSql.setSql(bindingSQL.getSql());
                bindingSQL.getArgs().forEach(arg -> boundSql.addArg(arg));
                break;
            }
        }
        log.info("execute sql is -> {}, args -> {}", boundSql.getSql(), boundSql.getArgs());
    }
}
