package com.sch.utils;

import com.sch.pojo.HtmlInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Random;

/**
 * @Auth: Gao
 * @Date: 2022/4/9 15:40
 */

@Component
public class AddRandomHtmlUtil {

    @Autowired
    private RedisUtil redisUtil;

    public ResultUtil getInfo(){
        try {
            HtmlInfo info = new HtmlInfo();
            info.setAsymptomatic(getRandom()+12312);
            info.setCumulativeCure(getRandom()+314122422);
            info.setOverseasInput(getRandom());
            info.setExistingDiagnosis(getRandom());
            info.setCumulativeDeath(getRandom()+1231244);
            info.setCumulativeDiagnosis(getRandom()+23214515);

            redisUtil.set("htmlInfo",info,60*24);
            return ResultUtil.ok().put(info);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("获取信息失败");
        }
    }

    private int getRandom(){
        Random random = new Random();
        int i = random.nextInt(10000) + 20000;
        return i;
    }
}
