package com.lx.mms.filter;

import com.google.common.base.Splitter;
import com.lx.mms.common.ApplicationContextHelper;
import com.lx.mms.common.RequestHolder;
import com.lx.mms.common.RespData;
import com.lx.mms.entity.SysUser;
import com.lx.mms.service.impl.SysCoreService;
import com.lx.mms.util.JsonMapper;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

/**
 * 权限控制器
 */
@Slf4j
public class AclControlFilter implements Filter {

    /**
     *  存放所有要排除的 url
     */
    private Set<String> exclusionUrlSet = new ConcurrentSkipListSet<>();

    /**
     *  定义未验证通过跳转的 url
     */
    private final String noAuthUrl = "/sys/user/noAuth.page";

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // 获取定义的不过滤的 url
        String exclusionUrls = filterConfig.getInitParameter("exclusionUrls");
        // 将字符串转换成 list
        List<String> exclusionUrlList = Splitter.on(",").omitEmptyStrings().trimResults().splitToList(exclusionUrls);
        // 将所有的不需要拦截的 url 加入 set 中
        exclusionUrlSet.addAll(exclusionUrlList);
        exclusionUrlSet.add(noAuthUrl);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        // 获取请求路径
        String servletPath = req.getServletPath();
        // 获取所有的请求参数
        Map<String, String[]> parameterMap = req.getParameterMap();

        // 当前请求是之前定义的排除的路径，直接放行
        if (exclusionUrlSet.contains(servletPath)){
            chain.doFilter(request, response);
            return;
        }
        // 获取当前登录的用户
        SysUser currentUser = RequestHolder.getCurrentUser();
        // 未登录
        if (currentUser == null){
            log.info("当前的请求路径：{}，没有登录，请求参数：{}", servletPath, JsonMapper.obj2String(parameterMap));
            // 处理未验证通过的 url
            noAuth(req, resp);
            return;
        }

        // 已经登录，判断用户是否有权限

        SysCoreService sysCoreService = ApplicationContextHelper.popBean(SysCoreService.class);

        // 没有权限访问
        if (!sysCoreService.hasUrlAcl(servletPath)){
            log.info("{} 用户的请求路径：{}，没有访问权限，请求参数：{}", currentUser.getUsername(), servletPath, JsonMapper.obj2String(parameterMap));
            noAuth(req, resp);
            return;
        }

        // 放行
        chain.doFilter(request, response);
    }


    private void noAuth(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String servletPath = req.getServletPath();

        if (servletPath.endsWith(".json")){
            RespData respData = RespData.error("您没有访问权限，如需访问，请联系管理员！");
            resp.setHeader("Content-Type", "application/json");
            resp.getWriter().println(JsonMapper.obj2String(respData));
        }else {
            clientRedirect(resp);
        }
    }


    private void clientRedirect(HttpServletResponse response) throws IOException{
        response.setHeader("Content-Type", "text/html");
        response.getWriter().print("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Strict//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd\">\n"
                + "<html xmlns=\"http://www.w3.org/1999/xhtml\">\n" + "<head>\n" + "<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\"/>\n"
                + "<title>跳转中...</title>\n" + "</head>\n" + "<body>\n" + "跳转中，请稍候...\n" + "<script type=\"text/javascript\">//<![CDATA[\n"
                + "window.location.href='" + noAuthUrl + "?ret='+encodeURIComponent(window.location.href);\n" + "//]]></script>\n" + "</body>\n" + "</html>\n");
    }
}
