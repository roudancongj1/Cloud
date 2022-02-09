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
    private StaticMapper cardMapper;

    @GetMapping("cardInfo")
    public ResultUtil cardInfo() {
        List<Static> statics = cardMapper.qureyAll();
        return ResultUtil.ok().put(statics);
    }







}

