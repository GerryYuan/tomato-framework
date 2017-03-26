package com.tomato.framework.demo.controller;

import com.tomato.framework.dao.page.Pagination;
import com.tomato.framework.demo.model.DemoMango;
import com.tomato.framework.demo.service.DemoService;
import com.tomato.framework.plugin.cache.common.CacheTimeoutConst;
import com.tomato.framework.plugin.cache.ops.RemoteCacheManager;
import com.tomato.framework.rest.helper.ViewModelHelper;
import com.tomato.framework.rest.result.ViewModelResult;
import org.jfaster.mango.plugin.page.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping
public class DemoController {

    @Autowired
    private DemoService demoService;

    @Autowired
    private RemoteCacheManager<DemoMango> remoteCacheManager;

    @RequestMapping("/get/{id}")
    public ViewModelResult<?> get(@PathVariable(name = "id") Integer id) {
        return ViewModelHelper.OKViewModelResult(remoteCacheManager.vget(id + "", s -> {
            return demoService.get(id);
        }, CacheTimeoutConst.HALF_AN_HOUR));
    }

    @RequestMapping("/get/page/{status}")
    public ViewModelResult<?> get(Page page, @PathVariable(name = "status") short status) {
        return ViewModelHelper.OKViewModelResult(Pagination.build(demoService.getMangos(status, page), page));
    }


    @RequestMapping("/add/{name}")
    public ViewModelResult<?> add(@PathVariable(name = "name") String name) {
        DemoMango demoMango = new DemoMango();
        demoMango.setName(name + new Date().getTime());
        return ViewModelHelper.OKViewModelResult(demoService.add(demoMango));
    }

    @RequestMapping("/del/{id}")
    public ViewModelResult<?> del(@PathVariable(name = "id") Integer id) {
        return ViewModelHelper.OKViewModelResult(demoService.delete(id));
    }

    @RequestMapping("/update/{id}/{name}")
    public ViewModelResult<?> update(@PathVariable(name = "id") Integer id, @PathVariable(name = "name") String name) {
        DemoMango demoMango = new DemoMango();
        demoMango.setId(id);
        demoMango.setName(name + new Date().getTime());
        return ViewModelHelper.OKViewModelResult(demoService.update(demoMango));
    }
}
