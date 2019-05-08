package com.tomato.framework.plugin.mqtt.properties;

import lombok.Data;

/**
 * 关于mqtt数据生产端配置类
 */
@Data
public class MQTTBoundProperties {
    private String clientId;
    private String topic;
    private long completionTimeout;
}
