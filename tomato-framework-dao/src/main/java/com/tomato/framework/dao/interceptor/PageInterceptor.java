package com.tomato.framework.dao.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.jfaster.mango.operator.Interceptor;
import org.jfaster.mango.operator.PreparedSql;
import org.jfaster.mango.reflect.Parameter;
import org.jfaster.mango.util.SQLType;

import java.util.List;

/**
 * 分页拦截器
 * 
 *
 * @author gerry
 * @version  1.0, 2016年9月29日下午6:44:46
 */
@Slf4j
public class PageInterceptor implements Interceptor {

	@Override
	public void intercept(PreparedSql preparedSql, List<Parameter> parameters, SQLType sqlType) {
		log.info(preparedSql.getSql());
	}

}
