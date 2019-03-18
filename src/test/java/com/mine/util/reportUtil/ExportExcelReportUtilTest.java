package com.mine.util.reportUtil;

import com.google.common.collect.Lists;
import com.mine.BaseJunit4Test;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;

/**
 * @Description 报表生成测试
 * Created by jiangqd on 2019/1/13.
 */
public class ExportExcelReportUtilTest extends BaseJunit4Test {

    @Autowired
    private ExportExcelReportUtil exportExcelReportUtil;

    /** 测试生成Excel报表 **/
    @Test
    public void testProduceExcelReport() throws IOException {
        exportExcelReportUtil.exportExcelReport();
    }

    /** 测试报表工具类 **/
    @Test
    public void testCreateNormalHead() {
        exportExcelReportUtil.createExcelObj("测试报表");
        exportExcelReportUtil.createNormalHead("测试报表第一行",4, 0);
        exportExcelReportUtil.createNormalTwoRow(new String []{"2018","2019"},4,1);
        exportExcelReportUtil.createColumHeader(new String []{"合同编号", "合同名称", "项目名称", "业务类型", "合同类型"}, 2);

        String[] arr1 = new String[]{"1", "测试合同1", "项目1", "业务类型", "合同类型"};
        String[] arr2 = new String[]{"2", "测试合同2", "项目2", "业务类型", "合同类型"};
        String[] arr3 = new String[]{"3", "测试合同3", "项目3", "业务类型", "合同类型"};
        ArrayList<String[]> list = Lists.newArrayList(arr1,arr2,arr3);
        for (int i = 3; i<list.size()+3; i++) {
            String[] arr = list.get(i-3);
            HSSFRow row = exportExcelReportUtil.getSheet().createRow(i);
            for (int j = 0;j<arr.length; j++) {
                exportExcelReportUtil.createCell(row,j, HSSFCellStyle.ALIGN_CENTER,arr[j]);
            }
        }
        String path = "D:\\测试文件夹\\";
        exportExcelReportUtil.exportExcel(path+ "测试.xls");
    }
}
