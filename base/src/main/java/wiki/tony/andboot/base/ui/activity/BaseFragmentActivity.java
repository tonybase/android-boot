package wiki.tony.andboot.base.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

import wiki.tony.andboot.base.AppManager;
import wiki.tony.andboot.base.R;
import wiki.tony.andboot.base.helper.UIHelper;
import wiki.tony.andlog.Logger;
import wiki.tony.andlog.LoggerFactory;


/**
 * FragmentActivity - 基类
 * <p/>
 * Created by hulk on 10/20/14.
 */
public abstract class BaseFragmentActivity extends FragmentActivity implements InitResources {
    protected Logger mLogger = LoggerFactory.getLogger(getClass().getSimpleName());

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // register
        AppManager.addActivity(this);

        // init resources
        beforeInitView();
        initView();
        initListener();
        initData();
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // bind back action
        UIHelper.bindActionBack(this, findViewById(R.id.action_back));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        // unregister event
        AppManager.removeActivity(this);
    }

    /**
     * fragment 显示处理
     */
    private Map<Class<? extends Fragment>, Fragment> fragmentMap = new HashMap<>();
    private Fragment currentFragment;

    @Override
    public void onSaveInstanceState(Bundle outState) {
//        super.onSaveInstanceState(outState);

        mLogger.i("onSaveInstanceState " + outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        super.onRestoreInstanceState(savedInstanceState);

        mLogger.i("onRestoreInstanceState " + savedInstanceState);
    }

    /**
     * 显示Fragment到容器中
     *
     * @param res
     * @param fragment
     */
    protected void showFragment(int res, Fragment fragment) {
        showFragment(res, fragment, null);
    }

    /**
     * 显示Fragment到容器中
     *
     * @param res
     * @param fragment
     * @param tag
     */
    protected void showFragment(int res, Fragment fragment, String tag) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(res, fragment, tag);
        transaction.commit();
        getSupportFragmentManager().executePendingTransactions();
        currentFragment = fragment;
    }

    /**
     * 显示Fragment到容器中
     *
     * @param clazz
     */
    protected void showFragment(int res, final Class<? extends Fragment> clazz) {
        if (!fragmentMap.containsKey(clazz)) {
            try {
                fragmentMap.put(clazz, clazz.newInstance());
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
        Fragment target = fragmentMap.get(clazz);
        if (currentFragment != null && target != null && currentFragment.getClass().equals(target.getClass())) {
            return;
        }
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        if (currentFragment != null) {
            transaction.hide(currentFragment);
            mLogger.i("hide " + currentFragment);
        }
        if (target.isAdded()) {
            if (target.isDetached()) {
                transaction.attach(currentFragment);
            } else {
                transaction.show(target);
            }
        } else {
            transaction.add(res, target);
        }
        transaction.commitAllowingStateLoss();
        currentFragment = target;
    }

    /**
     * 让fragment重新走生命周期
     */
    protected void updateFragment() {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        for (Class<? extends Fragment> clazz : fragmentMap.keySet()) {
            Fragment fragment = fragmentMap.get(clazz);
            if (fragment != currentFragment) {
                transaction.remove(fragment);
            }
        }
        transaction.detach(currentFragment);
        transaction.attach(currentFragment);
        transaction.commitAllowingStateLoss();
    }
}
