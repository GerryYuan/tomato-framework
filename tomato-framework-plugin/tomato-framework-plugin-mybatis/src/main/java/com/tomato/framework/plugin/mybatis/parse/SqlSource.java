package com.tomato.framework.plugin.mybatis.parse;

import com.tomato.framework.plugin.mybatis.config.Configuration;

public class SqlSource {
    
    private Configuration configuration;
    
    private String sqlText;
    
    public SqlSource(Configuration configuration, String sqlText) {
        this.configuration = configuration;
        this.sqlText = sqlText;
    }
    
    //处理sql占位符
    public BoundSql getBoundSql() {
        //sqlText-> select * from user where id = #{id}
        ParamMappingSqlHandler sqlHandler = new ParamMappingSqlHandler();
        GenericSqlParser genericSqlParser = new GenericSqlParser("#{", "}", sqlHandler);
        //sql-> select * form user where id = ?
        String sql = genericSqlParser.parse(sqlText);
        BoundSql boundSql = new BoundSql(sql, sqlHandler.getParamMappings());
        return boundSql;
    }
}
