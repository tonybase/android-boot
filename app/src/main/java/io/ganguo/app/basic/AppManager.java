package io.ganguo.app.basic;

import android.app.Activity;
import android.util.Log;

import java.util.Stack;

/**
 * 应用程序Activity管理类：用于Activity管理和应用程序退出
 *
 * @author ZhiHui_Chen
 * @Email: 6208317#qq.com
 * @Date 2013-5-14
 */
public class AppManager {
    private static final String TAG = AppManager.class.getName();

    /**
     * Activity记录栈
     */
    private static Stack<Activity> activityStack;
    /**
     * AppManager单例
     */
    private static AppManager instance;

    /**
     * 单例
     */
    private AppManager() {
    }

    /**
     * 获取AppManager单一实例
     */
    public static AppManager getInstance() {
        if (instance == null) {
            instance = new AppManager();
        }
        return instance;
    }

    /**
     * 添加Activity到堆栈
     */
    public void addActivity(Activity activity) {
        if (activityStack == null) {
            activityStack = new Stack<Activity>();
        }
        activityStack.add(activity);
    }

    /**
     * 获取当前Activity（堆栈中最后一个压入的）
     */
    public Activity currentActivity() {
        Activity activity = activityStack.lastElement();
        return activity;
    }

    /**
     * 结束当前Activity（堆栈中最后一个压入的）
     */
    public void finishActivity() {
        Activity activity = activityStack.lastElement();
        finishActivity(activity);
    }

    /**
     * 结束指定类名的Activity
     */
    public Activity getFirstActivity() {
        return activityStack.firstElement();
    }

    /**
     * 结束指定类名的Activity
     */
    public Activity getActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                return activity;
            }
        }
        return null;
    }

    /**
     * 结束指定的Activity
     */
    public void finishActivity(Activity activity) {
        if (activity != null) {
            activityStack.remove(activity);
            activity.finish();
            activity = null;
        }
    }

    /**
     * 结束指定类名的Activity
     */
    public void finishActivity(Class<?> cls) {
        for (Activity activity : activityStack) {
            if (activity.getClass().equals(cls)) {
                finishActivity(activity);
            }
        }
    }

    /**
     * 结束所有Activity
     */
    public void finishAllActivity() {
        for (int i = 0, size = activityStack.size(); i < size; i++) {
            if (null != activityStack.get(i)) {
                activityStack.get(i).finish();
            }
        }
        activityStack.clear();
    }

    /**
     * 退出应用程序
     */
    public void AppExit(AppContext context) {
        try {
            finishAllActivity();
            context.onExit();
        } catch (Exception e) {
            Log.e(TAG, "退出应用失败", e);
        } finally {
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(0);
        }
    }
}
