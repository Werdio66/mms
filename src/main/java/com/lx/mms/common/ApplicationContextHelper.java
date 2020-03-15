package com.lx.mms.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ApplicationContextHelper implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    public static Object popBean(String name){
        checkNotNull(applicationContext);
        return applicationContext.getBean(name);
    }

    public static <T> T popBean(String name, Class<T> clazz){
        checkNotNull(applicationContext);
        return applicationContext.getBean(name, clazz);
    }

    public static <T> T popBean(Class<T> clazz){
        checkNotNull(applicationContext);
        return applicationContext.getBean(clazz);
    }

    private static void checkNotNull(ApplicationContext applicationContext){
        log.info("应用上下文 applicationContext ：{}", applicationContext);
        if (applicationContext == null){
            throw new NullPointerException("上下文为空！");
        }
    }
}
