package com.tomato.framework.plugin.mvc.handler;

import com.alibaba.fastjson.JSON;
import com.tomato.framework.plugin.mvc.annotation.ResponseBody;
import java.io.IOException;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RequestMappingHandlerAdapter implements HandlerAdapter {
    
    @Override
    public boolean supports(Object handler) {
        return handler instanceof HandlerMethod;
    }
    
    @Override
    public void handleRequest(Object handler, HttpServletRequest request, HttpServletResponse response)
        throws Exception {
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        Method method = handlerMethod.getMethod();
        Object object = handlerMethod.getHandler();
        // 获取方法参数(参数绑定流程)
        Object[] args = getParameters(request, method);
        // 执行处理器方法
        Object returnValue = method.invoke(object, args);
        // 处理返回值
        handleReturnValue(returnValue, response, method);
        
    }
    
    private void handleReturnValue(Object returnValue, HttpServletResponse response, Method method) {
        try {
            if (method.isAnnotationPresent(ResponseBody.class)) {
                // 响应数据（前后端分离）
                if (returnValue instanceof String) {
                    response.setContentType("text/plain;charset=utf8");
                    response.getWriter().write(returnValue.toString());
                } else {
                    response.setContentType("application/json;charset=utf8");
                    response.getWriter().write(JSON.toJSONString(returnValue));
                }
            } else {
                // 展示视图
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    private Object[] getParameters(HttpServletRequest request, Method method) {
        // key=value&key1=value1.....
        // 获取请求URL中的参数信息
        Map<String, String[]> parameterMap = request.getParameterMap();
        // 获取handler方法的参数信息
        Parameter[] parameters = method.getParameters();
        List<Object> args = new ArrayList<>();
        for (Parameter parameter : parameters) {
            // 获取参数名称(如果不做特殊处理，name值是arg0，arg1)
            String name = parameter.getName();
            Class<?> type = parameter.getType();
            // 从url参数中获取指定名称的值
            String[] value = parameterMap.get(name);
            if (type == List.class) {
                args.add(value);
            } else if (type == Integer.class) {
                args.add(Integer.parseInt(value[0]));
            } else if (type == String.class) {
                args.add(value[0]);
            }
        }
        return args.toArray();
    }
}
