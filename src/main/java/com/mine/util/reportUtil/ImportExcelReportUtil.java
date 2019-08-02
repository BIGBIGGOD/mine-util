package com.mine.util.reportUtil;

import lombok.extern.slf4j.Slf4j;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Description 导入Excel文件Demo
 * Created by jiangqd on 2019/1/14.
 */
@Slf4j
public class ImportExcelReportUtil {

    public static void getDataFromExcel(String filePath) {

        //判断是否为excel类型文件
        if (!filePath.endsWith(".xls") && !filePath.endsWith(".xlsx")) {
            System.out.println("文件不是excel类型");
        }

        FileInputStream fis = null;
        Workbook workbook = null;

        try {
            //获取一个绝对地址的流
            fis = new FileInputStream(filePath);
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        try {
            //2003版本的excel，用.xls结尾
            workbook = new HSSFWorkbook(fis);//得到工作簿
        } catch (Exception ex) {
            log.info(ex.getMessage());
            try {
                //2007版本的excel，用.xlsx结尾
                workbook = new XSSFWorkbook(fis);//得到工作簿
            } catch (IOException e) {
                log.error(e.getMessage());
            }
        }

        //得到一个工作表
        Sheet sheet = workbook.getSheetAt(0);

        //获得表头
        Row rowHead = sheet.getRow(0);

        /**
         * getPhysicalNumberOfCells 是获取不为空的列个数。
         * getLastCellNum 是获取最后一个不为空的列是第几个。
         * row同cell一样有类似方法
         */
        //判断表头是否正确
        if (rowHead.getPhysicalNumberOfCells() != 1) {
            System.out.println("表头的数量不对!");
        }

        //获得数据的总行数
        int totalRowNum = sheet.getLastRowNum();

        //要获得属性
        String name = "";
        int latitude = 0;

        String[] arr = new String[]{"contractId", "contractName", "projectName", "serviceType", "contractType"};
        //将所有行的数据存储在一个List集合中
        List<Map<String, String>> dataList = new ArrayList();
        for (int i = 0; i <= totalRowNum; i++) {
            //获得第i行对象
            Row row = sheet.getRow(i);
            //当前row最后一个不为空的列
            int index = row.getLastCellNum();
            Cell cell;
            //当前row数据存储在一个map中
            Map<String, String> rowMap = new HashMap<>();
            for (int j = 0;j<index; j++){
                //获得获得第i行第j列的 String类型对象
                cell = row.getCell((short) j);
                rowMap.put(arr[j], cell.getStringCellValue());
            }
            System.out.println(rowMap);
            dataList.add(rowMap);

            //获得一个数字类型的数据
//            cell = row.getCell((short) 1);
//            latitude = (int) cell.getNumericCellValue();

        }
    }
}
