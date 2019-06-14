package com.tomato.framework.test.utils;

import com.tomato.framework.rest.client.HttpClientUtil;
import org.junit.Test;

/**
 * @author gerry
 * @version 1.0, 2017-12-14 11:51
 * @since com.hujiang 1.0.0
 */
public class HttpClientTest {
    
    @Test
    public void get() {
        System.out.println(HttpClientUtil.getInstance().get("http://www.baidu.com"));
    }
    
}
