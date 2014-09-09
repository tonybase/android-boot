package io.ganguo.app.basic.util;

import android.text.TextUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;
import java.util.regex.Pattern;

/**
 * Created by Wilson on 14-8-21.
 */
public class StringUtils {
    public static final String VERSION_SEPERATOR = ".";
    private final static Pattern emailer = Pattern
            .compile("\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*");

    private final static Pattern mobiler = Pattern
            .compile("^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$");

    private final static ThreadLocal<SimpleDateFormat> dateFormater = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        }
    };

    private final static ThreadLocal<SimpleDateFormat> dateFormater2 = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }
    };

    /**
     * 将字符串转为日期类型
     *
     * @param sdate
     * @return
     */
    public static Date toDate(String sdate) {
        try {
            return dateFormater.get().parse(sdate);
        } catch (ParseException e) {
            return null;
        }
    }

    /**
     * 将日期类型转为字符串
     *
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        return dateFormater2.get().format(date);
    }

    /**
     * 将日期类型转为字符串
     *
     * @param date
     * @return
     */
    public static String dateTimeFormat(Date date) {
        return dateFormater.get().format(date);
    }

    /**
     * 比较两个日期相差的时间
     *
     * @param a
     * @param b
     * @return
     */
    public static long determineDate(String a, String b) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long daysBetween = 0;
        try {
            Date d1 = sdf.parse(a);
            Date d2 = sdf.parse(b);
            daysBetween = (d2.getTime() - d1.getTime() + 1000000)
                    / (3600 * 24 * 1000);
            ;
        } catch (Exception e) {
        }

        return daysBetween;
    }

    /**
     * 判断字符串是否为空
     *
     * @param str
     * @return
     */
    public static boolean isEmpty(String str) {
        return TextUtils.isEmpty(str) || TextUtils.isEmpty(str.trim());
    }

    /**
     * 判断是不是一个合法的电子邮件地址
     *
     * @param email
     * @return
     */
    public static boolean isEmail(String email) {
        if (isEmpty(email)) return false;
        return emailer.matcher(email).matches();
    }

    /**
     * 判断是不是一个合法的手机号码
     *
     * @param mobile
     * @return
     */
    public static boolean isMobile(String mobile) {
        if (isEmpty(mobile)) return false;
        return mobiler.matcher(mobile).matches();
    }

    public static boolean equalsString(String src, String target) {
        if (isEmpty(src) || isEmpty(target))
            return false;
        return src.equals(target);
    }

    /**
     * 字符串转整数
     *
     * @param str
     * @param defValue
     * @return
     */
    public static int toInt(String str, int defValue) {
        try {
            return Integer.parseInt(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * String to Long
     *
     * @param str
     * @return 转换异常返回 0
     */
    public static long toLong(String str, long defValue) {
        try {
            return Long.parseLong(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * String to Double
     *
     * @param str
     * @return 转换异常返回 0
     */
    public static double toDouble(String str, double defValue) {
        try {
            return Double.parseDouble(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * String to Float
     *
     * @param str
     * @return 转换异常返回 0
     */
    public static float toFloat(String str, float defValue) {
        try {
            return Float.parseFloat(str);
        } catch (Exception e) {
        }
        return defValue;
    }

    /**
     * 如果含小数位 输出小数位，否则去掉小数位
     *
     * @param price
     * @return
     */
    public static String getPrettyPrice(float price) {
        int intPrice = (int) price;
        if ((price - intPrice) == 0f) {
            return String.valueOf(intPrice);
        }
        return String.valueOf(price);
    }

    /**
     * 分割符构成List<String>，做测试数据很方便
     *
     * @param str
     * @param seperator
     * @return
     */
    public static List<String> stringToList(String str, String seperator) {
        List<String> itemList = new ArrayList<String>();
        if (isEmpty(str)) {
            return itemList;
        }
        StringTokenizer st = new StringTokenizer(str, seperator);
        while (st.hasMoreTokens()) {
            itemList.add(st.nextToken());
        }

        return itemList;
    }

}
