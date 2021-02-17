package com.security.securitytestdemo.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {
//    @RequestMapping("/")
////    @ResponseBody
//    public String login() {
////        return "test";
//        return "redirect:login.html";
////        return "main";
//    }
    //判断角色
//    @Secured("ROLE_owner")
    //之前执行，是一个access表达式,''内允许以ROLE开头，但是配置类不允许以ROLE开头
    @PreAuthorize("hasRole('owner')")
    @RequestMapping("/toMain")
    public String main() {
        return "redirect:main.html";
    }
    @RequestMapping("/toError")
    public String error() {
        return "redirect:error.html";
    }
}
