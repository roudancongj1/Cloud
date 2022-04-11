package com.sch.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

/**
 * @Auth: Gao
 * @Date: 2022/3/13 15:33
 */

@Component
public class MailMessageUtil {

    private static final String OPINION= " 向您提交了建议";

    public static SimpleMailMessage getOpinion(String sub,Map info) {


        String sendTo = "Yun_mic@126.com";

        StringBuffer text = new StringBuffer();

        text.append("手机:" + info.get("phone") + "\n");
        text.append("地址:" + info.get("address") + "\n");
        text.append("账号:" + info.get("userNumber") + "\n");
        text.append("意见:" + info.get("opinion") + "\n");


        //String sendTo, String sub, String text
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("Yun_mic@126.com");

        mailMessage.setTo(sendTo);
        mailMessage.setSubject(sub+OPINION);
        mailMessage.setText(text.toString());

        return mailMessage;
    }

    public static SimpleMailMessage getMailCode(String mail){
        SimpleMailMessage mailMessage = new SimpleMailMessage();

        mailMessage.setFrom("Yun_mic@126.com");
        mailMessage.setTo(mail);

        mailMessage.setSubject("[光晕科技]请查收您的验证码,有效时间三分钟,若非本人操作请忽略");
        String s = UUID.randomUUID().toString().replaceAll("-", "").substring(0,4);
        mailMessage.setText(s);

        return mailMessage;
    }
}
