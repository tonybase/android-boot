package io.ganguo.app.basic.exception;

/**
 * Created by zhihui_chen on 14-8-14.
 */
public class HttpRequestException extends AppException {
    public HttpRequestException() {
        super("网络请求异常");
    }

    public HttpRequestException(String detailMessage) {
        super(detailMessage);
    }
}
