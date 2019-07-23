package com.tomato.framework.plugin.mvc.handler;

import javax.servlet.http.HttpServletRequest;

public interface HandlerMapping {
    
    Object getHandler(HttpServletRequest request);
    
}
