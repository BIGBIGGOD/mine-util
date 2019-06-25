package com.mine.util.javaToolUtil;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.RandomUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.*;

import java.util.Date;

/**
 * @Description commons.lang3工具包示例
 * Created by jiangqd on 2019/1/17.
 */
public class CommonsLang3 {

    /**
     * StringUtils工具类的使用
     */
    public static void testStringUtils() {

        /**
         * @Description 判断字符串
         */
        String str = "ceshizifu";

        // 字符串为空返回true
        boolean res1 = StringUtils.isEmpty(str);

        // 字符串不为空返回true
        boolean res2 = StringUtils.isNotEmpty(str);

        // 多个字符串中包含一个null就返回true
        boolean res3 = StringUtils.isAnyEmpty(str, "");

        // 多个字符串都不为空则返回true
        boolean res4 = StringUtils.isNoneEmpty(str, "000");

        // 当字符串单独为null或者为"\n"等这样类似的转义字符组合时返回true
        boolean res5 = StringUtils.isBlank("\n555555555");

        // 当字符串不单独为null或者不是"\n"等这样类似的转义字符组合时返回true
        boolean res6 = StringUtils.isNotBlank("");

        // 当多个字符串只要有一个单独为null或者为"\n"等这样类似的转义字符组合时返回true
        boolean res7 = StringUtils.isAnyBlank(str, "");

        // 当多个字符串没有有一个单独为null或者为"\n"等这样类似的转义字符组合时返回true
        boolean res8 = StringUtils.isNoneBlank(str, "");

        // 删除字符串开头或者结尾的空白字符
        String res9 = StringUtils.trim("  5 5  ");

        // 当字符串全部为空白字符的时候返回null
        String res10 = StringUtils.trimToNull("              ");

        // 当字符串为空的时候返回空字符串，其它同trim一样
        String res11 = StringUtils.trimToEmpty(null);

    }

    /**
     * @Description RandomUtils产生数字随机数，RandomStringUtils产生数字和字母混合的随机数
     * nextBytes(int count) 返回一个指定大小的随机byte数组
     * nextDouble() 返回一个随机double值
     * nextDouble(double startInclusive, double endInclusive) 返回一个指定范围的随机double值
     * nextFloat() 返回一个随机float值
     * nextFloat(float startInclusive, float endInclusive) 返回一个指定范围的随机float值
     * nextInt() 返回一个随机int值
     * nextInt(int startInclusive, int endExclusive) 返回一个指定范围的随机int值
     * nextLong() 返回一个随机long值
     * nextLong(long startInclusive, long endExclusive) 返回一个指定范围的随机long值
     */
    public static void testRandom() {

        // nextBytes(int count) 返回一个指定大小的随机byte数组
        byte[] res12 = RandomUtils.nextBytes(10);

    }

    /**
     * @Description 数组操作工具方法
     */
    public static void testArrayUtils() {

        int[] nums1 = {1, 2, 3, 4, 5, 6};
        // 通过常量创建新数组
        int[] nums2 = ArrayUtils.EMPTY_INT_ARRAY;

        // 比较两个数组是否相等
        boolean res13 = ArrayUtils.isEquals(nums1, nums2);

        // 输出数组,第二参数为数组为空时代替输出
        String res14 = ArrayUtils.toString(nums1, "array is null");

        // 克隆新数组,注意此拷贝为深拷贝
        int[] res15 = ArrayUtils.clone(nums1);

        // 截取数组
        int[] res16 = ArrayUtils.subarray(nums1, 1, 2);

        // 判断两个数组长度是否相等
        boolean res17 = ArrayUtils.isSameLength(nums1, nums2);

        // 判断两个数组类型是否相等,注意int和Integer比较时不相等
        boolean res18 = ArrayUtils.isSameType(nums1, nums2);

        // 反转数组
        ArrayUtils.reverse(nums1);

        // 查找数组元素位置
        int res19 = ArrayUtils.indexOf(nums1, 5);

        // 查找数组元素最后出现位置
        int res20 = ArrayUtils.lastIndexOf(nums1, 4);

        // 查找元素是否存在数组中
        boolean res21 = ArrayUtils.contains(nums1, 2);

        // 将基本数组类型转为包装类型
        Integer[] res22 = ArrayUtils.toObject(nums1);

        // 判断是否为空,length=0或null都属于
        boolean res23 = ArrayUtils.isEmpty(nums1);

        // 并集操作,合并数组
        int[] res24 = ArrayUtils.addAll(nums1, nums2);

        // 增加元素,在下标5中插入10,注意此处返回是新数组
        int[] res25 = ArrayUtils.add(nums1, 5, 1111);

        // 删除指定位置元素,注意返回新数组,删除元素后面的元素会前移,保持数组有序
        int[] res26 = ArrayUtils.remove(nums1, 5);

        // 删除数组中值为10的元素,以值计算不以下标
        int[] res27 = ArrayUtils.removeElement(nums1, 10);

        System.out.println(res27);
    }

    /**
     * @Description DateFormatUtils时间处理工具类
     */
    public static void testDateFormatUtils() {

        Date date = new Date();
        System.out.println(date);
        String pattern = "yyyy-MM-dd HH:mm:ss";

        //格式化时间输出字符串
        //第一种方法,多次直接调用pattern
        String dateStr1 = DateFormatUtils.format(date, pattern);
        System.out.println(dateStr1);

        //第二种方法，多次直接调用fdf对象
        FastDateFormat fdf = FastDateFormat.getInstance(pattern);
        String dateStr2 = fdf.format(date);
        System.out.println(dateStr2);

        //转换时间字符串生成时间
        try {
            Date date1 = fdf.parse(dateStr1);
            System.out.println(date1);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * @Description 数值类型转化
     */
    public static void testNumValueTypeChange() {
        String str1 = "111";
        //String转数值类型
        Integer res1 = Integer.valueOf(str1);
        Integer res2 = Integer.parseInt(str1);
        //int转long
        Long res3 = res1.longValue();
    }

    public static void main(String[] args) {
        CommonsLang3.testStringUtils();
        CommonsLang3.testRandom();
        CommonsLang3.testArrayUtils();
        CommonsLang3.testDateFormatUtils();
    }

}
