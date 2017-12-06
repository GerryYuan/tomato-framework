package com.tomato.framework.plugin.rmi.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2017-04-09-23:40
 */
@Slf4j
public class RemoteReferenceAnnotationBeanPostProcessor extends AutowiredAnnotationBeanPostProcessor {

    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
//        log.info("invoke 1, beanName " + beanName);
//        RemoteReference remoteReference = beanClass.getAnnotation(RemoteReference.class);
//        if(EmptyUtils.isNotEmpty(remoteReference)){
//            log.info("get invoke 1 annotation, beanName " + beanName);
//        }
        return super.postProcessBeforeInstantiation(beanClass, beanName);
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
//        log.info("invoke 2, beanName " + beanName);
//        RemoteReference remoteReference = bean.getClass().getAnnotation(RemoteReference.class);
//        if(EmptyUtils.isNotEmpty(remoteReference)){
//            log.info("get invoke 2 annotation, beanName " + beanName);
//        }
        return super.postProcessBeforeInitialization(bean, beanName);
    }

}
