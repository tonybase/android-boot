package io.ganguo.app.basic.core.http.api;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import io.ganguo.app.basic.exception.NotWebDataException;
import io.ganguo.app.basic.util.GsonUtils;


/**
 * RawData请求返回监听器
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class EntityHttpListener<T> implements HttpListener {
    private static final String TAG = EntityHttpListener.class.getName();

    @Override
    public void handleResponse(String response) {
        try {
            if (TextUtils.isEmpty(response)) {
                onFailure("请求数据为空", new NotWebDataException());
                return;
            }
            T t = GsonUtils.fromJson(response, new TypeToken<T>() {
            }.getType());
            onSuccess(t);
        } catch (JsonSyntaxException e) {
            Log.e(TAG, "转换RawData错误 " + response, e);
            onFailure("转换RawData错误", e);
        } finally {
            onFinish();
        }
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

}
