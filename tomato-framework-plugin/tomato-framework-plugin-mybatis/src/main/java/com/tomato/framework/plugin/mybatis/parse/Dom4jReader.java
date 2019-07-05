package com.tomato.framework.plugin.mybatis.parse;

import java.io.InputStream;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-29-14:43
 */
public final class Dom4jReader {
    
    private Dom4jReader() {
    }
    
    public static Document reader(InputStream in) {
        try {
            SAXReader reader = new SAXReader();
            return reader.read(in);
        } catch (DocumentException e) {
            e.printStackTrace();
        }
        return null;
    }
}
