package io.ganguo.app.basic.core.http;

import android.util.Log;

import io.ganguo.app.basic.AppContext;
import io.ganguo.app.basic.exception.NotRegistionContextException;
import io.ganguo.app.basic.core.http.api.HttpService;
import io.ganguo.app.basic.core.http.impl.VolleyImpl;

/**
 * 网络支持类
 *
 * Created by zhihui_chen on 14-8-4.
 */
public class HttpFactory {
    private static final String TAG = HttpFactory.class.getName();

    private static AppContext context = null;
    private static HttpService httpService = null;

    public static void register(AppContext context) {
        HttpFactory.context = context;
    }

    public static void checkContext() {
        if (context == null) {
            try {
                throw new NotRegistionContextException("Config error!");
            } catch (NotRegistionContextException e) {
                Log.e(TAG, "配置类没有注册上AppContext(下文环境)", e);
            }
        }
    }

    public static HttpService getHttpService() {
        checkContext();
        if (httpService == null) {
            // 通过Volley进行实现
            httpService = new VolleyImpl(context);
        }
        return httpService;
    }

    public static HttpService newHttpService() {
        checkContext();
        return new VolleyImpl(context);
    }

}
