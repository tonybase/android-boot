package io.ganguo.app.basic.core.http.impl;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.apache.http.Header;

import java.util.Map;

import io.ganguo.app.basic.exception.HttpRequestException;
import io.ganguo.app.basic.core.http.api.AbstractHttpService;
import io.ganguo.app.basic.core.http.api.HttpListener;
import io.ganguo.app.basic.core.http.api.JsonObjectHttpListener;
import io.ganguo.app.basic.core.http.api.StringHttpListener;

/**
 * Created by zhihui_chen on 14-8-14.
 */
public class AsyncHttpImpl extends AbstractHttpService {
    private static final String TAG = VolleyImpl.class.getName();
    private AsyncHttpClient client = new AsyncHttpClient();

    @Override
    public void doGet(String url, Map<String, String> parms, HttpListener<?> httpListener, int cacheTime) {
        String urlWithParams = buildUrlParams(url, parms);
        if (httpListener instanceof StringHttpListener) {
            doStringGet(urlWithParams, (HttpListener<String>) httpListener, cacheTime);
        } else if (httpListener instanceof JsonObjectHttpListener) {
            // do json request
        } else {
            try {
                throw new HttpRequestException("未知的HttpListener类型:" + httpListener.getClass().getName());
            } catch (HttpRequestException e) {
                Log.w(TAG, "未知的HttpListener类型", e);
            }
        }
    }

    /**
     * POST 网络请求
     *
     * @param url
     * @param parms
     * @param httpListener
     * @param cacheTime
     */
    @Override
    public void doPost(String url, Map<String, String> parms, HttpListener<?> httpListener, int cacheTime) {

    }

    /**
     * GET String　请求
     *
     * @param urlWithParams
     * @param httpListener
     */
    public void doStringGet(String urlWithParams, final HttpListener<String> httpListener, int cacheTime) {
        client.get(urlWithParams, new AsyncHttpResponseHandler() {

            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                httpListener.handleResponse(new String(responseBody));
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                httpListener.onFailure(new String(responseBody), error);
            }
        });
    }
}
