package com.tomato.framework.plugin.mvc.handler;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class BeanNameUrlHandlerMapping implements HandlerMapping {
    
    private Map<String, Object> urlHandlerMap = new HashMap<>();
    
    public void init() {
        //到bean工厂中获取相关的bean实例，然后查找beanName以/开头的，然后放入到Map中
    }
    
    @Override
    public Object getHandler(HttpServletRequest request) {
        return urlHandlerMap.get(request.getRequestURI());
    }
    
}
