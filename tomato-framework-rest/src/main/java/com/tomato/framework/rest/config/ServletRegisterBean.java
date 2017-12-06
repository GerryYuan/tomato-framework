package com.tomato.framework.rest.config;

import com.google.common.collect.Lists;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * @author Created by gerry
 * @version 1.0
 * @date 2017-04-16-23:18
 */
@Configuration
public class ServletRegisterBean {
    @Bean
    public ServletRegistrationBean servletRegistrationBean(DispatcherServlet dispatcherServlet){
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean();
        servletRegistrationBean.setServlet(dispatcherServlet);
        servletRegistrationBean.setUrlMappings(Lists.newArrayList("/*"));
        servletRegistrationBean.setLoadOnStartup(1);
        return servletRegistrationBean;
    }

}
