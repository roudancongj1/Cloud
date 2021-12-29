package com.gao.controller;


import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gao.dao.dataDao;
import com.gao.pojo.data;
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
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
@RequestMapping("aa")
public class mycontroller {
    @Autowired
    @Qualifier("myser2")
    private myser m;
    @Autowired
    private redisservice rd;
    @Autowired
    private dataDao dD;
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
        //map.put("age","122");
        Map<String,user> mu=new HashMap<>();
        user u = new user();
        u.setId(11);
        u.setPassword("231312");
        u.setRole(2);
        mu.put("用户",u);
        return JSON.toJSONString(map);
        //return JSON.toJSONString(mu);
    }
    @RequestMapping("test8")
    public String test8(){
        String str="hello";
        return str;
    }
    @RequestMapping("test9")
    public List<user> test9(){
        List l=new ArrayList<>();
        l.add("aadd");
        l.add("ddcc");
        l.add("ccff");
        System.out.println(l.get(0));

        return rd.getuserforcache();
    }
    @RequestMapping("test10")
    public String test10(){
        return rd.getuserforcache2();
    }
    @RequestMapping("test11")
    public String[] test11(){
        String qx="按防腐剂-asdas-fwer-晒哎,";
      //  Pattern r = Pattern.compile("");
      //  // 现在创建 matcher 对象
      //  Matcher m = r.matcher(qx);
      //  if (m.find( )) {
      //      System.out.println("Found value: " + m.group(0) );
      //      System.out.println("Found value: " + m.group(1) );
      //      System.out.println("Found value: " + m.group(2) );
      //      System.out.println("Found value: " + m.group(3) );
      //  } else {
      //      System.out.println("NO MATCH");
      //  }

        //String str = "房估字(2014)第YPQD0006号";
        //
        //String jieguo = str.substring(str.indexOf("第")+1,str.indexOf("号"));

        List list = new ArrayList<>();
        String[] ss = qx.split("-");//表示用逗号进行拆分字符串  结果是一个字符串数组
        for (int i = 0; i < ss.length; i++) {
            System.out.println(ss[i]);
            list.add(ss[i]);
        }

        return ss;
    }
    @RequestMapping("test12")
    public String test12(){
        data d=dD.selectOne(new QueryWrapper<data>().eq("id","1"));
        System.out.println(d.getCity());
      // data dup=new data();
      // dup.setCity("水水水水");
      // dup.setEat("sssss");
      // dup.setWifi(32);
      // dD.insert(dup);
      // System.out.println("添加成功");
        //dD.selectOne(new QueryWrapper<data>().eq("wifi",32)).getCity();
        return "添加成功";
    }
    @RequestMapping("test13")
    public String test13(){
        List<data> ls=dD.selectall();
        for (data d:
             ls) {
            System.out.println(d.getCity());
        }
        return "查询成功";
    }

}
