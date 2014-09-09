package io.ganguo.app.basic.core.cache;

/**
 * 缓存接口
 *
 * Created by zhihui_chen on 14-8-14.
 */
public interface Cache {
    /**
     * 放入数据
     *
     * @param key
     * @param value
     */
    public void put(String key, String value);

    /**
     * 放入数据和缓存时间
     *
     * @param key
     * @param value
     * @param cacheTime
     */
    public void put(String key, String value, int cacheTime);

    /**
     * 获取缓存数据
     *
     * @param key
     * @return
     */
    public String getString(String key);
}
