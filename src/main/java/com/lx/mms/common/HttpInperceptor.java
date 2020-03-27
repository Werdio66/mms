package com.lx.mms.common;

import com.lx.mms.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *  对请求 url 前后做操作
 */
@Slf4j
@Component
public class HttpInperceptor extends HandlerInterceptorAdapter {

    private long begin;

    /**
     * 前置操作
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 记录进入当前请求的时间
        begin = System.currentTimeMillis();
        String uri = request.getRequestURI();
        Map<String, String[]> parameterMap = request.getParameterMap();
        log.info("请求方式 : {}, 请求 uri : {}， 请求参数 : {}", request.getMethod(), uri, JsonMapper.obj2String(parameterMap));
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        String uri = request.getRequestURI();
        log.info("{} 请求共花费 {} 毫秒", uri, System.currentTimeMillis() - begin);
        // 在当前请求完成后，清除 threadlocal 中的数据
        removeThreadLocalInfo();
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
        removeThreadLocalInfo();
    }

    public void removeThreadLocalInfo(){
        RequestHolder.remove();
    }
}
