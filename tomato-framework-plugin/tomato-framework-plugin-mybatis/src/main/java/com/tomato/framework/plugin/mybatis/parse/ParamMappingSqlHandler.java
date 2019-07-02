package com.tomato.framework.plugin.mybatis.parse;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-30-19:33
 */
public class ParamMappingSqlHandler implements SqlHandler {
    
    @Getter
    private List<ParamMapping> paramMappings = new ArrayList<>();
    
    @Override
    public String handleSql(String content) {
        paramMappings.add(buildParamMapping(content));
        return "?";
    }
    
    private ParamMapping buildParamMapping(String content) {
        ParamMapping paramMapping = new ParamMapping();
        paramMapping.setContent(content);
        return paramMapping;
    }
    
}
