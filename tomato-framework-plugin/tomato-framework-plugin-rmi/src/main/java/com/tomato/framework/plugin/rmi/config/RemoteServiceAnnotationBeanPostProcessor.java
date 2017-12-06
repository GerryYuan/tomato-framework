package com.tomato.framework.plugin.rmi.config;

import com.google.common.collect.Maps;
import com.tomato.framework.core.util.EmptyUtils;
import com.tomato.framework.plugin.rmi.annotation.RemoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.beans.FatalBeanException;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessorAdapter;
import org.springframework.core.Ordered;
import org.springframework.core.PriorityOrdered;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.remoting.caucho.BurlapServiceExporter;
import org.springframework.remoting.caucho.HessianServiceExporter;
import org.springframework.remoting.httpinvoker.HttpInvokerServiceExporter;
import org.springframework.remoting.rmi.RmiServiceExporter;

import java.rmi.RemoteException;
import java.util.Map;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2017-04-07-18:54
 */
@Slf4j
public class RemoteServiceAnnotationBeanPostProcessor extends InstantiationAwareBeanPostProcessorAdapter implements PriorityOrdered {

    private Map<String, Object> map = Maps.newHashMap();

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        RemoteService service = AnnotationUtils.findAnnotation(bean.getClass(), RemoteService.class);
        if (EmptyUtils.isEmpty(service)) {
            return bean;
        }
        registerServiceExporterUrl(bean, service);
        return getServiceExporter(beanName, bean, service);
    }

    @Override
    public int getOrder() {
        return Ordered.LOWEST_PRECEDENCE - 1;
    }

    private Object getServiceExporter(String beanName, Object bean, RemoteService remoteService) {
        switch (remoteService.serviceType()) {
            case HTTP:
                HttpInvokerServiceExporter httpInvokerServiceExporter = new HttpInvokerServiceExporter();
                httpInvokerServiceExporter.setServiceInterface(remoteService.serviceInterface());
                httpInvokerServiceExporter.setService(bean);
                httpInvokerServiceExporter.afterPropertiesSet();
                return httpInvokerServiceExporter;
            case HESSIAN:
                HessianServiceExporter hessianServiceExporter = new HessianServiceExporter();
                hessianServiceExporter.setServiceInterface(remoteService.serviceInterface());
                hessianServiceExporter.setService(bean);
                hessianServiceExporter.afterPropertiesSet();
                return hessianServiceExporter;
            case BURLAP:
                BurlapServiceExporter burlapServiceExporter = new BurlapServiceExporter();
                burlapServiceExporter.setServiceInterface(remoteService.serviceInterface());
                burlapServiceExporter.setService(bean);
                burlapServiceExporter.afterPropertiesSet();
                return burlapServiceExporter;
            case RMI:
                try {
                    RmiServiceExporter rmiServiceExporter = new RmiServiceExporter();
                    rmiServiceExporter.setServiceInterface(remoteService.serviceInterface());
                    rmiServiceExporter.setService(bean);
                    if (remoteService.port() != 0) {
                        rmiServiceExporter.setRegistryPort(remoteService.port());
                    }
                    String serviceName = beanName;
                    if (serviceName.startsWith("/")) {
                        serviceName = serviceName.substring(1);
                    }
                    rmiServiceExporter.setServiceName(serviceName);
                    rmiServiceExporter.afterPropertiesSet();
                    return rmiServiceExporter;
                } catch (RemoteException remoteException) {
                    throw new FatalBeanException("Exception initializing RmiServiceExporter", remoteException);
                }
            default:
                return bean;
        }
    }

    private void registerServiceExporterUrl(Object bean, RemoteService remoteService) {
        if (EmptyUtils.isEmpty(remoteService.url())) {
            throw new RuntimeException("initializing bean ->" + bean + " error, please check bean annotation @RemoteService url property");
        }
        String serviceName = remoteService.url();
        map.put(serviceName, bean);
    }

    public Map<String, Object> getMap() {
        return map;
    }
}
