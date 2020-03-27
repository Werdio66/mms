package com.lx.mms.controller;

import com.lx.mms.common.Constance;
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
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotBlank;

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
                        Model model, HttpSession session) {
        log.info("登录：");
        log.info("用户名：{}", username);
        log.info("跳转到登录前 url = {}", ret);

        SysUser sysUser = sysUserService.findByKeyword(username);

        if (sysUser == null){
            log.info("用户不存在，跳转到登录页面");
            model.addAttribute("error", "用户名不存在");
            return "signin";
        }

        // 判断密码是否正确
        if (!sysUser.getPassword().equals(password)){
            log.info("用户名或者密码错误，跳转到登录页面");
            model.addAttribute("error", "用户名或密码错误");
            model.addAttribute("username", username);
            return "signin";
        }

        // 将登陆用户存放到 session中
        session.setAttribute(Constance.LOGIN_USER, sysUser);
        log.info("登录的用户：{}", sysUser);
        // 请求的路径
        if (StringUtils.isNotBlank(ret)){
            return ret;
        }

        log.info("登录成功，跳转到后台页面");
        return "redirect:/admin/index.page";
    }
}
