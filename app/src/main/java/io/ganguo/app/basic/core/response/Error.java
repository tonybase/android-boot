package io.ganguo.app.basic.core.response;

import java.util.Map;

/**
 * Created by zhihui_chen on 14-9-9.
 */
public class Error {
    private int code;
    private String message;
    private Map<String, String> errors;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, String> getErrors() {
        return errors;
    }

    public void setErrors(Map<String, String> errors) {
        this.errors = errors;
    }

    @Override
    public String toString() {
        return "Error{" +
                "code=" + code +
                ", message='" + message + '\'' +
                ", errors=" + errors +
                '}';
    }
}
