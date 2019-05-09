package com.tomato.framework.plugin.mqtt.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "com.mqtt")
public class MqttProperties {
    private String url;
    private String username;
    private String password;
    private String subscribeId;
    private String publisherId;
    private String topics;
    //#超时时间 单位为秒
    private long completionTimeout;
    
    /**
     * #0代表“至多一次”，消息发布完全依赖底层 TCP/IP 网络。会发生消息丢失或重复。这一级别可用于如下情况，环境传感器数据，丢失一次读记录无所谓，因为不久后还会有第二次发送。
     * #1代表“至少一次”，确保消息到达，但消息重复可能会发生。
     * #2代表“只有一次”，确保消息到达一次。这一级别可用于如下情况，在计费系统中，消息重复或丢失会导致不正确的结果。
     */
    private int qos = 1;
    /**
     * #若心跳时间过短，在消费端繁忙时会无法完成服务端的心跳回复导致服务端认为消费端下线而无法完成订阅
     * #可采用MQ接收端和消费端之间加上队列
     */
    private int keepAliveInterval = 60;
    
    private String defaultTopic;
    
}
