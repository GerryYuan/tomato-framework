package com.tomato.framework.plugin.mvc.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HandlerAdapter {
    
    boolean supports(Object handler);
    
    void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response) throws Exception;
    
}
