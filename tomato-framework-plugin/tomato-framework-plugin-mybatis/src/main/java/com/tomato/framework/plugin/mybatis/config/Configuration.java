package com.tomato.framework.plugin.mybatis.config;

import com.tomato.framework.plugin.mybatis.statement.MappedStatement;
import java.util.Map;
import javax.sql.DataSource;
import lombok.Data;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:52
 */
@Data
public class Configuration {
    
    private DataSource dataSource;
    
    private Map<String, MappedStatement> statement;
    
}
