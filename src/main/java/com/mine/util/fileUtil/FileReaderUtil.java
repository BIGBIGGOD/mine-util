package com.mine.util.fileUtil;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

import org.apache.commons.lang3.StringUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/12/10 9:55
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
@Slf4j
public class FileReaderUtil {

    private final static String fileLocal = "C:/Users/Administrator/Desktop/aa.txt";
    private final static String productFile = "C:/Users/Administrator/Desktop/bb.txt";

    /**
     * 普通操作使用FileReader
     */
    public static void operateFileReader() {
        try {
            FileReader fileReader = new FileReader(new File(fileLocal));
            BufferedReader reader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(new File(productFile));
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    System.out.println(line);
                    writer.write("null");
                    writer.newLine();
                    i++;
                    continue;
                }
                System.out.println(line);
                writer.write(line);
                writer.newLine();
                i++;
            }

            reader.close();
            fileReader.close();
            writer.close();
            fileWriter.close();

            System.out.println(i);
        } catch (Exception e) {
            log.error("错误!!", e);
        }
    }

    /**
     * 设置字符流编码
     */
    public static void FileReaderEncode() {
        try {
            //理清继承关系：字符流底层使用字节流实现
            FileInputStream fis = new FileInputStream(new File(fileLocal));
            InputStreamReader fileReader = new InputStreamReader(fis, StandardCharsets.UTF_8);
            BufferedReader reader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(new File(productFile));
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    System.out.println(line);
                    writer.write("你哈啊");
                    writer.newLine();
                    i++;
                    continue;
                }
                System.out.println(line);
                writer.write(line);
                writer.newLine();
                i++;
            }

            reader.close();
            fileReader.close();
            writer.close();
            fileWriter.close();

            System.out.println(i);
        } catch (Exception e) {
            log.error("错误!!", e);
        }
    }

    public static void test() {
        try {
            FileReader fileReader = new FileReader(new File(fileLocal));
            BufferedReader reader = new BufferedReader(fileReader);
            FileWriter fileWriter = new FileWriter(new File(productFile));
            BufferedWriter writer = new BufferedWriter(fileWriter);
            String line = null;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (StringUtils.isBlank(line)) {
                    System.out.println(line);
                    writer.write("null");
                    writer.newLine();
                    i++;
                    continue;
                }
                String[] arr = line.split(",");
                int num = Integer.valueOf(arr[0]);
                int money = Integer.valueOf(arr[1]);
                int total = num * money;
                System.out.println(total);
                writer.write(line);
                writer.newLine();
                i++;
            }

            reader.close();
            fileReader.close();
            writer.close();
            fileWriter.close();

            System.out.println(i);
        } catch (Exception e) {
            log.error("错误!!", e);
        }
    }
}
