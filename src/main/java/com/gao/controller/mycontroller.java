package com.gao.controller;

import com.gao.service.myser.myser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("aa")
public class mycontroller {
    @Autowired
    @Qualifier("myser2")
    private myser m;
    @RequestMapping("test")
    public String test(){

        System.out.println(m.selecttwo());

        //System.out.println(m.selecttwo().toString());

        String str=m.selecttwo().toString().replaceAll("name","名字");
        str=str.replaceAll("kuck","kuck1");
        str=str.replaceAll("id=2","wocao");
        System.out.println(str);
        return m.selecttwo().toString();
        //return m.selecttwo();
        //return m.hello();
    }
}
