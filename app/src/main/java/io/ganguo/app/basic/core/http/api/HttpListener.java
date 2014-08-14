package io.ganguo.app.basic.core.http.api;

/**
 * Created by zhihui_chen on 14-8-13.
 */
public interface HttpListener<T> {
    public void handleResponse(String response);

    public void onStart();

    public void onSuccess(T content);

    public void onFailure(String error, Throwable e);

    public void onFinish();
}
