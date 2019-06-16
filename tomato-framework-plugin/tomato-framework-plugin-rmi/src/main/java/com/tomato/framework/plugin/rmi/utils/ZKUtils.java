package com.tomato.framework.plugin.rmi.utils;

import com.tomato.framework.core.util.IPUtils;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2019-06-16-21:29
 */
public final class ZKUtils {
    
    public static String getServerRegisterUrl(String className) {
        // zk register node /namespace/className/ip，value->rmi://ip:port
        String ip = IPUtils.getLocalIp();
        return "/".concat(className).concat("/").concat(ip);
    }
    
    public static String getClientListenerUrl(String className) {
        // zk listener node /namespace/className
        return "/".concat(className);
    }
    
}
