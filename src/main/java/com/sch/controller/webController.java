package com.sch.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 0:48
 */
@Controller
public class webController {
    @RequestMapping("/")
    public String index(){
        return "index";
    }
}
