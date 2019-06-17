package com.tomato.framework.plugin.rmi.loadbalance;

import java.util.List;

public interface LoadBalance {
    
    <T> T loadbalance(List<T> sources);
    
}
