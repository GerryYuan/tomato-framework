package com.tomato.framework.plugin.mybatis.excutor;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import com.tomato.framework.plugin.mybatis.parse.BoundSql;
import com.tomato.framework.plugin.mybatis.statement.MappedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class SimpleSqlExcutor implements SqlExcutor {
    
    private Configuration configuration;
    
    private MappedStatement mappedStatement;
    
    public SimpleSqlExcutor(Configuration configuration,
        MappedStatement mappedStatement) {
        this.configuration = configuration;
        this.mappedStatement = mappedStatement;
    }
    
    @Override
    public <T> List<T> selectList(Object param) {
        BoundSql boundSql = mappedStatement.getSqlSource().getBoundSql(param);
        Statement statement = null;
        Class<?> paramterTypeClass = mappedStatement.getParamterTypeClass();
        try {
            if ("prepared".equals(mappedStatement.getStatementType())) {
                statement = configuration.getDataSource().getConnection()
                    .prepareStatement(boundSql.getSql());
                PreparedStatement preparedStatement = (PreparedStatement) statement;
                if (paramterTypeClass == Integer.class) {
                    preparedStatement.setInt(1, (Integer) param);
                } else {
                    for (int i = 1; i <= boundSql.getParamMappings().size(); i++) {
                        if (param instanceof Integer) {
                            preparedStatement.setInt(i, (Integer) param);
                        }
                    }
                }
                ResultSet resultSet = preparedStatement.executeQuery();
                while (resultSet.next()) {
                    System.out.println(resultSet.getString(1));
                    System.out.println(resultSet.getString(2));
                }
            } else {
                //其他statement
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        //TODO 通过执行器，执行sql
        //TODO 处理结果集
        return null;
    }
    
}
