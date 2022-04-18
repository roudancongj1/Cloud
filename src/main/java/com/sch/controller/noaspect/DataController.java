package com.sch.controller.noaspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sch.dao.CityMapper;
import com.sch.dao.PlaceMapper;
import com.sch.dao.StaticMapper;
import com.sch.pojo.City;
import com.sch.pojo.HtmlInfo;
import com.sch.pojo.Place;
import com.sch.pojo.Static;
import com.sch.service.MailService;
import com.sch.serviceimpl.ExcelServiceImpl;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2021/12/31 14:02
 */

@RestController
@RequestMapping("/data")
public class DataController {

    private static final Logger log = LoggerFactory.getLogger(DataController.class);

    @Autowired
    private StaticMapper staticMapper;
    @Autowired
    private ExcelServiceImpl excelService;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private PlaceMapper placeMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MailService mailService;



    @GetMapping("static/{code}")
    public ResultUtil staticMessage(@PathVariable("code") String code) {
        try {
            List<Static> staticInfo = staticMapper.queryCode(code);
            return ResultUtil.ok().put(staticInfo);
        } catch (Exception e) {
            return ResultUtil.error("查询静态数据异常");
        }
    }


    @GetMapping("exportToday")
    public void exportToday(HttpServletResponse response){
        String exportName = LocalDate.now().toString();//.format("yyyy-MM-dd HH:mm:ss");
        String sheetName = "疫情风险表";
        String cellsArr[]={"省份","地区","地址","风险等级"};
        List<String> cellsList = List.of(cellsArr);

        excelService.exportExcelTemp(exportName,sheetName,cellsList,response);

    }

    @GetMapping("searchCity")
    public ResultUtil searchCity(@RequestParam String wrapper){
        try {
            List<City> cities = cityMapper.searchCityLike(wrapper);
            return ResultUtil.ok().put(cities);
        } catch (Exception e) {
            return ResultUtil.error("模糊查询失败");
        }
    }

    @GetMapping("simpleCityInfo")
    public ResultUtil simpleCityInfo(){
        try {
            List<City> cities = cityMapper.selectList(new QueryWrapper<>());
            return ResultUtil.ok().put(cities);
        } catch (Exception e) {
            return ResultUtil.error("查询城市信息失败");
        }
    }

    @GetMapping("simplePlaceInfo")
    public ResultUtil simplePlaceInfo(@RequestParam Integer mark){
        try {
            List<Place> cities = placeMapper.selectList(new QueryWrapper<Place>().eq("parent_mark",mark));
            return ResultUtil.ok().put(cities);
        } catch (Exception e) {
            return ResultUtil.error("查询子城市信息失败");
        }
    }


    @GetMapping("queryHtmlInfo")
    public ResultUtil queryHtmlInfo(){
        try {
            HtmlInfo info = JSON.parseObject(redisUtil.get("htmlInfo").toString(), HtmlInfo.class);

            return ResultUtil.ok().put(info);
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("查询失败");
        }
    }

    @GetMapping("sendMailCode")
    public ResultUtil sendMailCode(@RequestParam String mail){
        try {
            boolean state = mailService.sendMailCode(mail);
            if (state)
                return ResultUtil.ok("验证码发送成功");
            else
                return ResultUtil.error("验证码发送失败");
        } catch (Exception e) {
            e.printStackTrace();
            return ResultUtil.error("验证码发送异常");
        }
    }

    @GetMapping("exportTemplate")
    public void exportTemplate(@RequestParam String form,HttpServletResponse response){
        try {
            String exportName;
            String sheetName;
            List<String> cellHeader;
            boolean state = false;

            switch (form){
                case "city":
                    exportName = new String("城市模板".getBytes(),"ISO8859-1");
                    sheetName = "第一页";
                    cellHeader = List.of(new String[]{"城市名称", "风险等级","城市编号","简介","是否存在中高风险地","隔离天数"});
                    state = excelService.exportExcelTemp(exportName, sheetName, cellHeader, response);
                    break;
                case "feedback":
                    exportName = new String("反馈信息模板".getBytes(),"ISO8859-1");
                    sheetName = "第一页";
                    cellHeader = List.of(new String[]{"反馈用户", "反馈信息"});
                    state = excelService.exportExcelTemp(exportName, sheetName, cellHeader, response);
                    break;
                case "place":
                    exportName = new String("酒店模板".getBytes(),"ISO8859-1");
                    sheetName = "第一页";
                    cellHeader = List.of(new String[]{"城市标记", "酒店名称","酒店标记","酒店地址","酒店食物","酒店wifi","单日金额","酒店信息","区域负责人"});
                    state = excelService.exportExcelTemp(exportName, sheetName, cellHeader, response);
                    break;
                case "user":
                    exportName = new String("用户模板".getBytes(),"ISO8859-1");
                    sheetName = "第一页";
                    cellHeader = List.of(new String[]{"账号", "密码","用户名","性别","手机号码","行程地区","行程日期","用户角色"});
                    state = excelService.exportExcelTemp(exportName, sheetName, cellHeader, response);
                    break;
                case "static":
                    exportName = new String("静态数据模板".getBytes(),"ISO8859-1");
                    sheetName = "第一页";
                    cellHeader = List.of(new String[]{"静态数据识别码", "标签","值"});
                    state = excelService.exportExcelTemp(exportName, sheetName, cellHeader, response);
                    break;
                case "flow":
                    exportName = new String("订单流水模板".getBytes(),"ISO8859-1");
                    sheetName = "第一页";
                    cellHeader = List.of(new String[]{"流水号", "订单号","产品标记","支付金额","支付方式","购买数量"});
                    state = excelService.exportExcelTemp(exportName, sheetName, cellHeader, response);
                    break;
                case "orders":
                    exportName = new String("订单模板".getBytes(),"ISO8859-1");
                    sheetName = "第一页";
                    cellHeader = List.of(new String[]{"订单号","订单状态","订单金额","实际支付金额","产品标记","购买数量","支付时间"});
                    state = excelService.exportExcelTemp(exportName, sheetName, cellHeader, response);
                    break;
                default:
                    break;
            }
            if (state)
                log.info("下载了"+form+"模板");
            else
                log.error("下载"+form+"模板错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PostMapping("exportFormInfo")
    public void exportFromInfo(@RequestBody Map<String,Object> map, HttpServletResponse response){
        try {
            List<Object> objectList = (List<Object>) map.get("Info");

            boolean state = excelService.exportFormExcel(map.get("form").toString(),objectList,response);

            if (state)
                log.info("导出"+map.get("form").toString()+"表格成功");
            else
                log.error("导出"+map.get("form").toString()+"表格错误");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @RequestMapping("uploadExcel")
    public ResultUtil uploadExcel(@RequestParam(value = "uploadFile", required = false) MultipartFile file){
        boolean state = excelService.uploadExcel(file);
        if (state)
            return ResultUtil.ok("添加成功");
        else
            return ResultUtil.error("添加失败");
    }
}

