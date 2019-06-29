package com.tomato.framework.plugin.mybatis.sqlsession;

import com.tomato.framework.plugin.mybatis.config.Configuration;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:49
 */
public class DefaultSqlSessionFactory implements SqlSessionFactory {
    
    private Configuration configuration;
    
    public DefaultSqlSessionFactory(Configuration configuration) {
        this.configuration = configuration;
    }
    
    @Override
    public SqlSession openSqlSession() {
        return null;
    }
}
