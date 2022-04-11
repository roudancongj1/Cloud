package com.sch.scheduled;

import com.sch.dao.UserMapper;
import com.sch.pojo.HtmlInfo;
import com.sch.utils.AddRandomHtmlUtil;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.HtmlUtils;

import java.util.Random;


/**
 * @Auth: Gao
 * @Date: 2022/3/8 13:30
 */
//@Slf4j
@RestController()
@RequestMapping("scheduled")
public class ScheduledTask {

    /**
     * trace
     * debug
     * info
     * warn
     * error
     */

    private final static Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AddRandomHtmlUtil randomHtmlUtil;

    @PostMapping("resetFdNum")
    @Scheduled(cron = "0 0 0 * * ?")
    public ResultUtil ResetFdNum(){
        try {
            int i = userMapper.resetFdNum(3);
            log.info("重置条数"+i);

        } catch (Exception e) {
            return ResultUtil.error("重置失败");
        }
        return ResultUtil.ok("重置成功");
    }

    @PostMapping("addRandom")
    @Scheduled(cron = "0 0 0 * * ?")
    public ResultUtil addRandom(){
        try {
            return randomHtmlUtil.getInfo();
        } catch (Exception e) {
            return ResultUtil.error("更新疫情信息失败");
        }
    }
}
