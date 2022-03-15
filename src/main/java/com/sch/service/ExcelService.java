package com.sch.service;

import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpResponse;
import java.util.List;

/**
 * @Auth: Gao
 * @Date: 2022/3/9 19:18
 */


public interface ExcelService {

    boolean uploadExcel();

    boolean exportExcel(String exportName, String sheetName, List<String> cellHeader, HttpServletResponse response);

}
