package wiki.tony.andboot.di.module;


import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import wiki.tony.andboot.data.repository.UserRepository;
import wiki.tony.andboot.data.repository.impl.UserRepositoryImpl;

/**
 * Created by Tony on 3/18/16.
 */
@Module
public class RepositoryModule {

    @Provides
    @Singleton
    UserRepository userRepository() {
        return new UserRepositoryImpl();
    }

}
