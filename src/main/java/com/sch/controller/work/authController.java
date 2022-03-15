package com.sch.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sch.dao.CityMapper;
import com.sch.dao.UserMapper;
import com.sch.pojo.City;
import com.sch.pojo.User;
import com.sch.service.MailService;
import com.sch.utils.ResultUtil;
import com.sch.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/3/5 14:01
 */

@RestController
public class authController {
    private static final Logger log = LoggerFactory.getLogger(authController.class);

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MailService mailService;
    @Autowired
    private CityMapper cityMapper;

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

        JSONObject userThread = ThreadLocalUtil.getThreadLocalUser();
        User user = userMapper.queryNum(userThread.get("userNumber").toString());

        map.put("userNumber",user.getUserNumber());

        if (user.getFeedbackNum() == 0){
            return ResultUtil.error("每个用户每天最多发送三次");
        }else {

            Map updateInfo = new HashMap();
            updateInfo.put("userNumber",user.getUserNumber());
            updateInfo.put("feedbackNum",user.getFeedbackNum()-1);

            boolean judge = mailService.sendOpinionMail(map.get("mail"),map);
            log.info(judge ?"邮件成功发送":"邮件发送失败");
            if (judge){
                userMapper.updateFdNum(updateInfo);
                return ResultUtil.ok("邮件已发送");
            }
            else
                return ResultUtil.error("邮件发送失败");
        }
    }

    @PostMapping("addCity")
    public ResultUtil addCity(){
        try {
            City city = new City();
            city.setCityDays(22);
            city.setCityLv(2);
            city.setCityInfo("是一个好地方");
            city.setCityName("大连");
            city.setCityRisk(1);
            city.setChildMark(100);
            cityMapper.insert(city);
            return ResultUtil.ok("添加成功");
        } catch (Exception e) {
            return ResultUtil.error("添加失败");
        }
    }

    @PostMapping ("updateExpect")
    public ResultUtil updateExpect(@RequestParam Integer expectId){
        JSONObject user = ThreadLocalUtil.getThreadLocalUser();
        try {
            userMapper.updateEID(expectId,user.get("userNumber").toString());
            return ResultUtil.ok("更新成功");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("更新购物车失败");
        }
    }
}
