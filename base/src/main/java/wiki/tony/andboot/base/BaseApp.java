package wiki.tony.andboot.base;

import android.app.Application;

/**
 * Application - 基类
 * <p/>
 * Created by Tony on 9/30/15.
 */
public class BaseApp extends Application {

    /**
     * 应用启动
     */
    @Override
    public void onCreate() {
        super.onCreate();

    }

    /**
     * 应用销毁
     */
    @Override
    public void onTerminate() {
        super.onTerminate();

        // exit
        AppManager.exitApp();
    }

}
