package com.sch.serviceimpl;

import com.sch.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

import java.io.File;

/**
 * @Auth: Gao
 * @Date: 2022/3/5 13:58
 */

@Service
public class MailServiceImpl implements MailService {

    private static final String SUBJECT = " 向您提交了建议";
    @Autowired
    JavaMailSenderImpl mailSender;

    @Override
    public boolean sendMail(String mail, String text,String sendTo) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();

            mailMessage.setSubject(mail + SUBJECT);
            mailMessage.setText(text);

            mailMessage.setFrom("Yun_mic@126.com");
            mailMessage.setTo(sendTo);

            mailSender.send(mailMessage);

            return true;

        } catch (Exception e) {
            return false;
        }
    }
}
