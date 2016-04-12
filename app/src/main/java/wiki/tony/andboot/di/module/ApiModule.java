package wiki.tony.andboot.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import wiki.tony.andboot.api.UserApi;

/**
 * Created by Tony on 3/18/16.
 */
@Module
public class ApiModule {

    @Provides
    @Singleton
    UserApi userApi(Retrofit retrofit) {
        return retrofit.create(UserApi.class);
    }

}
