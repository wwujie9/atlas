package com.base.atlas.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author wujie
 * @date 2023/2/13 15:34
 */
public class DateUtil {
    /**
     * 时间格式(yyyy-MM-dd)
     */
    public final static String DATE_PATTERN = "yyyy-MM-dd";
    /**
     * 时间格式(yyyy-MM-dd HH:mm:ss)
     */
    public final static String DATE_TIME_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");


    // 把Data对象按照格式转成字符串
    public static String dateToStr(Date date, String pattern){

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        String strDate = sdf.format(date);

        return strDate;
    }

    // 把某种格式的字符串转成Date对象
    public static Date strToDate(String str, String pattern) throws ParseException {

        SimpleDateFormat sdf = new SimpleDateFormat(pattern);
        Date date = sdf.parse(str);
        // 返回Date对象
        return date;
    }

    // 将String yyyy-MM-dd HH:mm:ss 转换为 yyyy-MM-dd
    public static String dateTimeToDate(String dateTime) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat(DATE_TIME_PATTERN);
        SimpleDateFormat sdf2 = new SimpleDateFormat(DATE_PATTERN);
        Date date = sdf.parse(dateTime);
        String targetDate = sdf2.format(date);
        return targetDate;
    }

    // 通过Date提供的compareTo()进行比较
    // 通过Date提供的compareTo()进行比较
    // String beginTime = "2018-07-28 14:42:32"; String endTime = "2018-07-29 12:26:32";  -1
    // String beginTime = "2018-07-29 14:42:32"; String endTime = "2018-07-29 12:26:32";  1
    // 否则为0
    public static int compareDate(String beginTime, String endTime) throws ParseException {
        try {
            Date date1 = format.parse(beginTime);
            Date date2 = format.parse(endTime);

            int compareTo = date1.compareTo(date2);
//            boolean before = date1.before(date2); // 通过Date自带的before()或者after()方法比较

            return compareTo;

        } catch (ParseException e) {
            e.printStackTrace();
        }
        return 0;
    }


}
