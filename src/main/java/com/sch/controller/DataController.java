package com.sch.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sch.dao.StaticMapper;
import com.sch.dao.UserMapper;
import com.sch.pojo.Captcha;
import com.sch.pojo.Static;
import com.sch.pojo.User;
import com.sch.service.CaptchaUtilService;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 14:02
 */

@RestController
@RequestMapping("/data")
public class DataController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CaptchaUtilService captchaUtilService;
    @Autowired
    UserMapper userMapper;
    @Autowired
    StaticMapper cardMapper;


    @GetMapping("test")
    public ResultUtil test(){
        List l=new ArrayList();
        l.add("222222");
        l.add("333333");
        //return l.toString();
        Map map = new HashMap();
        map.put("info","啊大苏打");
        map.put("name","张三");
        User u=userMapper.selectOne(new QueryWrapper<User>().eq("user_id","1"));
        redisUtil.set("wocao","dasda");
        return ResultUtil.ok().put(map).put(u);
    }

    @GetMapping("cardInfo")
    public ResultUtil cardInfo(){
        List<Static> statics=cardMapper.qureyAll();
        return ResultUtil.ok().put(statics);
    }

    //@CrossOrigin
    @GetMapping("captcha")
    public void captcha(HttpServletResponse response){
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("application/json;charset=UTF-8");

        Captcha captcha = captchaUtilService.getCaptcha();

        try {
            System.out.println("-----——————---获取验证码"+captcha.getCode()+"---------------");
            ImageIO.write(captcha.getImage(),"jpg",response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
        // captchaUtil.Instance();
     // BufferedImage image = captchaUtil.getImage();
     // String str = captchaUtil.getStr();
     //  System.out.println("-----——————---获取验证码"+str+"---------------");
     //  try{
     //      ImageIO.write(image,"jpg",response.getOutputStream());
     //  } catch (IOException e) {
     //      e.printStackTrace();
     //  }

    }
}
