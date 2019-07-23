package com.tomato.framework.plugin.mvc.handler;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;

public class RequestMappingHandlerMapping implements HandlerMapping {
    
    private Map<String, HandlerMethod> urlHandlerMethodMap = new HashMap<>();
    
    public void init() throws ClassNotFoundException {
        //第一步，实现BeanNameAware接口，获取BeanName集合
        //第二步，根据名称获取BeanDefinition
        //第三步，根据BeanDefinition获取Class类型，看是否包含@Controller和@RequestMapping注解
        //第四步，反射class，然后获取Method对象，然后获取方法的注解@RequestMapping，拿到url
        //第五步，封装HandlerMethod，设置method和Handler
        //第六步，建立映射关系
//        List<String> beanNames = beanFactory.getBeanNamesByType(Object.class);
//        for (String beanName : beanNames) {
//            Map<String, BeanDefinition> beanDefinitions = beanFactory.getBeanDefinitions();
//            BeanDefinition beanDefinition = beanDefinitions.get(beanName);
//            String beanClassName = beanDefinition.getBeanClassName();
//            Class<?> clazz = Class.forName(beanClassName);
//            // 如果bean是带有@Controller注解的，再进行下一步处理
//            if (isHandler(clazz)) {
//                // 再去判断带有@Controller注解的类中的方法上面是否带有@RequestMapping注解
//                Method[] methods = clazz.getDeclaredMethods();
//                for (Method method : methods) {
//                    if (method.isAnnotationPresent(RequestMapping.class)) {
//                        // 获取requestMapping注解中的url
//                        RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
//                        String url = requestMapping.value();
//                        // 获取该方法对象Method，封装到HandlerMethod对象中
//                        HandlerMethod handlerMethod = new HandlerMethod(method, beanFactory.getBean(beanName));
//                        // 建立映射关系
//                        urlHandlerMethodMap.put(url, handlerMethod);
//                    }
//                }
//            }
//        }
    }
    
    @Override
    public Object getHandler(HttpServletRequest request) {
        return urlHandlerMethodMap.get(request.getRequestURI());
    }
}
