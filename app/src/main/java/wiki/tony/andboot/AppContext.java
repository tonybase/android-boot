package wiki.tony.andboot;

import android.content.Context;

import javax.inject.Inject;

import wiki.tony.andboot.base.BaseApp;
import wiki.tony.andboot.di.component.AppComponent;
import wiki.tony.andboot.di.component.DaggerAppComponent;
import wiki.tony.andboot.di.module.ApiModule;
import wiki.tony.andboot.di.module.AppModule;
import wiki.tony.andboot.di.module.PresenterModule;
import wiki.tony.andboot.di.module.RepositoryModule;
import wiki.tony.andboot.di.module.RetrofitModule;
import wiki.tony.andlog.Logger;
import wiki.tony.andlog.LoggerFactory;

/**
 * App 上下文环境
 * <p/>
 * Created by Tony on 9/30/15.
 */
public class AppContext extends BaseApp {

    private final Logger mLogger = LoggerFactory.getLogger(AppContext.class);

    @Inject
    protected Settings mSettings;

    protected AppComponent mComponent;

    public AppComponent getComponent() {
        if (mComponent == null) {
            mComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(this))
                    .apiModule(new ApiModule())
                    .retrofitModule(new RetrofitModule())
                    .presenterModule(new PresenterModule())
                    .repositoryModule(new RepositoryModule())
                    .build();
        }
        return mComponent;
    }

    /**
     * app context
     *
     * @param context
     * @return
     */
    public static AppContext from(Context context) {
        return (AppContext) context.getApplicationContext();
    }

    /**
     * App 启动
     */
    @Override
    public void onCreate() {
        super.onCreate();

        AppEnv.init(this);

        getComponent().inject(this);

        mLogger.i(mSettings);
    }

}
