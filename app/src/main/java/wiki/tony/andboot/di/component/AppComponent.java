package wiki.tony.andboot.di.component;


import javax.inject.Singleton;

import dagger.Component;
import wiki.tony.andboot.AppContext;
import wiki.tony.andboot.di.module.ApiModule;
import wiki.tony.andboot.di.module.AppModule;
import wiki.tony.andboot.di.module.PreferenceModule;
import wiki.tony.andboot.di.module.PresenterModule;
import wiki.tony.andboot.di.module.RepositoryModule;
import wiki.tony.andboot.di.module.RetrofitModule;
import wiki.tony.andboot.ui.activity.MainActivity;
import wiki.tony.andboot.ui.activity.StartActivity;

/**
 * Created by Tony on 3/18/16.
 */
@Singleton
@Component(
        modules = {
                AppModule.class,
                ApiModule.class,
                RetrofitModule.class,
                PreferenceModule.class,
                RepositoryModule.class,
                PresenterModule.class
        })
public interface AppComponent {

    void inject(AppContext context);

    void inject(StartActivity activity);

    void inject(MainActivity activity);

}
