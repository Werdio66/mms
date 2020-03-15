package com.lx.mms.common;

import com.lx.mms.exception.ParamException;
import com.lx.mms.exception.PermissionException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;

/**
 *  自定义异常处理
 */
@Slf4j
@Component
public class SpringExceptionResover implements HandlerExceptionResolver {

    /**
     *  默认的错误信息
     */
    private static final String DEFULT_MSG = "error";

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

        String url = request.getRequestURL().toString();
        ModelAndView mv = null;

        // 所有的数据返回为 .json，页面返回为 .page
        if (url.endsWith(".json")){

            // 判断是不是自定义的异常类
            if (ex instanceof PermissionException || ex instanceof ParamException){
                RespData respData = RespData.error(ex.getMessage());
                mv = new ModelAndView("jsonView", respData.toMap());
            }else {
                // 不是自定义异常类
                log.error("其他异常：url = {},异常信息：{}", url, ex.getMessage());
                log.error("堆栈信息：{}", Arrays.toString(ex.getStackTrace()));
                RespData respData = RespData.error(ex.getMessage());
                mv = new ModelAndView("jsonView", respData.toMap());
            }

            // .page
        }else if (url.endsWith(".page")){
            log.error("跳转到错误页面，未知异常：url = {}, 异常信息：{}", url, ex.getMessage());
            log.error("堆栈信息：{}", Arrays.toString(ex.getStackTrace()));
            RespData respData = RespData.error(DEFULT_MSG);
            // 跳转到异常页面
            mv = new ModelAndView("exception", respData.toMap());
        }else {
            log.error("未知异常：url = {},异常信息：{}", url, ex.getMessage());
            RespData respData = RespData.error(DEFULT_MSG);
            mv = new ModelAndView("jsonView", respData.toMap());
        }
        return mv;
    }
}
