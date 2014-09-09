package io.ganguo.app.basic.ui.extend;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import io.ganguo.app.basic.R;

/**
 * Created by zhihui_chen on 14-8-4.
 */
public class BaseFragment extends Fragment {

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if(getView() != null) {
            // 返回通过Action
            View actionBack = getView().findViewById(R.id.action_back);
            if (actionBack != null) {
                actionBack.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getActivity().finish();
                    }
                });
            }
        }
    }

}
