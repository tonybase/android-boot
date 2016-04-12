package wiki.tony.andboot.ui.presenter.impl;

import wiki.tony.andboot.api.UserApi;
import wiki.tony.andboot.data.repository.UserRepository;
import wiki.tony.andboot.ui.presenter.LoginPresenter;

/**
 * Created by Tony on 3/17/16.
 */
public class LoginPresenterImpl implements LoginPresenter {

    private UserApi mUserApi;
    private UserRepository mUserRepository;

    public LoginPresenterImpl(UserApi userApi, UserRepository userRepository) {
        mUserApi = userApi;
        mUserRepository = userRepository;
    }
}
