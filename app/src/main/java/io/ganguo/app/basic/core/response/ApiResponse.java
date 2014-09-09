package io.ganguo.app.basic.core.response;

import java.io.Serializable;

/**
 * 接口数据模型
 * <p/>
 * Created by tony on 8/24/14.
 */
public class ApiResponse<T> implements Serializable {

    private String apiVersion;
    private Error error;
    private T data;

    public String getApiVersion() {
        return apiVersion;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        this.error = error;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "ApiResponse{" +
                "apiVersion='" + apiVersion + '\'' +
                ", error=" + error +
                ", data=" + data +
                '}';
    }
}
