package com.sch.service;

import java.io.File;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/3/5 13:57
 */


public interface MailService {
    boolean sendOpinionMail(String sub, Map info);
}
