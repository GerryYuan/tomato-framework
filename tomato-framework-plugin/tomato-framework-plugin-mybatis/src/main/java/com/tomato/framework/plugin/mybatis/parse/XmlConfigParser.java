package com.tomato.framework.plugin.mybatis.parse;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import java.util.List;
import java.util.Objects;
import java.util.Properties;
import org.apache.commons.dbcp.BasicDataSource;
import org.dom4j.Element;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:45
 */
public class XmlConfigParser {
    
    private Configuration configuration;
    
    public XmlConfigParser(Configuration configuration) {
        this.configuration = configuration;
    }
    
    public void parser(Element root) {
        //开始解析environments
        Element envs = root.element("environments");
        String defaultId = envs.attributeValue("default");
        List<Element> env = envs.elements("environment");
        env.forEach(e -> {
            String envId = e.attributeValue("id");
            if (Objects.isNull(envId) || !envId.equals(defaultId)) {
                return;
            }
            // 如果和默认的环境ID匹配，才进行解析
            parseDataSource(e.element("dataSource"));
        });
    }
    
    private void parseDataSource(Element element) {
        String type = element.attributeValue("type");
        if (Objects.isNull(type) || type.equals("")) {
            type = "DBCP";
        }
        List<Element> elements = element.elements("property");
        Properties properties = new Properties();
        elements.forEach(e -> {
            String name = e.attributeValue("name");
            String value = e.attributeValue("value");
            properties.put(name, value);
        });
    
        BasicDataSource dataSource = null;
        if (type.equals("DBCP")) {
            dataSource = new BasicDataSource();
            dataSource.setDriverClassName(properties.getProperty("driver"));
            dataSource.setUrl(properties.getProperty("url"));
            dataSource.setUsername(properties.getProperty("username"));
            dataSource.setPassword(properties.getProperty("password"));
        }
        //解析完了set到configuration中
        configuration.setDataSource(dataSource);
    }
    
}
