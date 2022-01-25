package com.sch.controller.data;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sch.dao.StaticMapper;
import com.sch.dao.UserMapper;
import com.sch.pojo.Captcha;
import com.sch.pojo.Static;
import com.sch.pojo.TokenEntity;
import com.sch.pojo.User;
import com.sch.service.CaptchaUtilService;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import com.sch.utils.ThreadLocalUtil;
import com.sch.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 14:02
 */

@RestController
@RequestMapping("/data")
public class DataController {


    @Autowired
    private StaticMapper cardMapper;



    @GetMapping("cardInfo")
    public ResultUtil cardInfo() {
        List<Static> statics = cardMapper.qureyAll();
        return ResultUtil.ok().put(statics);
    }







}

