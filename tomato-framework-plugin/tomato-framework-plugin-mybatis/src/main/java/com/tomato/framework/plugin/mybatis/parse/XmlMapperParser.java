package com.tomato.framework.plugin.mybatis.parse;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import java.io.InputStream;
import java.util.List;
import org.dom4j.Document;
import org.dom4j.Element;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:45
 */
public class XmlMapperParser {
    
    private Configuration configuration;
    
    public XmlMapperParser(Configuration configuration) {
        this.configuration = configuration;
    }
    
    public void parser(Element root) {
        Element mappers = root.element("mappers");
        List<Element> mapper = mappers.elements("mapper");
        mapper.forEach(e -> {
            String resource = e.attributeValue("resource");
            InputStream in = this.getClass().getClassLoader().getResourceAsStream(resource);
            Document reader = Dom4jReader.reader(in);
            parserMapper(reader.getRootElement());
        });
        return;
    }
    
    private void parserMapper(Element root) {
        //TODO 解析mapper文件
    }
    
}


