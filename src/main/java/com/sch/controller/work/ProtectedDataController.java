package com.sch.controller.work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sch.dao.*;
import com.sch.pojo.*;
import com.sch.service.ExcelService;
import com.sch.service.MailService;
import com.sch.serviceimpl.ExcelServiceImpl;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import com.sch.utils.ThreadLocalUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.*;

/**
 * @Auth: Gao
 * @Date: 2022/2/15 18:28
 */

@RestController
public class ProtectedDataController {

    private static final String CITY="city";
    private static final String FEEDBACK="feedback";
    private static final String PLACE="place";
    private static final String USER="user";
    private static final String STATIC="static";
    private static final String FLOW="flow";
    private static final String ORDERS="orders";


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


    @GetMapping("queryExpect")
    public ResultUtil queryExpect(){
        try {
            JSONObject localUser = ThreadLocalUtil.getThreadLocalUser();
            User user = userMapper.queryNum(localUser.get("userNumber").toString());
            int expectId =user.getExpectId();
            Place place = placeMapper.selectOne(new QueryWrapper<Place>().eq("place_mark", expectId));
            return ResultUtil.ok().put(place);
        } catch (Exception e) {
            return ResultUtil.error("查询地方城市信息失败");
        }
    }

    @GetMapping("queryInfo/{from}")
    public ResultUtil queryInfo(@PathVariable("from") String from){
        try {

            List<Object> list = new ArrayList<>();
            switch (from){
                case CITY:
                    List<City> cities = cityMapper.selectList(new QueryWrapper<>());
                    list.addAll(cities);
                    break;
                case USER:
                    List<User> users = userMapper.selectList(new QueryWrapper<>());
                    //定义长度copy
                    //List<Object> list = Arrays.asList(new Object[list.size()]);
                    //Collections.copy(list,users);
                    //ArrayList clone
                    //(ArrayList<Object>)arraylist.clone(user);
                    //带有[]
                    //list = Collections.singletonList(users);
                    //list = Arrays.asList(users);
                    list.addAll(users);
                    break;
                case PLACE:
                    List<Place> places = placeMapper.selectList(new QueryWrapper<>());
                    list.addAll(places);
                    break;
                case STATIC:
                    List<Static> statics = staticMapper.selectList(new QueryWrapper<>());
                    list = new ArrayList<>(statics);
                    break;
                case FLOW:
                    List<Flow> flows = flowMapper.selectList(new QueryWrapper<>());
                    list.addAll(flows);
                    break;
                case FEEDBACK:
                    List<Feedback> feedbacks = feedbackMapper.selectList(new QueryWrapper<>());
                    list.addAll(feedbacks);
                    break;
                case ORDERS:
                    List<Orders> orders = ordersMapper.selectList(new QueryWrapper<>());
                    list.addAll(orders);
                    break;
                default:
                    return ResultUtil.error("表名称错误");
            }

            return ResultUtil.ok().put(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("查询信息失败");
        }
    }

    @GetMapping("queryLikeInfo")
    public ResultUtil queryLikeInfo(@RequestParam String from,@RequestParam String wrapper){
        try {
            List<Object> list = new ArrayList<>();
            switch (from){
                case CITY:
                    List<City> cities = cityMapper.selectList(new QueryWrapper<City>().like("city_name",wrapper));
                    list.addAll(cities);
                case USER:
                    List<User> users = userMapper.selectList(new QueryWrapper<User>().like("user_name",wrapper));
                    list.addAll(users);
                    break;
                case PLACE:
                    List<Place> places = placeMapper.selectList(new QueryWrapper<Place>().like("place_name", wrapper));
                    list.addAll(places);
                    break;
                case STATIC:
                    List<Static> statics = staticMapper.selectList(new QueryWrapper<Static>().like("static_label", wrapper));
                    list.addAll(statics);
                    break;
                case FLOW:
                    List<Flow> flows = flowMapper.selectList(new QueryWrapper<Flow>().like("flow_no", wrapper));
                    list.addAll(flows);
                    break;
                case FEEDBACK:
                    List<Feedback> feedbacks = feedbackMapper.selectList(new QueryWrapper<Feedback>().like("feedback_auth", wrapper));
                    list.addAll(feedbacks);
                    break;
                case ORDERS:
                    List<Orders> orders = ordersMapper.selectList(new QueryWrapper<Orders>().like("order_no",wrapper));
                    list.addAll(orders);
                    break;
                default:
                    return ResultUtil.error("查询条件错误");

            }

            return ResultUtil.ok().put(list);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("模糊查询失败");
        }
    }
}
