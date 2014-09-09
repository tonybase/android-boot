package io.ganguo.app.basic.core.http.api;

import io.ganguo.app.basic.exception.NotWebDataException;
import io.ganguo.app.basic.util.StringUtils;


/**
 * String请求返回监听器
 * <p/>
 * Created by zhihui_chen on 14-8-14.
 */
public abstract class StringHttpListener implements HttpListener<String> {
    private static final String TAG = StringHttpListener.class.getName();

    @Override
    public void handleResponse(String response) {
        try {
            if (StringUtils.isEmpty(response)) {
                onFailure("请求数据为空", new NotWebDataException());
                return;
            }
            onSuccess(response);
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
