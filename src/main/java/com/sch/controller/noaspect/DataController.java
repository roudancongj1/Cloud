package com.sch.controller.noaspect;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sch.dao.CityMapper;
import com.sch.dao.PlaceMapper;
import com.sch.dao.StaticMapper;
import com.sch.pojo.City;
import com.sch.pojo.Place;
import com.sch.pojo.Static;
import com.sch.serviceimpl.ExcelServiceImpl;
import com.sch.utils.ResultUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    @Autowired
    private ExcelServiceImpl excelService;
    @Autowired
    private CityMapper cityMapper;
    @Autowired
    private PlaceMapper placeMapper;



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

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < cellsList.size(); i++) {
            row.createCell(i).setCellValue(cellsList.get(i));
        }

        response.setContentType("application/ms-excel");
        response.addHeader("Content-Disposition", "attachment;filename=" + exportName + ".xlsx");
        try {
            workbook.write(response.getOutputStream());
//
        } catch (Exception e) {
            e.printStackTrace();
            //log.error("导出文件流异常");
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
    public ResultUtil simplePlaceInfo(Integer pId){
        try {
            List<Place> cities = placeMapper.selectList(new QueryWrapper<Place>().eq("parent_id",pId));
            return ResultUtil.ok().put(cities);
        } catch (Exception e) {
            return ResultUtil.error("查询子城市信息失败");
        }
    }


}

