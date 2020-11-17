package com.mine.utils.testutil;

import java.io.*;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * io流测试
 * Created by jiangqd on 2019/1/12.
 */
@Slf4j
public class IoTest {

    @Test
    public void test2() throws IOException {
        File file = new File("D:\\测试文件夹\\incubator-dubbo-ops\\pom.xml");
        FileInputStream fis = new FileInputStream(file);
        FileOutputStream fos = new FileOutputStream("D:\\测试文件夹\\pom.xml");
        byte[] b = new byte[(int) file.length()];
        int res = 0;
        fis.read(b);
        fos.write(b);
//        if ((res=fis.read()) != -1){
//            fos.write(b, 0, res);
//        }
        fos.close();
        fis.close();
    }

    @Test
    public void test3() throws IOException {
        File file = new File("D:\\测试文件夹\\incubator-dubbo-ops\\pom.xml");
        FileReader fr = new FileReader(file);
        char[] c = new char[(int) file.length()];
        fr.read(c);
        FileWriter fw = new FileWriter("D:\\测试文件夹\\pom.xml");
        fw.write(c);
        fw.close();
        fr.close();
    }

    @Test
    public void test4() throws FileNotFoundException {
        File file = new File("D:\\测试文件夹\\incubator-dubbo-ops\\pom.xml");
        BufferedReader br = new BufferedReader(new FileReader(file));
    }

}
