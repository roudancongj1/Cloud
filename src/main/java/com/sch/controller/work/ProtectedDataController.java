package com.sch.controller.work;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sch.dao.CityMapper;
import com.sch.dao.PlaceMapper;
import com.sch.dao.UserMapper;
import com.sch.pojo.City;
import com.sch.pojo.Place;
import com.sch.pojo.User;
import com.sch.service.ExcelService;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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
    private CityMapper cityMapper;
    @Autowired
    private PlaceMapper placeMapper;




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

    @GetMapping("cityInfo")
    public ResultUtil cityInfo(){
        try {
            List<City> cities = cityMapper.selectList(new QueryWrapper<>());
            return ResultUtil.ok().put(cities);
        } catch (Exception e) {
            return ResultUtil.error("查询城市信息失败");
        }
    }

    @GetMapping("queryExpect")
    public ResultUtil queryExpect(){
        try {
            JSONObject user = ThreadLocalUtil.getThreadLocalUser();
            int expectId =(int) user.get("expectId");
            Place place = placeMapper.selectOne(new QueryWrapper<Place>().eq("place_id", expectId));
            return ResultUtil.ok().put(place);
        } catch (Exception e) {
            return ResultUtil.error("查询地方城市信息失败");
        }
    }





}
