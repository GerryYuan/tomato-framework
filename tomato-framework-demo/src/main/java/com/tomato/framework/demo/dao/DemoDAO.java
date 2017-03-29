package com.tomato.framework.demo.dao;

import com.tomato.framework.dao.page.Pagination;
import com.tomato.framework.demo.model.DemoMango;
import org.jfaster.mango.annotation.DB;
import org.jfaster.mango.annotation.SQL;

import java.util.List;

@DB(name = "tomato_framework", table = "tomato_framework_test")
public interface DemoDAO {
	String COLUMNS = "id, name, status";

	@SQL("insert into #table(" + COLUMNS + ") values(:id, :name)")
	boolean addDemoMango(DemoMango demoMango);

	@SQL("select " + COLUMNS + " from #table where id = :1")
	DemoMango getDemoMango(int id);

	@SQL("select " + COLUMNS + " from #table where 1 = 1")
	List<DemoMango> list(Pagination pagination);

	@SQL("update #table vset name=:name where id = :id")
	boolean updateDemoMango(DemoMango demoMango);

	@SQL("delete from #table where id = :1")
	boolean deleteDemoMango(int id);

	@SQL("select " + COLUMNS + " from #table where status = :1")
	List<DemoMango> getDemoMangos(short status, Pagination pagination);
}
