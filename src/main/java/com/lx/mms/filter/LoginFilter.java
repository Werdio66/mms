package com.lx.mms.filter;

import com.lx.mms.common.Constance;
import com.lx.mms.common.RequestHolder;
import com.lx.mms.entity.SysUser;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  登录请求拦截
 */
@Slf4j
public class LoginFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        SysUser user = (SysUser) req.getSession().getAttribute(Constance.LOGIN_USER);
        log.info("session 中的用户：{}", user);
        if (user == null){
            req.setAttribute("error", "您没有访问权限，请先登录");
            req.getRequestDispatcher("/login").forward(req, resp);
            return;
        }
        // 存放到 threadlocal 中
        if (RequestHolder.getCurrentUser() == null){
            RequestHolder.add(user);
        }

        if (RequestHolder.getCurrentRequest() == null){
            RequestHolder.add(req);
        }

        // 登录成功
        chain.doFilter(request, response);
    }
}
