package com.tomato.framework.plugin.mybatis.sqlsession;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:34
 */
public interface SqlSessionFactory {
    
    SqlSession openSqlSession();
    
}
