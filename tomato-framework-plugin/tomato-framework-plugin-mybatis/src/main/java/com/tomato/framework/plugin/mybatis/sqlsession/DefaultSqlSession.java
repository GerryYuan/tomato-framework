package com.tomato.framework.plugin.mybatis.sqlsession;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import com.tomato.framework.plugin.mybatis.excutor.SimpleSqlExcutor;
import com.tomato.framework.plugin.mybatis.excutor.SqlExcutor;
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
        List<T> results = selectList(statementId, param);
        if (results != null && results.size() == 1) {
            return results.get(0);
        }
        return null;
    }
    
    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        SqlExcutor sqlExcutor = new SimpleSqlExcutor(configuration, configuration.getMappedStatement(statementId));
        return sqlExcutor.selectList(param);
    }
}
