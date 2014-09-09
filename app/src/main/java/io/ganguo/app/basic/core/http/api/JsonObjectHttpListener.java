package io.ganguo.app.basic.core.http.api;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import io.ganguo.app.basic.exception.NotWebDataException;
import io.ganguo.app.basic.util.StringUtils;

/**
 * JsonObject请求返回监听器
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class JsonObjectHttpListener implements HttpListener<JSONObject> {
    private static final String TAG = JsonObjectHttpListener.class.getName();

    @Override
    public void handleResponse(String response) {
        try {
            if (StringUtils.isEmpty(response)) {
                onFailure("请求数据为空", new NotWebDataException());
                return;
            }
            onSuccess(new JSONObject(response));
        } catch (JSONException e) {
            Log.e(TAG, "转换JSON错误", e);
            onFailure("转换JSON错误", e);
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
