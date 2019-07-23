package com.tomato.framework.plugin.mvc.dispatcher;

import com.google.common.collect.Lists;
import com.tomato.framework.plugin.mvc.handler.HandlerAdapter;
import com.tomato.framework.plugin.mvc.handler.HandlerMapping;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DispatcherServlet extends HttpServlet {
    
    private List<HandlerMapping> handlerMappings = Lists.newArrayList();
    
    private List<HandlerAdapter> handlerAdapters = Lists.newArrayList();
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        String contextConfigLocation = config.getInitParameter("contextConfigLocation");
        //第一步，创建spring的ioc容器
//        BeanFactory beanFactory = new DefaultListableBeanFactory(contextConfigLocation);
        //第二步，根据class类型获取容器中的HandlerMapping和HandlerAdapter
        //赋值给handlerMappings和handlerAdapters中
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //第一步，根据url去获取映射关系HandlerMapping，然后get到Handler
        Object handlder = getHandler(req);
        //第二步，根据handler去适配一个HandlderAdapter
        HandlerAdapter handlerAdapter = getHandlerAdapter(handlder);
        //第三步，执行相关handler接口调用相关业务代码
        try {
            handlerAdapter.handleRequest(handlder, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPost(req, resp);
    }
    
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doPut(req, resp);
    }
    
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doDelete(req, resp);
    }
    
    public Object getHandler(HttpServletRequest request) {
        for (HandlerMapping handlerMapping : handlerMappings) {
            Object handler = handlerMapping.getHandler(request);
            if (handler != null) {
                return handler;
            }
        }
        return null;
    }
    
    public HandlerAdapter getHandlerAdapter(Object handler) {
        for (HandlerAdapter handlerAdapter : handlerAdapters) {
            if (handlerAdapter.supports(handler)) {
                return handlerAdapter;
            }
        }
        return null;
    }
    
}
