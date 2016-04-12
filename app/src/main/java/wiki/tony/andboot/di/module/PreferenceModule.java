package wiki.tony.andboot.di.module;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Named;
import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import wiki.tony.andboot.Settings;
import wiki.tony.andboot.bean.Constants;

import static android.content.Context.MODE_PRIVATE;

/**
 * Created by Tony on 3/19/16.
 */
@Module
public class PreferenceModule {

    @Provides
    @Named(Constants.PREFS_DEFAULT)
    SharedPreferences defaultPrefs(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context);
    }

    @Provides
    @Named(Constants.PREFS_USER)
    SharedPreferences userPrefs(Context context) {
        return context.getSharedPreferences("user", MODE_PRIVATE);
    }

    @Provides
    @Singleton
    Settings settings(@Named(Constants.PREFS_DEFAULT) SharedPreferences prefs) {
        return new Settings(prefs);
    }

}
