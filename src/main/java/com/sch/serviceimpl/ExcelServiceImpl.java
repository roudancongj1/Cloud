package com.sch.serviceimpl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sch.dao.*;
import com.sch.pojo.City;
import com.sch.service.ExcelService;
import com.sch.service.MailService;
import com.sch.utils.RedisUtil;
import com.sch.utils.ResultUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.net.http.HttpResponse;
import java.util.*;

import static org.apache.poi.ss.usermodel.CellType.BLANK;

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
    @Transactional(rollbackFor = Exception.class)
    public boolean uploadExcel(MultipartFile file) {

        try {
            Map<Integer, String> mapHead = new LinkedHashMap<>();
            List<Map<Integer, String>> list = readExcel(file, mapHead);

            if (CollectionUtils.isEmpty(list)) {
                return false;
            }

            list.forEach((map)->{
                City city = new City();
                city.setCityName(map.get(0));
                city.setCityLv(Integer.valueOf(map.get(1)));
                city.setChildMark(Integer.valueOf(map.get(2)));
                city.setCityInfo(map.get(3));
                city.setCityRisk(Integer.valueOf(map.get(4)));
                city.setCityDays(Integer.valueOf(map.get(5)));
                cityMapper.insert(city);
            });


            return true;
        } catch (Exception e) {
            return false;
        }
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

    private ResultUtil excelExport(String exportName,XSSFWorkbook workbook,HttpServletResponse response){
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

    private List<Map<Integer, String>> readExcel(MultipartFile file, Map<Integer, String> mapHead) {

        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            log.error("上传文件格式不正确");
        }
        List<Map<Integer, String>> result = new ArrayList<>();

        Workbook workbook = null;
        try {
            InputStream is = file.getInputStream();
            if (fileName.endsWith(EXCEL2007)) {
                workbook = new XSSFWorkbook(is);
            }
            if (fileName.endsWith(EXCEL2003)) {
                workbook = new HSSFWorkbook(is);
            }
            if (workbook != null) {

                Sheet sheet = workbook.getSheetAt(0);
                boolean firstRow = true;
                for (int i = sheet.getFirstRowNum(); i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (firstRow) {
                        for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            String cellValue = getCellValue(cell);
                            if (StringUtils.isNotEmpty(cellValue)) {
                                mapHead.put(j, cellValue);
                            }
                        }
                        firstRow = false;
                    } else {
                        Map<Integer, String> rowMap = new LinkedHashMap<>();
                        for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            if (cell != null){
                                String cellValue = getCellValue(cell);
                                rowMap.put(j, cellValue);
                            }else {
                                rowMap.put(j, "");
                            }

                        }
                        result.add(rowMap);
                    }
                }
            }
        } catch (Exception e) {
            log.error(String.format("parse excel exception!"), e);
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    log.error(String.format("parse excel exception!"), e);
                }
            }
        }
        return result;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == CellType.NUMERIC) {
            if (DateUtil.isCellDateFormatted(cell)) {
                return DateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            } else {
                return new BigDecimal(cell.getNumericCellValue()).toString();
            }
        } else if (cell.getCellType() == CellType.STRING) {
            return StringUtils.trimToEmpty(cell.getStringCellValue());
        } else if (cell.getCellType() == CellType.FORMULA) {
            return StringUtils.trimToEmpty(cell.getCellFormula());
        } else if (cell.getCellType() == CellType.BLANK) {
            return "";
        } else if (cell.getCellType() == CellType.BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == CellType.ERROR) {
            return "ERROR";
        } else {
            //删除空白格
            return cell.toString().trim();
        }
    }

}
