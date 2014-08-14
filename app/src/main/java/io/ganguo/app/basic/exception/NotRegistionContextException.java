package io.ganguo.app.basic.exception;

/**
 * Created by zhihui_chen on 14-8-5.
 */
public class NotRegistionContextException extends AppException {
    public NotRegistionContextException() {
        super("没有注册上下文环境(Application)");
    }

    public NotRegistionContextException(String detailMessage) {
        super(detailMessage);
    }

}
