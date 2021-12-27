package com.gao.controller;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @Auth: Gao
 * @Date: 2021/12/27 0:16
 */
@RestController
@RequestMapping("excel")
public class excelcontroller {

    @RequestMapping("/export")
    public String getQxExcelTemplate(HttpServletResponse response) {
      //  HSSFWorkbook excelTemp = getQxExcelTemp();
       Workbook wb =  new XSSFWorkbook();
       //创建HSSFSheet对象
       Sheet sheet = wb.createSheet( "sheet0" );
       //创建HSSFRow对象
       Row row = sheet.createRow( 0 );
       //创建HSSFCell对象
       Cell cell=row.createCell( 0 );
       //设置单元格的值
       cell.setCellValue( "单元格中的中文" );

        String name = "qxtemp";
        return excelExport(name, response, wb);
    }

    public HSSFWorkbook getQxExcelTemp() {
        HSSFWorkbook wb = new HSSFWorkbook();
        try {
            List<String> columns = new ArrayList<>();
            columns.add("姓名");
            columns.add("工号");
            columns.add("权限");
            columns.add("组织");
            String str = "权限表";
            HSSFSheet sheet = getWorkbook(wb, str, columns);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return wb;
    }
    //ScheduleControl.java

    public HSSFSheet getWorkbook(HSSFWorkbook workbook, String sheetTitle, List<String> headers) {
        // 生成一个表格
        HSSFSheet sheet = workbook.createSheet(sheetTitle);
        // 设置表格默认列宽度为20个字节
        sheet.setDefaultColumnWidth(20);
        // 生成一个样式
        HSSFCellStyle style = workbook.createCellStyle();
        // 设置这些样式
        style.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
        style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style.setBorderBottom(BorderStyle.THIN);
        style.setBorderLeft(BorderStyle.THIN);
        style.setBorderRight(BorderStyle.THIN);
        style.setBorderTop(BorderStyle.THIN);
        style.setAlignment(HorizontalAlignment.forInt(1));
        // 生成一个字体
        HSSFFont font = workbook.createFont();
        font.setBold(false);
        font.setFontName("宋体");
        font.setColor(HSSFColor.WHITE.index);
        font.setFontHeightInPoints((short) 11);
        // 把字体应用到当前的样式
        style.setFont(font);
        // 生成并设置另一个样式
        HSSFCellStyle style2 = workbook.createCellStyle();
        style2.setFillForegroundColor(HSSFColor.WHITE.index);
        style2.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        style2.setBorderBottom(BorderStyle.THIN);
        style2.setBorderLeft(BorderStyle.THIN);
        style2.setBorderRight(BorderStyle.THIN);
        style2.setBorderTop(BorderStyle.THIN);
        style2.setAlignment(HorizontalAlignment.forInt(1));
        style2.setVerticalAlignment(VerticalAlignment.forInt(1));
        // 生成另一个字体
        HSSFFont font2 = workbook.createFont();
        font2.setBold(false);
        // 把字体应用到当前的样式
        style2.setFont(font2);

        // 产生表格标题行
        HSSFRow row = sheet.createRow(0);
        HSSFCell cellHeader;
        for (int i = 0; i < headers.size(); i++) {
            cellHeader = row.createCell(i);
            cellHeader.setCellStyle(style);
            cellHeader.setCellValue(new HSSFRichTextString(headers.get(i)));
        }



        return sheet;
    }



    private String excelExport(String name, HttpServletResponse response, Workbook scheduleDataExcel) {
        // 设置编码
        response.setContentType("application/ms-excel");
        response.addHeader("Content-Disposition", "attachment;filename=" + name + ".xlsx");
        try {
            scheduleDataExcel.write(response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  "导出成功" ;
    }


    /*解析excal ------头和行
    public static List<Map<Integer, String>> readExcel(MultipartFile file, Map<Integer, String> mapHead) {

        String fileName = file.getOriginalFilename();
        if (!fileName.matches("^.+\\.(?i)(xls)$") && !fileName.matches("^.+\\.(?i)(xlsx)$")) {
            //log.error("上传文件格式不正确");
            System.out.println("-------------------------上传格式不正确-------------------------------------");
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

                Map<Integer, List<Field>> reflectionMap = new HashMap<>(16);
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
                        Map<Integer, String> map1 = new LinkedHashMap<>();
                        for (int j = row.getFirstCellNum(); j < row.getLastCellNum(); j++) {
                            Cell cell = row.getCell(j);
                            String cellValue = getCellValue(cell);
                            map1.put(j, cellValue);
                        }
                        result.add(map1);
                    }
                }
            }
        } catch (Exception e) {
            //log.error(String.format("parse excel exception!"), e);
            System.out.println("parse excel exception!");
        } finally {
            if (workbook != null) {
                try {
                    workbook.close();
                } catch (Exception e) {
                    //log.error(String.format("parse excel exception!"), e);
                    System.out.println("parse excel exception!");
                }
            }
        }
        return result;
    }

    private static String getCellValue(Cell cell) {
        if (cell == null) {
            return "";
        }
        if (cell.getCellType() == Cell.CELL_TYPE_NUMERIC) {
            if (HSSFDateUtil.isCellDateFormatted(cell)) {
                return HSSFDateUtil.getJavaDate(cell.getNumericCellValue()).toString();
            } else {
                return new BigDecimal(cell.getNumericCellValue()).toString();
            }
        } else if (cell.getCellType() == Cell.CELL_TYPE_STRING) {
            return StringUtils.trimToEmpty(cell.getStringCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_FORMULA) {
            return StringUtils.trimToEmpty(cell.getCellFormula());
        } else if (cell.getCellType() == Cell.CELL_TYPE_BLANK) {
            return "";
        } else if (cell.getCellType() == Cell.CELL_TYPE_BOOLEAN) {
            return String.valueOf(cell.getBooleanCellValue());
        } else if (cell.getCellType() == Cell.CELL_TYPE_ERROR) {
            return "ERROR";
        } else {
            return cell.toString().trim();
        }
    */
}
