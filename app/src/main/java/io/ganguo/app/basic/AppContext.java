package io.ganguo.app.basic;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.TextUtils;
import android.util.Log;

import io.ganguo.app.basic.core.cache.CacheManager;
import io.ganguo.app.basic.core.http.HttpFactory;

/**
 * App上下文环境
 * <p/>
 * Created by zhihui_chen on 14-8-4.
 */
public class AppContext extends Application {
    private static final String TAG = AppContext.class.getName();

    /**
     * 应用启动
     */
    @Override
    public void onCreate() {
        super.onCreate();

        registerModule();
    }

    private void registerModule() {
        Config.register(this);
        HttpFactory.register(this);
        CacheManager.register(this);
    }

    /**
     * 应用退出
     */
    @Override
    public void onTerminate() {
        super.onTerminate();
        onExit();
    }

    /**
     * 应用内存不足
     */
    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    /**
     * 应用退出，由AppManager回调
     */
    public void onExit() {

    }

}
