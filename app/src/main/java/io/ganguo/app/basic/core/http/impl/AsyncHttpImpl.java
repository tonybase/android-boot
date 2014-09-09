package io.ganguo.app.basic.core.http.impl;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONObject;

import java.util.Map;

import io.ganguo.app.basic.core.cache.CacheTime;
import io.ganguo.app.basic.core.http.api.AbstractHttpService;
import io.ganguo.app.basic.core.http.api.HttpListener;


/**
 * Created by zhihui_chen on 14-8-14.
 */
public class AsyncHttpImpl extends AbstractHttpService {
    private static final String TAG = VolleyImpl.class.getName();
    private AsyncHttpClient client = new AsyncHttpClient();

    /**
     * GET 网络请求 无缓存
     *
     * @param url
     * @param params
     * @param httpListener
     */
    @Override
    public void doGet(String url, Map<String, String> params, HttpListener<?> httpListener) {
        doGet(url, params, httpListener, CacheTime.NONE);
    }

    @Override
    public void doGet(String url, Map<String, String> params, HttpListener<?> httpListener, int cacheTime) {
        String urlWithParams = buildUrlParams(url, params);
        Log.d(TAG, "doGet: " + urlWithParams);
        // 是否已经有缓存了，存在自动返回缓存数据
        if (cacheTime != 0 && fireCache(url, httpListener)) return;

        // 已经有同一URL的请求了，等待自动响应
        if (addQequest(urlWithParams, httpListener)) return;

        doStringGet(urlWithParams, cacheTime);
    }

    /**
     * POST 网络请求
     *
     * @param url
     * @param httpListener
     */
    @Override
    public void doPost(String url, Map<String, String> params, final HttpListener<?> httpListener) {
        RequestParams requestParams = new RequestParams();
        for(String key : params.keySet()) {
            requestParams.add(key, params.get(key));
        }
        client.post(url, requestParams, new TextHttpResponseHandler() {
            @Override
            public void onStart() {
                httpListener.onStart();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                httpListener.onFailure(responseString, throwable);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                httpListener.handleResponse(responseString);
            }

            @Override
            public void onFinish() {
                httpListener.onFinish();
            }
        });
    }

    /**
     * POST 网络请求
     *
     * @param url
     * @param jsonObject
     * @param httpListener
     */
    @Override
    public void doPost(String url, JSONObject jsonObject, HttpListener<?> httpListener) {

    }

    /**
     * 添加请求头信息
     *
     * @param key
     * @param value
     */
    @Override
    public void addHeader(String key, String value) {
        super.addHeader(key, value);
        client.addHeader(key, value);
    }

    /**
     * GET String　请求
     *
     * @param urlWithParams
     */
    public void doStringGet(final String urlWithParams, final int cacheTime) {
        client.get(urlWithParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                // notify & add cache
                sendSuccessResponse(urlWithParams, new String(responseBody), cacheTime);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                // notify
                sendFailureResponse(urlWithParams, error.getMessage(), error);
            }
        });
    }
}
