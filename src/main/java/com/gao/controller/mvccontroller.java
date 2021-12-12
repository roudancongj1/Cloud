package com.gao.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Auth: Gao
 * @Date: 2021/12/12 0:59
 */
@Controller
public class mvccontroller {
    @RequestMapping("ele")
    public String ele(){
        return "index";
    }
    @RequestMapping("boot")
    public String boot(){
        return "index2";
    }
}
