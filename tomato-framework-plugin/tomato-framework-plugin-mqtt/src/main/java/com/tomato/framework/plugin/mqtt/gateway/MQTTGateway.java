package com.tomato.framework.plugin.mqtt.gateway;

import org.springframework.integration.annotation.MessagingGateway;
import org.springframework.integration.mqtt.support.MqttHeaders;
import org.springframework.messaging.handler.annotation.Header;

@MessagingGateway(defaultRequestChannel = "mqttOutputChannel")
public interface MQTTGateway {
    
    void sendToMqtt(String data);
    
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, String data);
    
    void sendToMqtt(@Header(MqttHeaders.TOPIC) String topic, @Header(MqttHeaders.QOS) int qos, String data);
    
}
