package wiki.tony.andboot.base.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wiki.tony.andboot.base.R;
import wiki.tony.andboot.base.helper.UIHelper;
import wiki.tony.andlog.Logger;
import wiki.tony.andlog.LoggerFactory;

/**
 * Fragment - 基类
 * <p/>
 * Created by zhihui_chen on 14-8-4.
 */
public abstract class BaseFragment extends Fragment implements InitResources {

    private final Logger logger = LoggerFactory.getLogger(BaseFragment.class);

    public String getFragmentTitle() {
        return null;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(getLayoutResourceId(), container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        // bind back action
        UIHelper.bindActionBack(getActivity(), getView().findViewById(R.id.action_back));

        // init resources
        initView();
        initListener();
        initData();
    }

}
