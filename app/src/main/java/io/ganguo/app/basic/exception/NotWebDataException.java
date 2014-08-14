package io.ganguo.app.basic.exception;

/**
 * Created by zhihui_chen on 14-8-14.
 */
public class NotWebDataException extends AppException {
    public NotWebDataException() {
        super("没有获得请求数据");
    }

    public NotWebDataException(String detailMessage) {
        super(detailMessage);
    }
}
