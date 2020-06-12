package com.mine.util.algorithmutil;

/**
 * @author jiangqingdong
 * @version v1.0
 * @Description 位运算符使用
 * @DATE 2020/3/30 19:54
 * @Copyright Copyright © 2019 深圳花儿绽放网络科技股份有限公司. All rights reserved.
 */
public class OperationPointUtil {
    public static void main(String[] args) {
        /**
         * ~ 运算符,~是对位求反 1变0， 0变1
         */
        System.out.println(~1);
        /**
         * 移位运算符移位运算符把位按指定的值向左或向右移动
         * << 向左移动， 而 >> 向右移动，超过的位将丢失，而空出的位则补0
         * <<可以用来做乘法运算
         * >>可以用来做除法运算
         */
        System.out.println(2 << 1);
        System.out.println(3 << 1);
        System.out.println(4 << 1);
        System.out.println(4 >> 1);
        /**
         * 任何小数 把它 >> 0可以取整
         */
        System.out.println(314159 >> 1);
        /**
         *^运算符有个神奇的特性,用于两个数交换
         * ^运算符表示异或运算，通过异或运算翻转对应位，如：X=10101110，使X低4位翻转，用X ^0000 1111 = 1010 0001即可得到
         */
        int n1 = 3;
        int n2 = 4;
        System.out.println(n1 + ":" + n2);
        n1 ^= n2;
        System.out.println(n1 + ":" + n2);
        n2 ^= n1;
        System.out.println(n1 + ":" + n2);
        n1 ^= n2;
        System.out.println(n1 + ":" + n2);

        //101----
        System.out.println(5 >> 1);
        System.out.println(5 >>> 4);

        /**
         * &与运算符的使用,|运算符同理
         * &是将两数转成64为二进制，然后同位置全为1则为1，否则取反为0
         * 当数为负数时需要将数的绝对值转成64为二进制，然后取反加1得到其负数的二进制
         *  负数转二进制如：
         *      1.先将-5的绝对值转换成二进制，即为0000 0101；
         *     2.然后求该二进制的反码，即为 1111 1010；
         *     3.最后将反码加1，即为：1111 1011
         */
        System.out.println(2 & -1);

    }
}
