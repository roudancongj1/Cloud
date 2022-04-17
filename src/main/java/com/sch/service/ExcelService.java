package com.sch.service;



import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2022/3/9 19:18
 */


public interface ExcelService {

    boolean uploadExcel();

    boolean exportExcelTemp(String exportName, String sheetName, List<String> cellHeader, HttpServletResponse response);

    boolean exportFormExcel(String form,List<Object> objectList,HttpServletResponse response);

}
