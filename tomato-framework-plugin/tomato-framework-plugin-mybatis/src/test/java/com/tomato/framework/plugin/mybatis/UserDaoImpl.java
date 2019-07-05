package com.tomato.framework.plugin.mybatis;

import com.tomato.framework.plugin.mybatis.sqlsession.SqlSession;
import com.tomato.framework.plugin.mybatis.sqlsession.SqlSessionFactory;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:25
 */
public class UserDaoImpl implements UserDao {
    
    private SqlSessionFactory sqlSessionFactory;
    
    public UserDaoImpl(SqlSessionFactory sqlSessionFactory) {
        this.sqlSessionFactory = sqlSessionFactory;
    }
    
    @Override
    public User findUserById(Integer id) {
        SqlSession sqlSession = sqlSessionFactory.openSqlSession();
        String statementId = "com.tomato.framework.plugin.mybatis.UserDao.findUserById";
        return sqlSession.selectOne(statementId, id);
    }
}
