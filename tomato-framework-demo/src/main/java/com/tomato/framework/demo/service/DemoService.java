package com.tomato.framework.demo.service;


import com.tomato.framework.dao.page.Pagination;
import com.tomato.framework.demo.model.DemoMango;

import java.util.List;

public interface DemoService {

	List<DemoMango> getMangos(short status, Pagination<DemoMango> page);

	List<DemoMango> list(Pagination<DemoMango> page);

	DemoMango get(Integer id);

	boolean delete(Integer id);

	boolean add(DemoMango demoMango);

	boolean update(DemoMango demoMango);
}
