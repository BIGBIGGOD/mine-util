package com.mine.utils.reportUtil;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * Created by jiangqd on 2019/1/15.
 */
@Slf4j
public class ImportExcelReportUtilTest {

    @Test
    public void testGetDataFromExcel() {
        String path = "F:\\测试文件夹\\测试.xls";
        log.info(" path={}：", path);
        ImportExcelReportUtil.getDataFromExcel(path);
    }

}
