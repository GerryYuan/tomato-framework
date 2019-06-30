package com.tomato.framework.plugin.mybatis.sqlsession;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import com.tomato.framework.plugin.mybatis.statement.MappedStatement;
import java.util.List;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-22:05
 */
public class DefaultSqlSession implements SqlSession {
    
    private Configuration configuration;
    
    public DefaultSqlSession(Configuration configuration) {
        this.configuration = configuration;
    }
    
    @Override
    public <T> T selectOne(String statementId, Object param) {
        MappedStatement mappedStatement = configuration.getStatement().get(statementId);
        String sql = mappedStatement.getSql();
        //反射处理
        //TODO 处理sql占位符
        //TODO 处理结果集
        return null;
    }
    
    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        return null;
    }
}
