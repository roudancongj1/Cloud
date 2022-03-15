package com.sch.serviceimpl;

import com.sch.service.MailService;

import com.sch.utils.MailMessageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/3/5 13:58
 */

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public boolean sendOpinionMail(String sub, Map info) {
        try {
            SimpleMailMessage message = MailMessageUtil.getOpinion(sub, info);

            mailSender.send(message);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


}
