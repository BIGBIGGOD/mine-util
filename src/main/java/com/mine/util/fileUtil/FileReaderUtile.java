package com.mine.util.fileUtil;

import java.io.*;

import org.apache.commons.lang3.StringUtils;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description
 * @DATE 2019/12/10 9:55
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class FileReaderUtile {

    public static void FileReader() {
        String fileLocal = "C:/Users/Administrator/Desktop/aa.txt";
        String productFile = "C:/Users/Administrator/Desktop/bb.txt";
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
            e.printStackTrace();
        }
    }

    public static void test() {
        String fileLocal = "C:/Users/Administrator/Desktop/aa.txt";
        String productFile = "C:/Users/Administrator/Desktop/bb.txt";
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
                System.out.println(num*money);
                writer.write(num*money);
                writer.newLine();
                i++;
            }

            reader.close();
            fileReader.close();
            writer.close();
            fileWriter.close();

            System.out.println(i);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
