package com.tomato.framework.plugin.rmi;

import lombok.Data;

@Data
public class RMIRequest {
    
    private String className;
    
    private String methodName;
    
    private Object[] paramters;
    
}
