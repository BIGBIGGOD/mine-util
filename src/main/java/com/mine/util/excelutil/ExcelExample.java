package com.mine.util.excelutil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mine.common.BaseJunit4Test;
import com.mine.entity.User;
import com.mine.util.dateUtil.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/8/19 0019 10:25
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class ExcelExample extends BaseJunit4Test {

    @Test
    public void test() throws IOException {
        List<User> userList = new ArrayList<>();
        User user = new User().setName("xxx");
        userList.add(user);
        //新建流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        XlsGenerator generator = XlsGenerator.Builder.xlsxGenerator(byteArrayOutputStream, 50000);
        generator.selector("sheet", User.class).write(userList);
        generator.flush();
        //写入流结束
        //新建zip以及对应流
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ZipOutputStream zip = new ZipOutputStream(outputStream);
        //在zip中新建文件
        zip.putNextEntry(new ZipEntry("分销王报数" + DateUtil.getDateStr(new Date(), "yyyyMMdd") + ".xlsx"));
        //将之前的文件流写入zip
        IOUtils.write(byteArrayOutputStream.toByteArray(), zip);
        zip.closeEntry();
        IOUtils.closeQuietly(zip);
    }



//    /**
//     * controller层下载文件示例
//     *
//     * @param startTime 开始时间
//     * @param endTime   结束时间
//     * @return res
//     */
//    @RequestMapping("exportCollectionByTime")
//    @ResponseBody
//    public Result exportCollectionByTime(HttpServletResponse response, @DateTimeFormat(pattern = "yyyy-MM-dd") Date startTime, @DateTimeFormat(pattern = "yyyy-MM-dd") Date endTime) {
//        log.info("进入统计信息下载接口，参数startTime={}, endTime={}", startTime, endTime);
//        try {
//            List<ExportStatistics> exportStatisticsList = queryCollectionsService.exportCollectionByTime(startTime, endTime);
//            response.reset();
//            response.setContentType("application/vnd.ms-excel;charset=utf-8");
//            response.setHeader("Content-Disposition", "filename=" +
//                    new String("积分代发统计数据.xlsx".getBytes(StandardCharsets.UTF_8), StandardCharsets.ISO_8859_1));
//            OutputStream outputStream = response.getOutputStream();
//            int pageSize = 40000;
//            XlsGenerator generator = XlsGenerator.Builder.xlsxGenerator(outputStream, pageSize);
//            generator.selector("sheetA", ExportStatistics.class).write(exportStatisticsList);
//            generator.flush();
//            outputStream.flush();
//            return successResponse();
//        } catch (Exception e) {
//            log.info("统计信息下载接口出错，e=", e);
//            return failResponse(SERVER_ERROR.getCode(), SERVER_ERROR.getMessage());
//        }
//    }

}
