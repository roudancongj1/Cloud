package com.gao.controller;

import ch.qos.logback.core.util.StringCollectionUtil;
import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gao.pojo.user;
import com.gao.service.myser.myser;
import com.gao.service.redisservice;
import com.gao.service.redisserviceimpl;
import com.mysql.cj.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.jackson.JsonObjectDeserializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
@RequestMapping("aa")
public class mycontroller {
    @Autowired
    @Qualifier("myser2")
    private myser m;
    @Autowired
    private redisservice rd;
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
    public Map test3(ServletRequest req, ServletResponse res) throws JsonProcessingException {
        Map map=new HashMap();
        map.put("aaaa","bbbbb");
        Object o=req.getAttribute("msg");
        ObjectMapper mapper = new ObjectMapper();

        Map m = mapper.readValue(mapper.writeValueAsString(o), Map.class);

        //对象 d = mapper.readValue(mapper.writeValueAsString(m), 对象.class);
        return m;
    }
    @RequestMapping("test4")
    public String test4(){
        Date date=new Date();
        //return test();
        SimpleDateFormat sdf=new SimpleDateFormat();
        sdf.applyPattern("yyyy-MM-dd HH:mm:ss");
        return sdf.format(date);
    }
    @RequestMapping("test5")
    public String test5(){
        Set set = new HashSet<>();
        set.add("aaaaaa");
        set.add("bbbbbb");
        set.add("aaaaaa");
        //return set.toString();
        set.forEach(System.out::println);
        Map m=new HashMap();
        m.put(set,set);
        return m.toString();
    }
    @RequestMapping("test6")
    public String test6(){
        user u = new user();
        u.setId(22);
        u.setPassword("2222");
        /*if (null==u.getRole()){
            return "s为空";
        }else {
            return "s不为空";
        }*/
        String s = "s为空的1111";
        if ("3".equals(u.getRole())){
            return "s为空";
        }else {
            return s;
        }
    }
    @RequestMapping("test7")
    public String test7(){
        Map map = new HashMap<>();
        map.put("name","张三");
        map.put("age","12");
        return JSON.toJSONString(map);
    }
    @RequestMapping("test8")
    public String test8(){
        String str="hello";
        return str;
    }
    @RequestMapping("test9")
    public List<user> test9(){

        return rd.getuserforcache();
    }
    @RequestMapping("test10")
    public String test10(){

        return rd.getuserforcache2();
    }
}
