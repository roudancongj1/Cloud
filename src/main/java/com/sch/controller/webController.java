package com.sch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 0:48
 */
@Controller
public class webController {
    @RequestMapping("/")
    public String index() { return "page/index.html"; }
    @RequestMapping("404")
    public String error(){
        return "page/404.html";
    }
    @RequestMapping("login")
    public String login(){ return "page/login.html"; }
    @RequestMapping("user")
    public String user(){ return "page/user.html"; }
    @RequestMapping("account")
    public String account(){ return "page/account.html"; }
    @RequestMapping("manage")
    public String manage(){ return "page/manage.html"; }
}
