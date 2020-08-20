package com.mine.utils.javaToolUtil;

import java.io.*;
import java.util.Collections;
import java.util.List;

import org.apache.commons.io.IOUtils;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2020/3/17 14:55
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class IOUtil {
    public static void main(String[] args) throws IOException {
        String path = "C:\\Users\\Administrator\\Desktop\\aa.txt";
        InputStream inputStream1 = IOUtils.toInputStream("content");
        InputStream inputStream2 = new FileInputStream(new File(path));
        List<String> strList = IOUtils.readLines(inputStream2, "UTF-8");

        FileOutputStream fileOutputStream = new FileOutputStream("C:\\Users\\Administrator\\Desktop\\ab.txt");
        IOUtils.writeLines(strList, "UTF-8", fileOutputStream);

        for (String str : strList) {
            byte[] arr = str.getBytes();
            inputStream2.read(arr);
            fileOutputStream.write(arr.length);
        }
    }
}
