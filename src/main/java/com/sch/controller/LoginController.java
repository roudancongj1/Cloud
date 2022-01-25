package com.sch.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sch.dao.UserMapper;
import com.sch.pojo.TokenEntity;
import com.sch.pojo.User;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import com.sch.utils.ThreadLocalUtil;
import com.sch.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/1/25 21:36
 */

@RestController
public class LoginController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;

    @PostMapping("login")
    public ResultUtil login(@RequestBody User user) {
        User u = userMapper.selectOne(new QueryWrapper<User>().eq("user_number",user.getUserNumber()));

        String token= TokenUtil.getToken();

        TokenEntity entity = new TokenEntity();

        JSONObject jsonObject = JSON.parseObject(JSON.toJSONString(u));
        entity.setData(jsonObject);
        entity.setToken("token:"+token);

        redisUtil.set(token+":",entity);
        return ResultUtil.ok().put(u).put("token",token).put(u);
    }


}
