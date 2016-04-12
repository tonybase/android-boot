package wiki.tony.andboot;

import android.content.SharedPreferences;

/**
 * 设置选项
 * <p/>
 * Created by Tony on 10/24/15.
 */
public class Settings {
    private static final String SETTING_FIRST_OPEN = "setting_first_open";
    private static final String SETTING_BEFORE_VERSION = "setting_before_version";

    private SharedPreferences mPrefs;

    public Settings(SharedPreferences prefs) {
        mPrefs = prefs;
    }

    /**
     * 是否首次打开应用
     *
     * @return
     */
    public boolean isFirstOpen() {
        return mPrefs.getBoolean(SETTING_FIRST_OPEN, true);
    }

    /**
     * 设置是否首次打开
     *
     * @param isFirstOpen
     */
    public void setFirstOpen(boolean isFirstOpen) {
        mPrefs.edit()
                .putBoolean(SETTING_FIRST_OPEN, isFirstOpen)
                .commit();
    }

    /**
     * 获取上一次版本号
     *
     * @return
     */
    public int getBeforeVersion() {
        return mPrefs.getInt(SETTING_BEFORE_VERSION, AppEnv.VERSION_CODE);
    }

    /**
     * 设置上一次版本号
     */
    public void setBeforeVersion(int version) {
        int before = mPrefs.getInt(SETTING_BEFORE_VERSION, 0);
        if (before != version) {
            mPrefs.edit()
                    .putInt(SETTING_BEFORE_VERSION, version)
                    .commit();
        }
    }

}
