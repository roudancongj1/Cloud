package com.gao.service;

import com.gao.pojo.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2021/12/17 23:29
 */

public interface redisservice {

    List<user> getuserforcache();
    String getuserforcache2();
}
