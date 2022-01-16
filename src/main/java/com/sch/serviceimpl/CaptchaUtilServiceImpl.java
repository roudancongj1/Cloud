package com.sch.serviceimpl;

import com.sch.pojo.Captcha;
import com.sch.service.CaptchaUtilService;
import com.sch.utils.CaptchaUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auth: Gao
 * @Date: 2022/1/16 19:20
 */

@Service
public class CaptchaUtilServiceImpl implements CaptchaUtilService {
    @Autowired
    private CaptchaUtil captchaUtil;
    //获取验证码
    public Captcha getCaptcha(){
        captchaUtil.init();

        Captcha captcha = new Captcha();

        captcha.setCode(captchaUtil.getCode());
        captcha.setImage(captchaUtil.getImage());

        return captcha;
    }
}
