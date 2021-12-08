package com.gao.controller;

import com.gao.service.myser.myser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.util.HashMap;
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
    @RequestMapping("test2")
    public String test2(ServletRequest req, ServletResponse res) throws IOException, ServletException {
        Map m=new HashMap();
        m.put("name","张三");
        m.put("age","12");
        //res.sendRedirect("test3");
        req.setAttribute("msg",m);
        req.getRequestDispatcher("test3").forward(req,res);

        return null;
    }
    @RequestMapping("test3")
    public String test3(ServletRequest req, ServletResponse res){
        Map map=new HashMap();
        map.put("aaaa","bbbbb");
        req.getAttribute("msg");
        return req.getAttribute("msg").toString();
    }
}
