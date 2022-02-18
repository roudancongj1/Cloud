package com.sch.controller.work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sch.dao.UserMapper;
import com.sch.pojo.User;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/2/15 18:28
 */

@RestController
public class ProtectedDataController {
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private UserMapper userMapper;
    @GetMapping("userInfo")
    public ResultUtil userInfo(HttpServletRequest request){
        String token=request.getHeader("token");
        try{
            if(redisUtil.hasKey(token)){
                JSONObject jsonObject= JSON.parseObject(JSON.toJSONString(redisUtil.get(token)));
                return ResultUtil.ok().put(jsonObject);
            }else {
                return ResultUtil.error("未登录,请登陆访问");
            }
        } catch (Exception e) {
            return ResultUtil.error("查询失败");
        }
    }


    @RequestMapping("pass")
    public ResultUtil updatepass(@RequestBody Map<String,Object> map){

        try {
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_number",map.get("userNumber")));
            if(map.get("userPass").equals(user.getUserPass())){
                user.setUserPass(map.get("newUserPass").toString());
                userMapper.update(user,new UpdateWrapper<User>().eq("user_number",map.get("userNumber")));
                return ResultUtil.ok("修改成功");
            }else {
                return ResultUtil.error("旧密码输入错误");
            }
        } catch (Exception e) {
            return ResultUtil.error("修改异常");
        }
    }
}
