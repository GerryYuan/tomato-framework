package com.tomato.framework.plugin.mybatis.sqlsession;

import java.util.List;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:34
 */
public interface SqlSession {
    
    <T> T selectOne(String statementId, Object param);
    
    <T> List<T> selectList(String statementId, Object param);
}
