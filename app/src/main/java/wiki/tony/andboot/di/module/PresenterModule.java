package wiki.tony.andboot.di.module;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import wiki.tony.andboot.api.UserApi;
import wiki.tony.andboot.data.repository.UserRepository;
import wiki.tony.andboot.ui.presenter.LoginPresenter;
import wiki.tony.andboot.ui.presenter.MainPresenter;
import wiki.tony.andboot.ui.presenter.impl.LoginPresenterImpl;
import wiki.tony.andboot.ui.presenter.impl.MainPresenterImpl;

/**
 * Created by Tony on 3/18/16.
 */
@Module
public class PresenterModule {

    @Provides
    @Singleton
    MainPresenter mainPresenter(UserApi userApi, UserRepository userRepository) {
        return new MainPresenterImpl(userApi, userRepository);
    }

    @Provides
    @Singleton
    LoginPresenter loginPresenter(UserApi userApi, UserRepository userRepository) {
        return new LoginPresenterImpl(userApi, userRepository);
    }

}
