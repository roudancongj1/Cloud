package com.sch.controller;

import com.alibaba.fastjson.JSON;
import com.sch.pojo.Card;
import com.sch.utils.CaptchaUtil;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
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
    private CaptchaUtil captchaUtil;

    @GetMapping("test")
    public Map test(){
        List l=new ArrayList();
        l.add("222222");
        l.add("333333");
        //return l.toString();
        Map map = new HashMap();
        map.put("info","啊大苏打");
        map.put("name","张三");



        redisUtil.set("wocao","dasda");
        return map;
    }

    @GetMapping("cardInfo")
    public ResultUtil cardInfo(){
        Card card1 = new Card("1ff2","23dss");
        Card card2 = new Card("21321","3232");
        Card card3 = new Card("2132221","32ds32");
        Card card4 = new Card("21341aa21","32432");
        ArrayList<Card> cards = new ArrayList<>();
        cards.add(card1);
        cards.add(card2);
        cards.add(card3);
        cards.add(card4);
        System.out.println(cards);
        System.out.println(JSON.toJSON(cards));
        return ResultUtil.ok().put(cards);
    }

    //@CrossOrigin
    @GetMapping("captcha")
    public void captcha(HttpServletResponse response){
        response.setHeader("Cache-Control", "no-store, no-cache");


        captchaUtil.Instance();
        BufferedImage image = captchaUtil.getImage();
        String str = captchaUtil.getStr();
        System.out.println("-----——————---获取验证码"+str+"---------------");
        try{
            ImageIO.write(image,"jpg",response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
