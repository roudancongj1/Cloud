package com.sch.controller.noaspect;

import com.sch.pojo.Captcha;
import com.sch.service.CaptchaUtilService;
import com.sch.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;

/**
 * @Auth: Gao
 * @Date: 2022/1/25 21:01
 */

@RestController
public class CaptchaController {

    @Autowired
    private CaptchaUtilService captchaUtilService;
    @Autowired
    private RedisUtil redisUtil;

    //@CrossOrigin
    @GetMapping("captcha")
    public void captcha(HttpServletResponse response) {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("application/json;charset=UTF-8");

        Captcha captcha = captchaUtilService.getCaptcha();

        if(redisUtil.hasKey("captcha")){
            redisUtil.delete("captcha");
            redisUtil.set("captcha",captcha.getCode(),1);
        }else {
            redisUtil.set("captcha",captcha.getCode(),1);
        }

        //   InputStream stream = this.getClass().getResourceAsStream("CaptchaUtil.class");
        try {
            System.out.println("-----——————---获取验证码" + captcha.getCode() + "---------------");
            ImageIO.write(captcha.getImage(), "jpg", response.getOutputStream());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
