package com.sch.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 14:02
 */
@RestController
@RequestMapping("/data")
public class dataController {
    @GetMapping("test")
    public String test(){
        List l=new ArrayList();
        l.add("222222");
        l.add("333333");
        return l.toString();
    }
}
