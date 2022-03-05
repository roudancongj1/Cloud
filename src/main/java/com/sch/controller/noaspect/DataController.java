package com.sch.controller.noaspect;

import com.sch.dao.StaticMapper;
import com.sch.pojo.Static;
import com.sch.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 14:02
 */

@RestController
@RequestMapping("/data")
public class DataController {


    @Autowired
    private StaticMapper staticMapper;

    @GetMapping("static/{code}")
    public ResultUtil cardInfo(@PathVariable("code") String code) {
        try {
            List<Static> staticInfo = staticMapper.queryCode(code);
            return ResultUtil.ok().put(staticInfo);
        } catch (Exception e) {
            return ResultUtil.error("查询静态数据异常");
        }
    }







}

