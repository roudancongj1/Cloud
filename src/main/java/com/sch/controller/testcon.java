package com.sch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 0:34
 */
@Controller
@RequestMapping("aa")
public class testcon {
    @RequestMapping("test")
    public String test(){
        return "index";
    }
}
