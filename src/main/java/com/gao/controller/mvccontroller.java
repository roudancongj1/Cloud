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
        return "index4";
    }
    @RequestMapping("boot5")
    public String boot4(){
        return "index2";
    }
    @RequestMapping("boot4")
    public String boot5(){
        return "index3";
    }
    @RequestMapping("/")
    public String index(){
        return "index";
    }
    @RequestMapping("local")
    public String local(){
        return "indexforlocal";
    }
}
