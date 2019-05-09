package com.tomato.framework.plugin.mqtt.exception;

import lombok.Getter;

public class MqttException extends RuntimeException {
    
    @Getter
    private int errorCode = 50003;
    
    public MqttException(String msg) {
        super(msg);
    }
    
    public MqttException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public MqttException(Throwable cause) {
        super(cause);
    }
    
}
