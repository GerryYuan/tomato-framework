package com.tomato.framework.plugin.mqtt.msg;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MsgContext<T> implements Serializable {
    
    private MsgContextHeader header;
    
    private T msg;
}
