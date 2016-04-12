package wiki.tony.andboot;

import android.content.Context;
import android.util.Log;

import wiki.tony.andlog.FileLogger;
import wiki.tony.andlog.LogConfig;
import wiki.tony.andlog.Logger;
import wiki.tony.andlog.LoggerFactory;

/**
 * App 环境配置
 * <p/>
 * Created by Tony on 3/4/15.
 */
public class AppEnv {

    private static Logger LOG = LoggerFactory.getLogger(AppEnv.class);

    /**
     * 开发环境配置信息
     * 根据app/build.gradle生成
     */
    public final static boolean DEBUG = BuildConfig.DEBUG;                  // 是否处于测试环境
    public final static boolean ACTIVE_DEV = BuildConfig.ACTIVE_DEV;        // 是否处于测试环境
    public final static String APPLICATION_ID = BuildConfig.APPLICATION_ID; // 市场渠道
    public final static String MARKET_CHANNEL = BuildConfig.FLAVOR;         // 市场渠道
    public final static String VERSION_NAME = BuildConfig.VERSION_NAME;     // 版本名称
    public final static int VERSION_CODE = BuildConfig.VERSION_CODE;        // 版本号

    /**
     * init
     */
    public static void init(Context context) {
        // 日志配置
        LogConfig.LOGGER_CLASS = FileLogger.class;
        LogConfig.FILE_PATH = context.getExternalFilesDir("logs");
        LogConfig.PRIORITY = DEBUG ? Log.VERBOSE : Log.INFO;
        LogConfig.FILE_PRIORITY = Log.ERROR;

        // app info
        LOG.i("****** AppEnv ******");
        LOG.i(" DEBUG: " + DEBUG);
        LOG.i(" APPLICATION_ID: " + APPLICATION_ID);
        LOG.i(" VERSION_CODE: " + VERSION_CODE);
        LOG.i(" VERSION_NAME: " + VERSION_NAME);
        LOG.i(" FLAVOR: " + MARKET_CHANNEL);
        LOG.i(" LOG_CONSOLE: " + LogConfig.PRIORITY);
        LOG.i(" LOG_FILE: " + LogConfig.FILE_PRIORITY);
        LOG.i("********************");
    }

}
