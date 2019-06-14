package com.tomato.framework.plugin.mqtt.exception;

import lombok.Getter;

public class MqttSerializerException extends RuntimeException {
    
    @Getter
    private int errorCode = 50004;
    
    public MqttSerializerException(String msg) {
        super(msg);
    }
    
    public MqttSerializerException(String msg, Throwable cause) {
        super(msg, cause);
    }
    
    public MqttSerializerException(Throwable cause) {
        super(cause);
    }
    
}
