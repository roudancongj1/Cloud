package com.sch.controller.work;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sch.dao.*;
import com.sch.pojo.*;
import com.sch.service.MailService;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import com.sch.utils.ThreadLocalUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.*;

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
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private FeedbackMapper feedbackMapper;
    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private StaticMapper staticMapper;
    @Autowired
    private FlowMapper flowMapper;
    @Autowired
    private OrdersMapper ordersMapper;

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

    @RequestMapping("updateSimpleUserInfo")
    public ResultUtil updateSimpleUserInfo(@RequestBody Map map){
        try {
            JSONObject localUser = ThreadLocalUtil.getThreadLocalUser();
            User user = userMapper.selectOne(new QueryWrapper<User>().eq("user_number", localUser.get("userNumber")));
            user.setUserName(map.get("userName").toString());
            user.setUserSex((int)map.get("userSex"));
            user.setUserPhone(map.get("userPhone").toString());
            user.setUserNumber(map.get("userNumber").toString());

            userMapper.updateForId((int)localUser.get("userId"),user);

            ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
            String token = requestAttributes.getRequest().getHeader("token");
            redisUtil.delete(token);
            return ResultUtil.ok("更新用户信息成功请重新登录");
        } catch (Exception e) {
            return ResultUtil.error("更新用户信息失败");
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

    @PostMapping("payTrip")
    public ResultUtil payTrip(String info,boolean imgVx){


        /*调用支付接口*/
        if(imgVx){

        }else {

        }

        /*判断支付成功*/
        /*发送短信导区域负责人*/
        return null;
    }

}
