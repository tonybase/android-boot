package wiki.tony.andboot.ui.activity;

import android.content.Intent;
import android.os.Handler;

import javax.inject.Inject;

import wiki.tony.andboot.AppContext;
import wiki.tony.andboot.AppEnv;
import wiki.tony.andboot.R;
import wiki.tony.andboot.Settings;
import wiki.tony.andboot.base.ui.activity.BaseActivity;
import wiki.tony.andlog.Logger;
import wiki.tony.andlog.LoggerFactory;

/**
 * App 启动页面
 * <p/>
 * Created by tony on 3/10/16.
 */
public class StartActivity extends BaseActivity {

    private Logger mLogger = LoggerFactory.getLogger(StartActivity.class);

    @Inject
    protected Settings mSettings;

    @Override
    public void beforeInitView() {
        setContentView(R.layout.activity_start);

        AppContext.from(this).getComponent().inject(this);
    }

    @Override
    public void initView() {

    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mLogger.d(mSettings.isFirstOpen());

        // 是否首次打开App
        if (mSettings.isFirstOpen()) {
            redirectGuidePage();
            // set opened
            mSettings.setFirstOpen(false);
        } else {
            initSplashPage();
        }
        // 设置当前版本号
        mSettings.setBeforeVersion(AppEnv.VERSION_CODE);
    }

    /**
     * 初始化闪屏图片
     */
    private void initSplashPage() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        }, 2000);
    }

    /**
     * 跳转到 引导页面
     */
    private void redirectGuidePage() {
        initSplashPage();
    }

}
