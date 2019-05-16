package com.tomato.framework.plugin.rmi.server;

import com.google.common.collect.Maps;
import com.tomato.framework.core.util.EmptyUtils;
import java.util.Arrays;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class RMIRegisterConfig {
    
    /**
     * 服务端发布的服务
     */
    private static Map<String, Object> services = Maps.newConcurrentMap();
    
    /**
     * 绑定服务名称和服务对象
     */
    public static void bind(Object... services) {
        for (Object service : services) {
            //获取发布的服务接口
            //发布接口的class
            Class<?>[] classes = service.getClass().getInterfaces();
            if (EmptyUtils.isEmpty(classes)) {
                return;
            }
            Arrays.stream(classes).forEach(clzz -> {
                String serviceName = clzz.getName();
                log.info("将{}和{}进行绑定", serviceName, service);
                RMIRegisterConfig.services.put(serviceName, service);
            });
            
        }
    }
    
    public static Object get(String name) {
        return services.get(name);
    }
    
}
