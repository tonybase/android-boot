package io.ganguo.app.basic.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Handler;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;

import java.io.File;
import java.util.List;

import io.ganguo.app.basic.Config;
import io.ganguo.app.basic.bean.Constants;
import io.ganguo.app.basic.common.UIHelper;

/**
 * Android 工具类
 * <p/>
 * Created by zhihui_chen on 14-8-4.
 */
public class AndroidUtils {
    private static final String TAG = AndroidUtils.class.getName();

    /**
     * 获取屏幕高度
     *
     * @param activity
     * @return
     */
    public static int getScreenHeight(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.heightPixels;
    }

    /**
     * 获取屏幕宽度
     *
     * @param activity
     * @return
     */
    public static int getScreenWidth(Activity activity) {
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        return displayMetrics.widthPixels;
    }

    /**
     * 关闭软键盘
     *
     * @param window
     */
    public static void hideSoftKeyBoard(final Window window) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (window.getCurrentFocus() != null) {
                    InputMethodManager inputManager = (InputMethodManager) window.getContext().getSystemService(Activity.INPUT_METHOD_SERVICE);
                    inputManager.hideSoftInputFromWindow(window.getCurrentFocus().getWindowToken(), 0);
                }
            }
        }, 200);
    }

    /**
     * dp to px
     *
     * @param dp
     * @param context
     * @return
     */
    public static int dpToPx(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

    /**
     * 安装apk
     *
     * @param apkFilePath
     */
    public static void installApk(Context ctx, String apkFilePath) {
        File apkfile = new File(apkFilePath);
        if (!apkfile.exists()) {
            UIHelper.toastMessage(ctx, "安装主程序失败，找不到主程序文件，请尝试重新更新。");
            return;
        }
        Intent i = new Intent(Intent.ACTION_VIEW);
        i.setDataAndType(Uri.parse("file://" + apkfile.toString()),
                "application/vnd.android.package-archive");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        ctx.startActivity(i);
    }

    /**
     * 获取wifi mac地址, 若wifi未开启,将自动开启wifi
     *
     * @param context
     * @return
     */
    public static String getMacAddress(Context context) {
        // 开发环境下，使用固定mac
        if (Constants.DEV_MODE) {
            return Constants.DEV_WIFI_MAC;
        }

        // 先从配置中拿到mac
        String macAddress = Config.getString(Constants.DEV_WIFI_MAC);
        if (!StringUtils.isEmpty(macAddress)) return macAddress;

        WifiManager wifi = (WifiManager) context.getSystemService(Context.WIFI_SERVICE);
        WifiInfo info = wifi.getConnectionInfo();
        macAddress = info.getMacAddress();
        if (StringUtils.isEmpty(macAddress) || !wifi.isWifiEnabled()) {
            wifi.setWifiEnabled(true);
            // 延迟一下
            SystemClock.sleep(3500);
            info = wifi.getConnectionInfo();
            macAddress = info.getMacAddress();
        }

        // 写入到配置信息中
        if (!StringUtils.isEmpty(macAddress))
            Config.putString(Constants.DEV_WIFI_MAC, macAddress);

        return macAddress;
    }

    /**
     * 获取App安装包信息
     *
     * @return
     */
    public PackageInfo getPackageInfo(Context context) {
        PackageInfo info = null;
        try {
            info = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
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
    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // Get the package info
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
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
     * 获取程序版本名称
     */
    public static String getAppVersionName(Context context, String packageName) {
        String versionName = "";
        try {
            // Get the package info
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(packageName, 0);
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
    public int getAppVersionCode(Context context) {
        int localVersion = 0;
        try {
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            localVersion = pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            Log.e(TAG, "获取当前代码版本号", e);
        }
        return localVersion;
    }

    /**
     * 比较两个app版本号，看是否需要更新
     *
     * @param targetVersion
     * @param baseVersion
     * @return true if targetVersion > baseVersion,return true if haven't
     * install
     */
    public static boolean needToUpdate(String targetVersion, String baseVersion) {
        if (StringUtils.isEmpty(targetVersion)) {
            return false;// empty target, return false
        }
        if (StringUtils.isEmpty(baseVersion)) {
            return true;// not install, return true
        }
        List<String> targetItems = StringUtils.stringToList(targetVersion, StringUtils.VERSION_SEPERATOR);
        List<String> baseItems = StringUtils.stringToList(baseVersion, StringUtils.VERSION_SEPERATOR);
        Log.e("targetItems", targetItems.toString());
        Log.e("baseItems", baseItems.toString());

        if (CollectionUtils.isEmpty(targetItems) || CollectionUtils.isEmpty(baseItems)) {
            return false;
        }

        final int targetSize = targetItems.size();
        final int baseSize = baseItems.size();
        final int total = targetSize > baseSize ? targetSize : baseSize;

        for (int i = 0; i < total; i++) {
            int targetV = (i >= targetSize) ? 0 : Integer.parseInt(targetItems.get(i));
            int baseV = (i >= baseSize) ? 0 : Integer.parseInt(baseItems.get(i));
            if (targetV > baseV) {
                return true;
            }
            if (targetV < baseV) {
                return false;
            }

        }
        return false;
    }

}
