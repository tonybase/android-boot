package wiki.tony.andboot.di.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import wiki.tony.andboot.AppContext;

/**
 * Created by Tony on 3/18/16.
 */
@Module
public class AppModule {

    private AppContext mContext;

    public AppModule(AppContext context) {
        mContext = context;
    }

    @Provides
    Context context() {
        return mContext.getApplicationContext();
    }

    @Provides
    AppContext appContext() {
        return mContext;
    }

}