package com.tomato.framework.plugin.rmi.loadbalance;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class RoundRobin implements LoadBalance {
    
    private AtomicInteger pos = new AtomicInteger(0);
    
    @Override
    public <T> T loadbalance(List<T> sources) {
        int index = pos.get();
        if (index >= sources.size()) {
            pos.set(0);
        }
        T value = sources.get(pos.get());
        pos.addAndGet(1);
        return value;
    }
    
}
