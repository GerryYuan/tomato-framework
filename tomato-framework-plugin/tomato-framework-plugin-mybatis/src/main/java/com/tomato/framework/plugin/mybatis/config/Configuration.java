package com.tomato.framework.plugin.mybatis.config;

import com.tomato.framework.plugin.mybatis.statement.MappedStatement;
import java.util.HashMap;
import java.util.Map;
import javax.sql.DataSource;
import lombok.Getter;
import lombok.Setter;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:52
 */
public class Configuration {
    
    @Setter
    @Getter
    private DataSource dataSource;
    
    //这里的map中的key一定要唯一
    private Map<String, MappedStatement> statement = new HashMap<>();
    
    public void addMappedStatement(Map<String, MappedStatement> statement) {
        this.statement.putAll(statement);
    }
    
    public MappedStatement getMappedStatement(String id) {
        return this.statement.get(id);
    }
}
