package com.tomato.framework.plugin.mybatis;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:25
 */
public interface UserDao {
    
    User findUserById(Integer id);
}
