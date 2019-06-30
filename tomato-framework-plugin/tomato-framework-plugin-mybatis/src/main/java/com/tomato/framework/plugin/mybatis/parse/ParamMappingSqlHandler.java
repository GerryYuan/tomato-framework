package com.tomato.framework.plugin.mybatis.parse;

import com.google.common.collect.Lists;
import java.util.List;
import lombok.Getter;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-30-19:33
 */
public class ParamMappingSqlHandler implements SqlHandler {
    
    @Getter
    private List<String> vars = Lists.newArrayList();
    
    @Override
    public String handleSql(String content) {
        vars.add(content);
        return "?";
    }
    
}
