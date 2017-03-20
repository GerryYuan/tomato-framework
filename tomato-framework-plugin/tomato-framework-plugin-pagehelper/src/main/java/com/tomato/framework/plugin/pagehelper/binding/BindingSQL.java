package com.tomato.framework.plugin.pagehelper.binding;

import com.google.common.collect.Lists;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-20-22:40
 * @since com.hujiang 1.0.0
 */
public class BindingSQL implements Serializable {

    @Getter
    @Setter
    private String sql;

    @Getter
    private List<Object> args;

    public BindingSQL(String sql) {
        this.sql = sql;
        this.args = Lists.newArrayList();
    }

    public BindingSQL(String sql, List<Object> args) {
        this.sql = sql;
        this.args = args;
    }
}
