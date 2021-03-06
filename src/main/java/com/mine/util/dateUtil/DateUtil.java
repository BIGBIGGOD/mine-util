package com.mine.util.dateUtil;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by jiangqd on 2018/12/26.
 */
public class DateUtil {

    /**
     * 设置时间格式
     */
    private static String pattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * SimpleDateFormat线程不安全，所以创建ThreadLocal,同时重写初始化方法，返回一个map集合
     * 调用 format 方法时，多个线程会同时调用 calendar.setTime 方法，可能一个线程刚设置好 time 值另外的一个线程马上把设置的 time 值给修改了导致返回的格式化时间可能是错误的
     */
    private static ThreadLocal<Map<String, SimpleDateFormat>> dateFormatMap = ThreadLocal.withInitial(() -> {
        //初始化一个HashMap，避免空指针异常
        System.out.println(Thread.currentThread().getName() + "init pattern:" + Thread.currentThread());
        return new HashMap<String, SimpleDateFormat>(1);
    });

    /**
     * 获取SimpleDateFormat对象
     *
     * @param pattern
     * @return
     */
    private static SimpleDateFormat getSdf(String pattern) {
        Map<String, SimpleDateFormat> sdfMap = dateFormatMap.get();
        SimpleDateFormat sdf = sdfMap.get(pattern);
        //如果sdf为空，新创建一个sdf并放入map中
        if (sdf == null) {
            System.out.println(Thread.currentThread().getName() + "put new SimpleDateFormat of pattern" + pattern + "to map");
            sdf = new SimpleDateFormat(pattern);
            sdfMap.put(pattern, sdf);
        }
        return sdf;
    }


    /**
     * 根据时间返回字符串
     *
     * @param date
     * @return
     */
    public static String getDateStr(Date date) {
        SimpleDateFormat sdf = getSdf(pattern);
        return sdf.format(date);
    }

    public static String getDateStr(Date date, String pattern) {
        SimpleDateFormat sdf = getSdf(pattern);
        return sdf.format(date);
    }

    /**
     * @param date
     * @return
     * @throws ParseException
     */
    public static Date getDate(String date) throws ParseException {
        return getSdf(pattern).parse(date);
    }

    public static Date getDate(String date, String pattern) throws ParseException {
        return getSdf(pattern).parse(date);
    }

    /**
     * 根据时间返回是否超过7天的判断结果
     *
     * @param createTime
     * @return
     * @throws ParseException
     */
    public static boolean getRes(String createTime) throws ParseException {
        return (System.currentTimeMillis() - getDate(createTime).getTime()) > (7 * 24 * 60 * 60 * 1000);

    }

    /**
     * 根据时间分别获取当前的年月日时分秒以及分别设置年月日时分秒
     *
     * @param date
     * @return
     * @throws ParseException
     */
    public static void getVairousProperty(Date date) {
        //传入的时间
        String DateStr1 = getDateStr(date);
        Calendar calendar = Calendar.getInstance();
        //Date类型转换成Calendar类型
        calendar.setTime(date);
        //分别获取年月日时分秒
        Integer year = calendar.get(Calendar.YEAR);
        Integer month = calendar.get(Calendar.MONTH);
        Integer day = calendar.get(Calendar.DAY_OF_MONTH);
        Integer hour = calendar.get(Calendar.HOUR_OF_DAY);
        Integer minute = calendar.get(Calendar.MINUTE);
        Integer second = calendar.get(Calendar.SECOND);
        //获取当月的天数
        Integer dayOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        //分别设置年月日时分秒
        calendar.set(Calendar.YEAR, 1);
        //注意月份少一，以及周期是从星期天开始的
        calendar.set(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 1);
        calendar.set(Calendar.MINUTE, 1);
        calendar.set(Calendar.SECOND, 1);
        //设置并获取当月的上一月份
        calendar.add(Calendar.MONTH, -1);
        //设置为当月第一天
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //Calendar转换成Date类型
        Date newDate = calendar.getTime();
        //生成的时间
        String DateStr2 = getDateStr(newDate);
    }

}
