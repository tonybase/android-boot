package io.ganguo.app.basic.core.cache;

/**
 * 缓存时间
 * <p/>
 * Created by zhihui_chen on 14-8-8.
 */
public class CacheTime {

    /**
     * 无缓存
     */
    public static final int NONE = 0;

    /**
     * 半分钟
     */
    public static final int HALF_MINUTE = 30000;

    /**
     * 一分钟
     */
    public static final int ONE_MINUTE = 2 * HALF_MINUTE;

    /**
     * 五分钟
     */
    public static final int FIVE_MINUTE = 5 * ONE_MINUTE;

    /**
     * 10分钟
     */
    public static final int TEN_MINUTE = 10 * ONE_MINUTE;

    /**
     * 15分钟
     */
    public static final int FIFTEEN_MINUTE = 15 * ONE_MINUTE;

    /**
     * 半个小时
     */
    public static final int HALF_HOUR = 30 * ONE_MINUTE;

    /**
     * 一个小时
     */
    public static final int HOUR = 60 * ONE_MINUTE;

    /**
     * 一天
     */
    public static final int DAY = 24 * HOUR;

    /**
     * 一周
     */
    public static final int WEEK = 7 * DAY;

}
