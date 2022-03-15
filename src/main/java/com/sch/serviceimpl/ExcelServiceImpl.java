package com.sch.serviceimpl;

import com.sch.service.ExcelService;
import com.sch.utils.ResultUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2022/3/10 14:17
 */

@Service
public class ExcelServiceImpl implements ExcelService {

    private static final Logger log = LoggerFactory.getLogger(ExcelServiceImpl.class);

    private final static String EXCEL2003 = "xls";
    private final static String EXCEL2007 = "xlsx";

    @Override
    public boolean uploadExcel() {
        return false;
    }

    @Override
    public boolean exportExcel(String exportName,String sheetName,List<String> cellHeader,HttpServletResponse response) {
        XSSFWorkbook workbook = new XSSFWorkbook();
        try {
            getSheet(sheetName,cellHeader,workbook);
            ResultUtil export = excelExport(exportName, response, workbook);

            if (export.get("code") == "0")
                return true;
            else
                return false;

        } catch (Exception e) {
            return false;
        }
    }

    private XSSFSheet getSheet(String sheetName,List<String> cellHeader,XSSFWorkbook workbook){
        XSSFSheet sheet = workbook.createSheet(sheetName);
        sheet.setDefaultColumnWidth(20);
        XSSFRow row = sheet.createRow(0);
        for (int i = 0; i < cellHeader.size(); i++) {
            row.createCell(i).setCellValue(cellHeader.get(i));
        }

        return sheet;
    }

    public ResultUtil excelExport(String exportName, HttpServletResponse response, XSSFWorkbook workbook){
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
