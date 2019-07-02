package com.tomato.framework.plugin.mybatis.parse;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

public class BoundSql {
    
    public BoundSql(String sql, List<ParamMapping> paramMappings) {
        this.sql = sql;
        this.paramMappings = paramMappings;
    }
    
    @Getter
    @Setter
    private String sql;
    
    @Getter
    private List<ParamMapping> paramMappings = new ArrayList<>();
    
    public void addParamMapping(ParamMapping paramMapping) {
        this.paramMappings.add(paramMapping);
    }
}
