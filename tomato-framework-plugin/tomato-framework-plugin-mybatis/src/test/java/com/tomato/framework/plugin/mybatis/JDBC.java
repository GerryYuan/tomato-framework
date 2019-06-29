package com.tomato.framework.plugin.mybatis;

import com.tomato.framework.plugin.mybatis.sqlsession.SqlSessionFactory;
import com.tomato.framework.plugin.mybatis.sqlsession.SqlSessionFactoryBuilder;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.junit.Before;
import org.junit.Test;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-13:00
 */
public class JDBC {
    
    private SqlSessionFactory sqlSessionFactory;
    
    @Before
    public void init() {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("mybatisConfig.xml");
        this.sqlSessionFactory = new SqlSessionFactoryBuilder().build(in);
    }
    
    @Test
    public void findUserByid() {
        UserDao userDao = new UserDaoImpl(sqlSessionFactory);
        System.out.println(userDao.findUserById(1));
    }
    
    @Test
    public void jdbc() throws ClassNotFoundException, SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        PreparedStatement preparedStatement = null;
        try {
            //加载数据库驱动
            Class.forName("com.mysql.jdbc.Driver");
            //获取数据库链接
            connection = DriverManager
                .getConnection("jdbc:mysql://gerry-out.mysql.rds.aliyuncs.com:3306/kkb?characterEncoding=utf-8",
                    "gerry", "123456jX");
            //根据数据库sql脚本从链接中获取preparestatement
            String sql = "select * from kkb_user where id = ? ";
            preparedStatement = connection.prepareStatement(sql);
            //构造sql查询的参数
            preparedStatement.setInt(1, 1);
            //执行查询操作
            resultSet = preparedStatement.executeQuery();
            //处理返回结果集
            while (resultSet.next()) {
                System.out.println("id:->" + resultSet.getString("id"));
                System.out.println("userName:->" + resultSet.getString("user_name"));
            }
        } finally {
            // 释放资源
            if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                }
            }
        }
        
    }
    
}
