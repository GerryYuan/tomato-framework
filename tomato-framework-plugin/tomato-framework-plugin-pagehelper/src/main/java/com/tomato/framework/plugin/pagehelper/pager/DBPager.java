package com.tomato.framework.plugin.pagehelper.pager;

import com.tomato.framework.core.page.Pagination;
import com.tomato.framework.plugin.pagehelper.binding.BindingSQL;

/**
 * @author Created by gerry
 * @version 1.0, 2017-03-20-22:18
 * @since com.hujiang 1.0.0
 */
public interface DBPager {

    void parse(Pagination pagination, BindingSQL bindingSQL);

}
