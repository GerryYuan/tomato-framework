package com.tomato.framework.plugin.mybatis.statement;

import com.tomato.framework.plugin.mybatis.parse.BoundSql;
import lombok.Builder;
import lombok.Data;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-15:20
 */
@Data
@Builder
public class MappedStatement {
    
    private String id;
    
    private BoundSql boundSql;
    
    private String parameterType;
    
    private Class<?> paramterTypeClass;
    
    private String resultType;
    
    private String statementType;
    
}
