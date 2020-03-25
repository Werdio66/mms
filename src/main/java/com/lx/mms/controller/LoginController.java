package com.lx.mms.controller;

import com.lx.mms.entity.SysUser;
import com.lx.mms.service.SysUserService;
import com.lx.mms.util.MD5Util;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;

@Controller
@Slf4j
public class LoginController {

    @Autowired
    private SysUserService sysUserService;

    @GetMapping("/login")
    public String login(){
        log.info("跳转到登录页面：");
        return "signin";
    }

    @PostMapping("/login.page")
    public String login(@NotBlank(message = "用户名不能为空") String username,
                      @NotBlank(message = "密码不能为空") String password,
                      @RequestParam(required = false, defaultValue = "") String ret,
                      Model model) {
        log.info("登录：");
        log.info("用户名：{}", username);
        log.info("跳转到登录前 url = {}", ret);
        SysUser sysUser = sysUserService.findByKeyword(username);

        if (sysUser == null){
            model.addAttribute("error", "用户名或密码不存在");
            return "forward:/login";
        }

        if (StringUtils.isNotBlank(ret)){
            return ret;
        }

        return "redirect:/admin/index.page";
    }
}
