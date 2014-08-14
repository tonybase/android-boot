package io.ganguo.app.basic.core.http.api;

import android.text.TextUtils;

import io.ganguo.app.basic.exception.NotWebDataException;

/**
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class StringHttpListener implements HttpListener<String> {

    @Override
    public void handleResponse(String response) {
        if (TextUtils.isEmpty(response)) {
            onFailure("请求数据为空", new NotWebDataException());
            return;
        }
        onSuccess(response);
        onFinish();
    }

    @Override
    public void onStart() {

    }

    @Override
    public void onFinish() {

    }

}
