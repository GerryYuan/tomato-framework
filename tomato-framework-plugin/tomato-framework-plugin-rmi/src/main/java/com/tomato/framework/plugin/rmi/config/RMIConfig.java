package com.tomato.framework.plugin.rmi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by gerry
 */
@Configuration
@ComponentScan(basePackages = {"com.tomato.framework.plugin.rmi"})
public class RMIConfig {

//    @Bean
//    public RemoteServiceAnnotationBeanPostProcessor remoteServiceAnnotationBeanPostProcessor() {
//        return new RemoteServiceAnnotationBeanPostProcessor();
//    }

//    @Bean
//    public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
//        RemoteServiceAnnotationBeanPostProcessor remoteServiceAnnotationBeanPostProcessor = remoteServiceAnnotationBeanPostProcessor();
//        SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
//        simpleUrlHandlerMapping.setUrlMap(remoteServiceAnnotationBeanPostProcessor.getMap());
//        return simpleUrlHandlerMapping;
//    }

//    @Bean
//    public RemoteReferenceAnnotationBeanPostProcessor remoteReferenceAnnotationBeanPostProcessor() {
//        RemoteReferenceAnnotationBeanPostProcessor remoteReferenceAnnotationBeanPostProcessor = new RemoteReferenceAnnotationBeanPostProcessor();
//        remoteReferenceAnnotationBeanPostProcessor.setAutowiredAnnotationType(RemoteReference.class);
//        return remoteReferenceAnnotationBeanPostProcessor;
//    }

}
