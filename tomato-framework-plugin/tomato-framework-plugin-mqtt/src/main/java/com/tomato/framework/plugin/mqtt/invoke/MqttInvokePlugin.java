package com.tomato.framework.plugin.mqtt.invoke;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import java.util.Arrays;
import java.util.Objects;

public final class MqttInvokePlugin {
    
    private volatile static MqttInvokePlugin plugin = null;
    
    public static MqttInvokePlugin getInstance() {
        if (plugin == null) {
            synchronized (MqttInvokePlugin.class) {
                if (plugin == null) {
                    plugin = new MqttInvokePlugin();
                }
            }
        }
        return plugin;
    }
    
    private Multimap<MqttInvoke, String> invokeMap = ArrayListMultimap.create();
    
    public void store(String[] topics, MqttInvoke mqttInvoke) {
        if (invokeMap.keys().contains(mqttInvoke)) {
            return;
        }
        Arrays.stream(topics).forEach(topic -> {
            invokeMap.put(mqttInvoke, topic);
        });
        return;
    }
    
    public MqttInvoke get(String topic) {
        return invokeMap.entries().stream().map(s -> {
            if (s.getValue().equals(topic)) {
                return s.getKey();
            }
            return null;
        }).filter(Objects::nonNull).findFirst().orElse(null);
    }
    
}
