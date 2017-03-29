package com.tomato.framework.demo.controller;

import com.tomato.framework.dao.page.Pagination;
import com.tomato.framework.demo.bean.PageResult;
import com.tomato.framework.demo.model.DemoMango;
import com.tomato.framework.demo.service.DemoService;
import com.tomato.framework.plugin.cache.ops.MultiCacheManager;
import com.tomato.framework.rest.helper.ViewModelHelper;
import com.tomato.framework.rest.result.ViewModelResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
        pagination.setResults(demoService.getMangos(status, pagination));
        return ViewModelHelper.OKViewModelResult(pagination);
    }

    @RequestMapping("/user/list")
    public ViewModelResult<?> list(Pagination page) {
        page.setResults(demoService.list(page));
        return ViewModelHelper.OKViewModelResult(page);
    }


//    /**
//     * @param rows 每页条数
//     * @param page 当前页码
//     * @return
//     */
//    @ResponseBody
//    @RequestMapping(value = "/user/list", method = RequestMethod.POST)
//    public PageResult list(int rows, int page) {
//        System.out.println(rows + "................................................" + page);
//        int records = 100;
//
//        int total = (int) Math.ceil(records / rows);
//        PageResult pr = new PageResult();
//        pr.setPage(page);
//        pr.setTotal(total);//计算
//        pr.setRecords(records);
//
//        List<DemoMango> ps = new ArrayList<>();
//        int csize = rows * (page - 1) + 1;
//        int lastsize = csize + rows;
//        for (int i = csize; i < lastsize; i++) {
//            DemoMango p = new DemoMango();
//            p.setName("zhang|" + i + "|" + page);
//            Short s = 1;
//            p.setStatus(s);
//            ps.add(p);
//        }
//
//        pr.setRows(ps);
//
//        return pr;
//    }

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
