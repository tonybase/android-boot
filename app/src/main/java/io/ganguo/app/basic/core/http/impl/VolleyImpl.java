package io.ganguo.app.basic.core.http.impl;

import android.content.Context;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Map;

import io.ganguo.app.basic.core.cache.CacheTime;
import io.ganguo.app.basic.core.http.HttpConstants;
import io.ganguo.app.basic.core.http.api.AbstractHttpService;
import io.ganguo.app.basic.core.http.api.HttpListener;
import io.ganguo.app.basic.exception.NotWebDataException;


/**
 * Volley　实现类
 * <p/>
 * 只对GET请求进行缓存
 * <p/>
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
     * GET 网络请求 默认无缓存
     *
     * @param url
     * @param params
     * @param httpListener
     */
    @Override
    public void doGet(String url, Map<String, String> params, HttpListener<?> httpListener) {
        doGet(url, params, httpListener, CacheTime.NONE);
    }

    /**
     * GET 网络请求
     *
     * @param url
     * @param params
     * @param httpListener
     * @param cacheTime
     */
    @Override
    public void doGet(String url, Map<String, String> params, HttpListener<?> httpListener, int cacheTime) {
        httpListener.onStart();

        String urlWithParams = buildUrlParams(url, params);
        Log.d(TAG, "doGet: " + urlWithParams);
        // 是否已经有缓存了，存在自动返回缓存数据
        if (cacheTime != 0 && fireCache(url, httpListener)) return;

        // 已经有同一URL的请求了，等待自动响应
        if (addQequest(urlWithParams, httpListener)) return;

        doStringGet(urlWithParams, httpListener, cacheTime);
    }

    /**
     * POST 网络请求
     *
     * @param url
     * @param params
     * @param httpListener
     */
    @Override
    public void doPost(String url, Map<String, String> params, HttpListener<?> httpListener) {
        Log.d(TAG, "doPost: " + url + " params:" + params);
        httpListener.onStart();
        doStringPost(url, params, httpListener);
    }

    /**
     * POST 网络请求
     *
     * @param url
     * @param jsonObject
     * @param httpListener
     */
    @Override
    public void doPost(String url, JSONObject jsonObject, final HttpListener<?> httpListener) {
        Response.Listener<JSONObject> responseListener = new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                if(response != null) {
                    httpListener.handleResponse(response.toString());
                } else {
                    httpListener.onFailure("获取请求数据失败", new NotWebDataException("请求数据为空"));
                    httpListener.onFinish();
                }
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpListener.onFailure(error.getMessage(), error);
                httpListener.onFinish();
            }

        };
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, jsonObject, responseListener, errorListener){
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeaderMap();
            }
        };
        request.setRetryPolicy(mRetryPolicy);
        request.setTag(TAG);
        mRequestQueue.add(request);
    }

    /**
     * POST String 网络请求
     *
     * @param url
     * @param params
     * @param httpListener
     */
    private void doStringPost(final String url, final Map<String, String> params, final HttpListener<?> httpListener) {
        Response.Listener<String> responseListener = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                httpListener.handleResponse(response);
            }
        };

        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                httpListener.onFailure(error.getMessage(), error);
                httpListener.onFinish();
            }

        };

        StringRequest request = new StringRequest(Request.Method.POST, url, responseListener, errorListener) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeaderMap();
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
    private void doStringGet(final String urlWithParams, final HttpListener<?> httpListener, final int cacheTime) {
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
                sendFailureResponse(urlWithParams, error.getMessage(), error);
            }

        };
        StringRequest request = new StringRequest(Request.Method.GET, urlWithParams, responseListener, errorListener) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getHeaderMap();
            }
        };
        request.setRetryPolicy(mRetryPolicy);
        request.setTag(TAG);
        mRequestQueue.add(request);
    }

}
