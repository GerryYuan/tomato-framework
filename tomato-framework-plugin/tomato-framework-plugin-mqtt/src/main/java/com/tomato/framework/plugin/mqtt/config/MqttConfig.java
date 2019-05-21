package com.tomato.framework.plugin.mqtt.config;

import com.google.common.base.Splitter;
import com.tomato.framework.plugin.mqtt.exception.MqttException;
import com.tomato.framework.plugin.mqtt.invoke.MqttInvoke;
import com.tomato.framework.plugin.mqtt.invoke.MqttInvokePlugin;
import com.tomato.framework.plugin.mqtt.msg.MsgContext;
import com.tomato.framework.plugin.mqtt.msg.MsgContextHeader;
import com.tomato.framework.plugin.mqtt.properties.MqttProperties;
import com.tomato.framework.plugin.mqtt.serializer.MqttFastJsonSerializer;
import com.tomato.framework.plugin.mqtt.serializer.Serializer;
import java.lang.reflect.ParameterizedType;
import java.nio.charset.Charset;
import javax.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
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

@Slf4j
@Configuration
@IntegrationComponentScan(basePackages = {"com.tomato.framework.plugin.mqtt"})
@EnableConfigurationProperties({MqttProperties.class})
public class MqttConfig {
    
    @Resource
    private MqttProperties mqttProperties;
    
    /**
     * 序列化
     */
    private Serializer serializer;
    
    private Class<? extends Object> clazz;
    
    @Bean
    public MqttConnectOptions getMqttConnectOptions() {
        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setUserName(mqttProperties.getUsername());
        mqttConnectOptions.setPassword(mqttProperties.getPassword().toCharArray());
        mqttConnectOptions
            .setServerURIs(Splitter.on(",").splitToList(mqttProperties.getUrl()).stream().toArray(String[]::new));
        mqttConnectOptions.setKeepAliveInterval(mqttProperties.getKeepAliveInterval());
        if (serializer == null) {
            serializer = new MqttFastJsonSerializer(Charset.defaultCharset());
        }
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
            mqttProperties.getSubscribeId(), mqttPahoClientFactory,
            Splitter.on(",").omitEmptyStrings().splitToList(mqttProperties.getTopics()).stream()
                .toArray(String[]::new));
        adapter.setCompletionTimeout(mqttProperties.getCompletionTimeout());
        adapter.setConverter(new DefaultPahoMessageConverter());
        adapter.setQos(mqttProperties.getQos());
        adapter.setOutputChannel(mqttInputChannel());
        return adapter;
    }
    
    // 通过通道获取数据（建议在逻辑处理层实现MessageHandler接口）
    @Bean
    @ServiceActivator(inputChannel = "mqttInputChannel")
    public MessageHandler receiveHandler() {
        return message -> {
            MsgContextHeader header = serializer.deserializeHeader(message.getHeaders());
            String msg = message.getPayload().toString();
            log.info("监听到topic[{}],消息[{}]", header.getTopic(), msg);
            MqttInvoke<?> mqttInvoke = MqttInvokePlugin.getInstance().get(header.getTopic());
            if (mqttInvoke == null) {
                throw new MqttException("topic[" + header.getTopic() + "]没有处理handle，处理失败");
            }
            if (clazz == null) {
                ParameterizedType type = (ParameterizedType) mqttInvoke.getClass()
                    .getGenericSuperclass();
                clazz = (Class<? extends Object>) type.getActualTypeArguments()[0];
            }
            mqttInvoke
                .invoke(new MsgContext(header, serializer.deserialize(msg.getBytes(Charset.defaultCharset()), clazz)));
        };
    }
    
    // 推送通道
    @Bean
    public MessageChannel mqttOutputChannel() {
        return new DirectChannel();
    }
    
    @Bean
    @ServiceActivator(inputChannel = "mqttOutputChannel")
    public MessageHandler sendHandler(MqttPahoClientFactory mqttPahoClientFactory) {
        MqttPahoMessageHandler messageHandler = new MqttPahoMessageHandler(
            mqttProperties.getPublisherId(), mqttPahoClientFactory);
        messageHandler.setAsync(true);
        messageHandler.setDefaultQos(mqttProperties.getQos());
        messageHandler.setDefaultTopic(mqttProperties.getDefaultTopic());
        return messageHandler;
    }
    
}
