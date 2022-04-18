package com.sch.service;



import com.sch.utils.ResultUtil;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

import java.util.List;
import java.util.Map;

/**
 * @Auth: Gao
 * @Date: 2022/3/9 19:18
 */


public interface ExcelService {

    boolean uploadExcel(MultipartFile file);

    boolean exportExcelTemp(String exportName, String sheetName, List<String> cellHeader, HttpServletResponse response);

    boolean exportFormExcel(String form,List<Object> objectList,HttpServletResponse response);

}
