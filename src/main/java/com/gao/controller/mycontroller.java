package com.gao.controller;

import com.gao.service.myser.myser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("aa")
public class mycontroller {
    @Autowired
    @Qualifier("myser2")
    private myser m;
    @RequestMapping("test")
    public String test(){

        return m.hello();
    }
}
