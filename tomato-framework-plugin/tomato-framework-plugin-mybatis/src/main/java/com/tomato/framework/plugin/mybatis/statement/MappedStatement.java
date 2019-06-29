package com.tomato.framework.plugin.mybatis.statement;

import lombok.Data;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-15:20
 */
@Data
public class MappedStatement {
    
    private String id;
    
    private String sql;
    
    private String parameterType;
    
    private String resultType;
    
    private String statementType;
    
}
