package com.lx.mms.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 *  mvc 配置扩展类
 */
@Slf4j
@Configuration
public class MySpringMvcConfig implements WebMvcConfigurer {

    @Autowired
    private HttpInperceptor httpInperceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        log.info("http 请求拦截器 : {}", httpInperceptor.getClass());
        registry.addInterceptor(httpInperceptor).addPathPatterns("/**");
    }
}
