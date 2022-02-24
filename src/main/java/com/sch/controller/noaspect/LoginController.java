package com.sch.controller.noaspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sch.dao.UserMapper;
import com.sch.pojo.TokenEntity;
import com.sch.pojo.User;
import com.sch.pojo.UserForm;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import com.sch.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.*;

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
    public ResultUtil login(@RequestBody UserForm userForm) {
//        User u = userMapper.selectOne(new QueryWrapper<User>().eq("user_number",user.getUserNumber()));
       User u=userMapper.queryNum(userForm.getUserNumber());

        if(null == u){
            return ResultUtil.error("用户不存在");
        }

        if(!userForm.getUserPass().equals(u.getUserPass())){
            return ResultUtil.error("密码错误");
        }
        if(null == redisUtil.get("captcha")){
            return ResultUtil.error("验证码已使用请勿重复登陆");
        }

        if(!userForm.getCaptcha().equals(redisUtil.get("captcha"))
                && !userForm.getCaptcha().equals(redisUtil.get("captcha").toString().toLowerCase(Locale.ROOT))){
            return ResultUtil.error("验证码错误或已过期");
        }

        String token= TokenUtil.getToken();

        TokenEntity entity = new TokenEntity();
        entity.setUserId(u.getUserId());
        entity.setUserName(u.getUserName());
        entity.setUserNumber(u.getUserNumber());
        entity.setUserRole(u.getUserRole());
        entity.setUserSex(u.getUserSex());
        entity.setUserTrip(u.getUserTrip());
        if(null != u.getUserTripTime()){
            entity.setUserTripTime(new SimpleDateFormat("yyyy-MM-dd").format(u.getUserTripTime()));
        }
        entity.setUserPhone(u.getUserPhone());

        redisUtil.set(token,entity);
        redisUtil.delete("captcha");
        return ResultUtil.ok().put(u).put("token",token).put(entity);
    }

    @GetMapping("logout")
    public ResultUtil logout(HttpServletRequest request){
        String token=request.getHeader("token");
        Boolean state=redisUtil.delete(request.getHeader("token"));
        if(state)
            return ResultUtil.ok("注销成功");
        else
            return ResultUtil.error("注销失败");
    }

    @GetMapping("visitor")
    public ResultUtil visitor(){

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        String token=requestAttributes.getRequest().getHeader("token");

        return ResultUtil.ok().put(!redisUtil.hasKey(token));

    }

    @RequestMapping("aa")
    public String aa(){
  //     Integer aa[]={1,3,-7,2,3,1,2,-2,1,2};

  //     ArrayList list = new ArrayList();

  //     for (int i = 0; i <aa.length ; i++) {
  //         int max=0;
  //         for (int j = i; j < aa.length; j++) {
  //                 max+=aa[j];
  //             list.add(max);
  //         }
  //     }
  //     System.out.println(Collections.max(list));
        Integer aa[]={1,2,3,4,5};
   //    for (int i = 0; i < aa.length; i++) {
   //        int x;
   //        if(aa[i]>aa[i+1]){
   //            x=aa[i];
   //            aa[i]=aa[i+1];
   //            aa[i+1]=x;
   //        }
   //    }
   //    int a=5;
   //    for (int i = 0; i < aa.length; i++) {
   //        if(aa[i]==a)
   //            System.out.println("包含"+ a);
   //    }

        HashMap map = new HashMap();
        map.put("aa",null);
        if (null == map.get("aa"))
            System.out.println("判断成功");
        else
            System.out.println("判断失败，空指针异常");

        int arr[]={1,2,12,12,12,1};
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < arr.length; i++) {
            list.add(arr[i]);
        }

        list.forEach(i ->{
            System.out.println(i.byteValue());
        });

        String dd[]={"as","sad"};

        User u1=new User();
        u1.setUserId(11);
        User u2=new User();
        u2.setUserId(22);
        User u[]={u1,u2};
        System.out.println(u[0]);






        return null;
    }





}
