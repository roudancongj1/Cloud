package com.sch.service;

import java.io.File;

/**
 * @Auth: Gao
 * @Date: 2022/3/5 13:57
 */


public interface MailService {
    boolean sendMail(String phone, String text,String sendTo);
}
