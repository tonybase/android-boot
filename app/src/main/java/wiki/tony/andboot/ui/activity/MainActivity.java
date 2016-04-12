package wiki.tony.andboot.ui.activity;

import android.view.View;

import java.util.List;

import javax.inject.Inject;

import wiki.tony.andboot.AppContext;
import wiki.tony.andboot.R;
import wiki.tony.andboot.base.helper.ToastHelper;
import wiki.tony.andboot.base.ui.activity.BaseActivity;
import wiki.tony.andboot.data.entity.User;
import wiki.tony.andboot.ui.presenter.MainPresenter;
import wiki.tony.andlog.Logger;
import wiki.tony.andlog.LoggerFactory;

/**
 * App 主界面
 * <p/>
 * Created by tony on 3/10/16.
 */
public class MainActivity extends BaseActivity implements MainPresenter.View, View.OnClickListener {

    private Logger mLogger = LoggerFactory.getLogger(MainActivity.class);

    @Inject
    protected MainPresenter mPresenter;

    @Override
    public void beforeInitView() {
        setContentView(R.layout.activity_main);

        AppContext.from(this).getComponent().inject(this);
    }

    @Override
    public void initView() {
        mPresenter.setView(this);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initData() {
        mPresenter.loadUsers();
        mLogger.i(mPresenter);
    }

    @Override
    public void onClick(View v) {
//        presenter.loadUsers();
    }

    @Override
    public void onUserLoaded(List<User> users) {
        mLogger.d(users);
    }

    @Override
    public void onUserLoadedError(Throwable e) {

        ToastHelper.showMessage(this, e.getMessage());
    }

}
