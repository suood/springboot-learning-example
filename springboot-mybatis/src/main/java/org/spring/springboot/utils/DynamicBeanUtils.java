package org.spring.springboot.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class DynamicBeanUtils implements
        ApplicationContextAware {
    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        this.applicationContext = applicationContext;

    }
    public static Object getBean(String name){
        return applicationContext.getBean(name);
    }


    public static <T> T getBean(String name,Class<T> tClass){
        return (T) applicationContext.getBean(name);
    }

    public static  <T> T getBean(Class<T> var1) {
        return applicationContext.getBean( var1);
    }
}