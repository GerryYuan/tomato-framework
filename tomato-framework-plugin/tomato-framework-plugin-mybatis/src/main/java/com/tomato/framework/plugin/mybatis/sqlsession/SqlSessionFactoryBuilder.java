package com.tomato.framework.plugin.mybatis.sqlsession;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import com.tomato.framework.plugin.mybatis.parse.Dom4jReader;
import com.tomato.framework.plugin.mybatis.parse.XmlConfigParser;
import java.io.InputStream;
import org.dom4j.Document;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:36
 */
public class SqlSessionFactoryBuilder {
    
    private Configuration configuration;
    
    public SqlSessionFactoryBuilder() {
        this.configuration = new Configuration();
    }
    
    public SqlSessionFactory build(InputStream in) {
        //读取配置文件，加载成Document
        Document reader = Dom4jReader.reader(in);
        //根据document进行config解析
        XmlConfigParser xmlConfigParser = new XmlConfigParser(this.configuration);
        xmlConfigParser.parser(reader.getRootElement());
        return build();
    }
    
    private SqlSessionFactory build() {
        return new DefaultSqlSessionFactory(configuration);
    }
}
