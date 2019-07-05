package com.tomato.framework.plugin.mybatis.parse;

import com.tomato.framework.plugin.mybatis.config.Configuration;

public class StaticSqlSource implements SqlSource {
    
    private Configuration configuration;
    
    private String sqlText;
    
    public StaticSqlSource(Configuration configuration, String sqlText) {
        this.configuration = configuration;
        this.sqlText = sqlText;
    }
    
    @Override
    public BoundSql getBoundSql(Object paramObj) {
        //sqlText-> select * from user where id = #{id}
        ParamMappingSqlHandler sqlHandler = new ParamMappingSqlHandler();
        GenericSqlParser genericSqlParser = new GenericSqlParser("#{", "}", sqlHandler);
        //sql-> select * form user where id = ?
        String sql = genericSqlParser.parse(sqlText);
        BoundSql boundSql = new BoundSql(sql, paramObj, sqlHandler.getParamMappings());
        return boundSql;
    }
}
