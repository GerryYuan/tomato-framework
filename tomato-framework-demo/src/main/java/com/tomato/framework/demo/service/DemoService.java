package com.tomato.framework.demo.service;


import com.tomato.framework.demo.model.DemoMango;
import org.jfaster.mango.plugin.page.Page;

import java.util.List;

public interface DemoService {

	List<DemoMango> getMangos(short status, Page page);

	DemoMango get(Integer id);

	boolean delete(Integer id);

	boolean add(DemoMango demoMango);

	boolean update(DemoMango demoMango);

}
