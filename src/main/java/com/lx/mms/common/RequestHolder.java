package com.lx.mms.common;

import com.lx.mms.entity.SysUser;

import javax.servlet.http.HttpServletRequest;

/**
 * 将当前线程的请求存放到 threadlocal 中，可以在任意地方取出当前登录的用户
 */
public class RequestHolder {

    private static ThreadLocal<SysUser> userHolder = new ThreadLocal<>();

    private static ThreadLocal<HttpServletRequest> requestHolder = new ThreadLocal<>();

    public static void add(SysUser user){
        userHolder.set(user);
    }

    public static void add(HttpServletRequest request){
        requestHolder.set(request);
    }

    public static SysUser getCurrentUser(){
        return userHolder.get();
    }

    public static HttpServletRequest getCurrentRequest(){
        return requestHolder.get();
    }

    // 清除当前线程中的数据，防止内存泄漏
    public static void remove(){
        userHolder.remove();
        requestHolder.remove();
    }
}
