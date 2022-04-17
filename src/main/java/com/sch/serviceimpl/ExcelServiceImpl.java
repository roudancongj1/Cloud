package com.sch.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sch.dao.*;
import com.sch.pojo.City;
import com.sch.service.ExcelService;
import com.sch.service.MailService;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/3/10 14:17
 */

@Service
public class ExcelServiceImpl implements ExcelService {

    private static final Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);

    private final static String EXCEL2003 = "xls";
    private final static String EXCEL2007 = "xlsx";

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private CityMapper cityMapper;
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

    @Override
    public boolean uploadExcel() {
        return false;
    }

    @Override
    public boolean exportExcelTemp(String exportName,String sheetName,List<String> cellHeader,HttpServletResponse response) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            getSheetHeader(sheetName,cellHeader,workbook);
            ResultUtil export = excelExport(exportName, workbook, response);

            if (export.get("code") == "0")
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public boolean exportFormExcel(String form,List<Object> objectList, HttpServletResponse response) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            String sheetName = "第一页";
            switch (form){
                case "city":
                    List<String> cellHeader = List.of(new String[]{"城市名称", "风险等级", "城市编号", "简介", "是否存在中高风险地", "隔离天数"});
                    XSSFSheet sheet = getSheetHeader(sheetName, cellHeader, workbook);

                    for (int i = 0; i <  objectList.size(); i++) {

                        Object o = objectList.get(i);
                        JSONObject jsonObject = new JSONObject((Map<String, Object>) o);
                        City city = JSON.parseObject(jsonObject.toJSONString(), City.class);

                        //从第一行开始创建
                        XSSFRow row = sheet.createRow(i+1);
                        row.createCell(0).setCellValue(city.getCityName());
                        row.createCell(1).setCellValue(city.getCityLv());
                        row.createCell(2).setCellValue(city.getChildMark());
                        row.createCell(3).setCellValue(city.getCityInfo());
                        row.createCell(4).setCellValue(city.getCityRisk());
                        row.createCell(5).setCellValue(city.getCityDays());
                    }
                    excelExport("城市信息表",workbook,response);
                    break;
                case "feedback":

                    break;
                case "place":

                    break;
                case "user":

                    break;
                case "static":

                    break;
                case "flow":

                    break;
                case "orders":

                    break;
                default:
                    return false;
            }
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private XSSFSheet getSheetHeader(String sheetName,List<String> cellHeader,XSSFWorkbook workbook){
        XSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < cellHeader.size(); i++) {
            row.createCell(i).setCellValue(cellHeader.get(i));
        }

        return sheet;
    }

    public ResultUtil excelExport(String exportName,XSSFWorkbook workbook,HttpServletResponse response){
        response.setContentType("application/ms-excel;charset=UTF-8");
        response.addHeader("Content-Disposition", "attachment;filename=" + exportName + ".xlsx");
        try {
            workbook.write(response.getOutputStream());
            return ResultUtil.ok();
        } catch (Exception e) {
            log.error("导出文件流异常");
            return ResultUtil.error("导出文件流异常");
        }
    }
}
