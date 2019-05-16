package com.tomato.framework.plugin.rmi;

import java.io.Serializable;
import lombok.Data;

@Data
public class RMIRequest implements Serializable {
    
    private String className;
    
    private String methodName;
    
    private Object[] paramters;
    
}
