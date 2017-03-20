package com.tomato.framework.demo.service;


import com.tomato.framework.core.page.Pagination;
import com.tomato.framework.demo.model.DemoMango;

import java.util.List;

public interface DemoService {

	List<DemoMango> getMangos(short status, Pagination pagination);

	DemoMango get(Integer id);

	boolean delete(Integer id);

	boolean add(DemoMango demoMango);

	boolean update(DemoMango demoMango);

}
