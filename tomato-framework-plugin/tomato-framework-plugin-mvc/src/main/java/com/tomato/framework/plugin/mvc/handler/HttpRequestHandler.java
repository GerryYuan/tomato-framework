package com.tomato.framework.plugin.mvc.handler;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpRequestHandler {
    
    void handleRequest(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException;
}
