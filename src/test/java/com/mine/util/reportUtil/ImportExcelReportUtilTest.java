package com.mine.util.reportUtil;

import com.mine.BaseJunit4Test;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * Created by jiangqd on 2019/1/15.
 */
@Slf4j
public class ImportExcelReportUtilTest extends BaseJunit4Test {

    @Test
    public void testGetDataFromExcel() {
        String path = "F:\\测试文件夹\\测试.xls";
        log.info(" path={}：",path);
        ImportExcelReportUtil.getDataFromExcel(path);
    }

}
