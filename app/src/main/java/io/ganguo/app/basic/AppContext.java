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

    public void onExit() {

    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "程序包名无法找到", e);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 获取当前程序版本名称
     */
    public String getAppVersionName() {
        String versionName = "";
        try {
            // Get the package info
            PackageManager pm = this.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            versionName = pi.versionName;
            if (TextUtils.isEmpty(versionName)) {
                return "";
            }
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "获取当前程序版本名称", e);
        }
        return versionName;
    }

    /**
     * 获取当前代码版本号
     */
    public int getAppVersionCode() {
        int localVersion = 0;
        try {
            PackageManager pm = getPackageManager();
            PackageInfo pi = pm.getPackageInfo(getPackageName(), 0);
            localVersion = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "获取当前代码版本号", e);
        }
        return localVersion;
    }

}
