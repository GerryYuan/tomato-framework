package com.tomato.framework.plugin.mqtt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.mqtt")
public class MQTTProperties {
    private String url;
    private String username;
    private String password;
    private MQTTBoundProperties inbound;
    private MQTTBoundProperties outbound;
    
}
