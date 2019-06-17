package com.tomato.framework.plugin.rmi.loadbalance;

import java.util.List;
import java.util.Random;

public class RandomLoadBalance implements LoadBalance {
    
    private Random random = new Random();
    
    @Override
    public <T> T loadbalance(List<T> sources) {
        return sources.get(random.nextInt(sources.size()));
    }
    
}
