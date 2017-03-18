package com.tomato.framework.demo.service;


import com.tomato.framework.demo.model.DemoMango;

public interface DemoService {

	DemoMango get(Integer id);

	boolean delete(Integer id);

	boolean add(DemoMango demoMango);

	boolean update(DemoMango demoMango);
}
