package com.tomato.framework.plugin.mqtt.invoke;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

@Data
public final class MqttMessageHeader<T> {
    
    @JSONField(name = "mqtt_receivedRetained")
    private Boolean isReceivedRetained;
    
    private String id;
    
    @JSONField(name = "mqtt_duplicate")
    private Boolean isDuplicate;
    
    @JSONField(name = "mqtt_receivedTopic")
    private String topic;
    
    @JSONField(name = "mqtt_receivedQos")
    private Integer qos;
    
    private long timestamp;
    
}
