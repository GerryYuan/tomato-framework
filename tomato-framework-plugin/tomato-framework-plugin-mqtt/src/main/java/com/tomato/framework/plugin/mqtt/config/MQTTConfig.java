package com.tomato.framework.plugin.mqtt.config;

import com.google.common.base.Splitter;
import com.tomato.framework.plugin.mqtt.properties.MQTTProperties;
import javax.annotation.Resource;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.core.MessageProducer;
import org.springframework.integration.mqtt.core.DefaultMqttPahoClientFactory;
import org.springframework.integration.mqtt.core.MqttPahoClientFactory;
import org.springframework.integration.mqtt.inbound.MqttPahoMessageDrivenChannelAdapter;
import org.springframework.integration.mqtt.outbound.MqttPahoMessageHandler;
import org.springframework.integration.mqtt.support.DefaultPahoMessageConverter;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.MessageHandler;

@Configuration
@IntegrationComponentScan(basePackages = {"com.tomato.framework.plugin.mqtt"})
@EnableConfigurationProperties({MQTTProperties.class})
public class MQTTConfig {
    
    @Resource
    private MQTTProperties mqttProperties;
    
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttProperties.getUsername());
        mqttConnectOptions.setPassword(mqttProperties.getPassword().toCharArray());
        mqttConnectOptions
            .setServerURIs(Splitter.on(",").splitToList(mqttProperties.getUrl()).stream().toArray(String[]::new));
        mqttConnectOptions.setKeepAliveInterval(2);
        return mqttConnectOptions;
    }
    
    @Bean
    public MqttPahoClientFactory mqttClientFactory() {
        DefaultMqttPahoClientFactory factory = new DefaultMqttPahoClientFactory();
        factory.setConnectionOptions(getMqttConnectOptions());
        return factory;
    }
    
    // 接收通道
    @Bean
    public MessageChannel mqttInputChannel() {
        return new DirectChannel();
    }
    
    // 配置client,监听topic
    @Bean
    public MessageProducer inbound(MqttPahoClientFactory mqttPahoClientFactory) {
        MqttPahoMessageDrivenChannelAdapter adapter = new MqttPahoMessageDrivenChannelAdapter(
            mqttProperties.getInbound().getClientId(), mqttPahoClientFactory,
            Splitter.on(",").omitEmptyStrings().splitToList(mqttProperties.getInbound().getTopic()).stream()
                .toArray(String[]::new));
        adapter.setCompletionTimeout(mqttProperties.getInbound().getCompletionTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(1);
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }
    
    // 通过通道获取数据（建议在逻辑处理层实现MessageHandler接口）
//    @Bean
//    @ServiceActivator(inputChannel = "mqttInputChannel")
//    public MessageHandler receiveHandler() {
//        return message -> {
//            //MessageHeaders headers = message.getHeaders();
//            String m = message.getPayload().toString();
//            // 处理数据交给具体的业务层
//            System.out.println(m);
//        };
//    }
    
    // 推送通道
    @Bean
    public MessageChannel mqttOutputChannel() {
        return new DirectChannel();
    }
    
    @Bean
    @ServiceActivator(inputChannel = "mqttOutputChannel")
    public MessageHandler sendHandler(MqttPahoClientFactory mqttPahoClientFactory) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
            mqttProperties.getOutbound().getClientId(), mqttPahoClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(1);
        messageHandler.setDefaultTopic(mqttProperties.getOutbound().getTopic());
        return messageHandler;
    }
    
}
