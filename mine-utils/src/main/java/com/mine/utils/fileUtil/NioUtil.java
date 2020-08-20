package com.mine.utils.fileUtil;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description NIO基本使用
 * @DATE 2020/8/4 0004 15:44
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class NioUtil {

    private final static String FILE_LOCAL = "C:/Users/Administrator/Desktop/aa.txt";
    private final static String PRODUCT_FILE = "C:/Users/Administrator/Desktop/bb.txt";

    public static void main(String[] args) throws IOException {
//        writeNio1();
//        writeNio2();
        //读取文件
        BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_LOCAL)));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        //readLine按行读返回行结果，read按单字节读取
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        //只要建立的输出流就会把对应的输出路径指向的文件初始化，导致里面内容全部为空
        reader.close();
        System.out.println(stringBuilder);
        //新建字节缓冲区并初始化缓冲字节数据
        ByteBuffer buffer = ByteBuffer.allocate(48);
        FileOutputStream fileInputStream = new FileOutputStream(new File(PRODUCT_FILE));
        FileChannel channel = fileInputStream.getChannel();
        int i = channel.read(buffer);
//        channel.write(buffer);
//        channel.close();
//        fileInputStream.close();
    }

    public static void writeNio1() throws IOException {
        //读取文件
        BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_LOCAL)));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        //readLine按行读返回行结果，read按单字节读取
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        //只要建立的输出流就会把对应的输出路径指向的文件初始化，导致里面内容全部为空
        reader.close();
        System.out.println(stringBuilder);
        //新建指定大小的字节缓冲区
        ByteBuffer buffer = ByteBuffer.allocate(48);
        //建立输出流并获取对应通道
        FileOutputStream fileInputStream = new FileOutputStream(new File(PRODUCT_FILE));
        FileChannel channel = fileInputStream.getChannel();
        buffer.put(stringBuilder.toString().getBytes());
        //反转缓冲区读取位置，上一步put操作导致读取位置到了最后位置，不反转会导致channel写入的内容为空
        buffer.flip();
        channel.write(buffer);
        channel.close();
        fileInputStream.close();
    }

    public static void writeNio2() throws IOException {
        //读取文件
        BufferedReader reader = new BufferedReader(new FileReader(new File(FILE_LOCAL)));
        String line = null;
        StringBuilder stringBuilder = new StringBuilder();
        //readLine按行读返回行结果，read按单字节读取
        while ((line = reader.readLine()) != null) {
            stringBuilder.append(line);
        }
        //只要建立的输出流就会把对应的输出路径指向的文件初始化，导致里面内容全部为空
        reader.close();
        System.out.println(stringBuilder);
        //新建字节缓冲区并初始化缓冲字节数据
        ByteBuffer buffer = ByteBuffer.wrap(stringBuilder.toString().getBytes());
        FileOutputStream fileInputStream = new FileOutputStream(new File(PRODUCT_FILE));
        FileChannel channel = fileInputStream.getChannel();
        channel.write(buffer);
        channel.close();
        fileInputStream.close();
    }

}
