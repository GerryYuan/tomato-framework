package com.tomato.framework.plugin.mvc.handler;

import java.lang.reflect.Method;
import lombok.Data;

@Data
public class HandlerMethod {
    
    private Method method;
    
    // 最终通过反射调用方法传的是Controller对象
    private Object handler;
    
}
