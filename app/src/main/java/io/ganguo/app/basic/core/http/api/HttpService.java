package io.ganguo.app.basic.core.http.api;

import java.util.Map;

/**
 * Created by zhihui_chen on 14-8-4.
 */
public interface HttpService {

    /**
     * GET 网络请求
     *
     * @param url
     * @param parms
     * @param httpListener
     * @param cacheTime
     */
    public void doGet(String url, Map<String, String> parms, HttpListener<?> httpListener, int cacheTime);

    /**
     * POST 网络请求
     *
     * @param url
     * @param parms
     * @param httpListener
     * @param cacheTime
     */
    public void doPost(String url, Map<String, String> parms, HttpListener<?> httpListener, int cacheTime);
}
