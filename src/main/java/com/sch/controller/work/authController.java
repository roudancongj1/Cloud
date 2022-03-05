package com.sch.controller.work;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sch.dao.UserMapper;
import com.sch.pojo.User;
import com.sch.service.MailService;
import com.sch.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/3/5 14:01
 */

@RestController
public class authController {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;

    @RequestMapping("pass")
    public ResultUtil updatepass(@RequestBody Map<String,Object> map){

        try {
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_number",map.get("userNumber")));
            if(map.get("oldUserPass").equals(user.getUserPass())){
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


    @PostMapping("sendMail")
    public ResultUtil mail(@RequestBody Map<String,String> map){

        String sentTo="Yun_mic@126.com";
        boolean judge = mailService.sendMail(map.get("phone"),map.get("text"),sentTo);
        if (judge)
            return ResultUtil.ok("邮件已发送");
        else
            return ResultUtil.error("邮件发送失败");
    }


}
