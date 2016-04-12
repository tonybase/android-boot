package wiki.tony.andboot.ui.presenter.impl;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import wiki.tony.andboot.api.UserApi;
import wiki.tony.andboot.data.repository.UserRepository;
import wiki.tony.andboot.data.entity.User;
import wiki.tony.andboot.ui.presenter.MainPresenter;
import wiki.tony.andlog.Logger;
import wiki.tony.andlog.LoggerFactory;

/**
 * Created by Tony on 3/17/16.
 */
public class MainPresenterImpl implements MainPresenter {

    private Logger logger = LoggerFactory.getLogger(MainPresenterImpl.class);

    private UserApi mUserApi;
    private UserRepository mUserRepository;

    private View mView;

    public MainPresenterImpl(UserApi userApi, UserRepository userRepository) {
        mUserApi = userApi;
        mUserRepository = userRepository;
    }

    @Override
    public void setView(View view) {
        mView = view;

        logger.d(mUserApi);
        logger.d(mUserRepository);
    }

    @Override
    public void loadUsers() {
        mUserApi.loadUsers("square", "retrofit")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<User>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.onUserLoadedError(e);
                    }

                    @Override
                    public void onNext(List<User> users) {
                        mView.onUserLoaded(users);
                    }
                });
    }

}
