package com.tomato.framework.demo.controller;

import com.tomato.framework.dao.page.Pagination;
import com.tomato.framework.demo.model.DemoMango;
import com.tomato.framework.demo.service.DemoService;
import com.tomato.framework.plugin.cache.ops.MultiCacheManager;
import com.tomato.framework.rest.helper.ViewModelHelper;
import com.tomato.framework.rest.result.ViewModelResult;
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

    @RequestMapping("/get/{id}")
    public ViewModelResult<?> get(@PathVariable(name = "id") Integer id) {
        return ViewModelHelper.OKViewModelResult(MultiCacheManager.getInstance().vget(id + "", s -> {
            return demoService.get(id);
        }, 120, 120 * 2));
//        return ViewModelHelper.OKViewModelResult(RemoteCacheManager.getInstance().vget(id + "", s -> {
//            return demoService.get(id);
//        }, CacheTimeoutConst.HALF_AN_HOUR));
//        return ViewModelHelper.OKViewModelResult(RemoteCacheManager.getInstance().vget(id + "", s -> {
//            return demoService.getMangos((short) 1, new Page(1, 100));
//        }, CacheTimeoutConst.HALF_AN_HOUR));
    }

    @RequestMapping("/get/page/{status}")
    public ViewModelResult<?> get(Pagination pagination, @PathVariable(name = "status") short status) {
        pagination.setRows(demoService.getMangos(status, pagination));
        return ViewModelHelper.OKViewModelResult(pagination);
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
