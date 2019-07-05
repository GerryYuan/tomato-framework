package com.tomato.framework.plugin.mybatis.parse;

public interface SqlSource {
    
    BoundSql getBoundSql(Object paramObj);
}
