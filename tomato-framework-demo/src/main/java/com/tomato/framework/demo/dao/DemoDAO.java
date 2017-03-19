package com.tomato.framework.demo.dao;

import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;

import com.tomato.framework.demo.model.DemoMango;

@DB(name = "tomato_framework", table = "tomato_framework_test")
public interface DemoDAO {
	String COLUMNS = "id, name";

	@SQL("insert into #table(" + COLUMNS + ") values(:id, :name)")
	boolean addDemoMango(DemoMango demoMango);

	@SQL("select " + COLUMNS + " from #table where id = :1")
	DemoMango getDemoMango(int id);

	@SQL("update #table set name=:name where id = :id")
	boolean updateDemoMango(DemoMango demoMango);

	@SQL("delete from #table where id = :1")
	boolean deleteDemoMango(int id);
}
