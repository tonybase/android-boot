package io.ganguo.app.basic.core.http.impl;

import android.content.Context;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.Map;

import io.ganguo.app.basic.core.cache.Cache;
import io.ganguo.app.basic.core.cache.CacheManager;
import io.ganguo.app.basic.core.http.HttpConstants;
import io.ganguo.app.basic.core.http.api.AbstractHttpService;
import io.ganguo.app.basic.core.http.api.HttpListener;
import io.ganguo.app.basic.core.http.api.JsonObjectHttpListener;
import io.ganguo.app.basic.core.http.api.StringHttpListener;

/**
 * Volley　实现类
 *
 * 只对GET请求进行缓存
 *
 * Created by zhihui_chen on 14-8-4.
 */
public class VolleyImpl extends AbstractHttpService {
    private static final String TAG = VolleyImpl.class.getName();
    private RequestQueue mRequestQueue;
    private DefaultRetryPolicy mRetryPolicy;

    public VolleyImpl(Context context) {
        mRequestQueue = Volley.newRequestQueue(context);
        mRetryPolicy = new DefaultRetryPolicy(HttpConstants.REQUEST_TIMEOUT_MS
                , HttpConstants.REQUEST_MAX_RETRIES, HttpConstants.REQUEST_BACKOFF_MULT);
    }

    /**
     * GET 网络请求
     *
     * @param url
     * @param parms
     * @param httpListener
     * @param cacheTime
     */
    @Override
    public void doGet(String url, Map<String, String> parms, HttpListener<?> httpListener, int cacheTime) {
        String urlWithParams = buildUrlParams(url, parms);
        // 是否已经有缓存了，存在自动返回缓存数据
        if(fireCache(url, httpListener)) return ;

        // 已经有同一URL的请求了，等待自动响应
        if (addQequest(urlWithParams, httpListener)) return;

        if (httpListener instanceof StringHttpListener) {
            doStringGet(urlWithParams, (HttpListener<String>) httpListener, cacheTime);
        } else if (httpListener instanceof JsonObjectHttpListener) {
            // do json request
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

        if (httpListener instanceof StringHttpListener) {
            doStringPost(url, parms, (HttpListener<String>) httpListener, cacheTime);
        }
    }

    /**
     * POST String 网络请求
     *
     * @param url
     * @param parms
     * @param httpListener
     * @param cacheTime
     */
    public void doStringPost(final String url, final Map<String, String> parms, final HttpListener<String> httpListener, int cacheTime) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // notify & add cache
                httpListener.handleResponse(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpListener.onFailure(error.getMessage(), error);
            }

        };

        StringRequest request = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return parms;
            }
        };
        request.setRetryPolicy(mRetryPolicy);
        request.setTag(TAG);
        mRequestQueue.add(request);
    }

    /**
     * GET String　请求
     *
     * @param urlWithParams
     * @param httpListener
     */
    public void doStringGet(final String urlWithParams, final HttpListener<String> httpListener, final int cacheTime) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // notify & add cache
                sendSuccessResponse(urlWithParams, response, cacheTime);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                // notify
                sendFailureResponse(urlWithParams, error.networkResponse.statusCode + " ", error);
            }

        };
        StringRequest request = new StringRequest(Request.Method.GET, urlWithParams, responseListener, errorListener);
        request.setRetryPolicy(mRetryPolicy);
        request.setTag(TAG);
        mRequestQueue.add(request);
    }
}
