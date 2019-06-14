package com.tomato.framework.plugin.mqtt.annotation;

import com.tomato.framework.plugin.mqtt.config.MqttConfig;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import org.springframework.context.annotation.Import;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({MqttConfig.class})
public @interface EnableMqtt {
    
}
