package com.tomato.framework.plugin.mybatis.parse;

import com.tomato.framework.plugin.mybatis.config.Configuration;
import com.tomato.framework.plugin.mybatis.exception.XmlMybatisParserException;
import com.tomato.framework.plugin.mybatis.statement.MappedStatement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
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
        String namespace = root.attributeValue("namespace");
        List<Element> select = root.elements("select");
        Map<String, MappedStatement> statementMap = new HashMap<>();
        select.forEach(s -> {
            MappedStatement mappedStatement = parserSelect(s);
            String statementId = namespace.concat(".").concat(mappedStatement.getId());
            statementMap.put(statementId, mappedStatement);
        });
        configuration.addMappedStatement(statementMap);
    }
    
    private MappedStatement parserSelect(Element select) {
        String id = select.attributeValue("id");
        if (Objects.isNull(id)) {
            throw new XmlMybatisParserException("parse mapper xml select tag id is null");
        }
        String statementType = select.attributeValue("statementType");
        if (Objects.isNull(statementType)) {
            statementType = "prepared";
        }
        String parameterType = select.attributeValue("parameterType");
        String resultType = select.attributeValue("resultType");
        String sqlText = select.getTextTrim();
        //需要解析sql，然后判断是否是动态sql还是静态sql
        SqlSource sqlSource = new StaticSqlSource(configuration, sqlText);
        try {
            Class<?> paramterTypeClass = Class.forName(parameterType);
            return MappedStatement.builder().id(id).parameterType(parameterType).paramterTypeClass(paramterTypeClass)
                .resultType(resultType).sqlSource(sqlSource).statementType(statementType).build();
        } catch (ClassNotFoundException e) {
            throw new XmlMybatisParserException("id -> " + id + "，parameterType error is -> " + e.getMessage());
        }
    
    }
    
}


