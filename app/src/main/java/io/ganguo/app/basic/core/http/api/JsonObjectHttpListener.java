package io.ganguo.app.basic.core.http.api;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import io.ganguo.app.basic.exception.NotWebDataException;

/**
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class JsonObjectHttpListener implements HttpListener<JSONObject> {
    private static final String TAG = JsonObjectHttpListener.class.getName();

    @Override
    public void handleResponse(String response) {
        if (TextUtils.isEmpty(response)) {
            onFailure("请求数据为空", new NotWebDataException());
            return;
        }
        try {
            onSuccess(new JSONObject(response));
        } catch (JSONException e) {
            Log.d(TAG, "转换JSON错误", e);
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
