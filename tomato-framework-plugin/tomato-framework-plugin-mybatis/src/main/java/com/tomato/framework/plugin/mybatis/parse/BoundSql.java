package com.tomato.framework.plugin.mybatis.parse;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class BoundSql {
    
    @Getter
    @Setter
    private String sql;
    
    private Object paramObj;
    
    public BoundSql(String sql, Object paramObj,
        List<ParamMapping> paramMappings) {
        this.sql = sql;
        this.paramObj = paramObj;
        this.paramMappings = paramMappings;
    }
    
    @Getter
    private List<ParamMapping> paramMappings = new ArrayList<>();
    
    public void addParamMapping(ParamMapping paramMapping) {
        this.paramMappings.add(paramMapping);
    }
}
