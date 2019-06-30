package com.tomato.framework.plugin.mybatis.sqlsession;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import com.tomato.framework.plugin.mybatis.parse.GenericSqlParser;
import com.tomato.framework.plugin.mybatis.parse.ParamMappingSqlHandler;
import com.tomato.framework.plugin.mybatis.statement.MappedStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
        MappedStatement mappedStatement = configuration.getMappedStatement(statementId);
        String sql = mappedStatement.getSql();
        //处理sql占位符
        ParamMappingSqlHandler sqlHandler = new ParamMappingSqlHandler();
        GenericSqlParser genericSqlParser = new GenericSqlParser("#{", "}", sqlHandler);
        //select * from user where id = #{id} -> select * from user where id = ?
        String newSql = genericSqlParser.parse(sql);
        //反射处理param，从ParamMapping中获取对应的变量名称
        //1、如果是param类型是String类型或基本类型或包装类，则，直接赋值
        //2、如果是一个Map类型，则直接从Map中查找对应的ParamMapping中的参数值
        //3、如果是一个List类型，则直接迭代List.get(x)，进行赋值
        //4、如果是一个Object类型，则需要反射，根据ParamMapping中的参数名，然后获取Object参数值
        try {
            PreparedStatement preparedStatement = configuration.getDataSource().getConnection()
                .prepareStatement(newSql);
            for (int i = 1; i <= sqlHandler.getVars().size(); i++) {
                if (param instanceof Integer) {
                    preparedStatement.setInt(i, (Integer) param);
                }
            }
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString(1));
                System.out.println(resultSet.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //TODO 通过执行器，执行sql
        //TODO 处理结果集
        return null;
    }
    
    @Override
    public <T> List<T> selectList(String statementId, Object param) {
        return null;
    }
}
