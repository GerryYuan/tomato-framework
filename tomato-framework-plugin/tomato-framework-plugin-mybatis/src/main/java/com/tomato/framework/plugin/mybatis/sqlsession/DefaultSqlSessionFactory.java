package com.tomato.framework.plugin.mybatis.sqlsession;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import com.tomato.framework.plugin.mybatis.statement.MappedStatement;
import java.util.List;

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
        //TODO 通过数据源操作数据
        return new SqlSession() {
           
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
        };
    }
}
