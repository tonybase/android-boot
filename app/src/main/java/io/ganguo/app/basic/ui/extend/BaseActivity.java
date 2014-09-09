package io.ganguo.app.basic.ui.extend;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import io.ganguo.app.basic.AppManager;
import io.ganguo.app.basic.R;

/**
 * 基本的 Activity
 * 用于继续使用
 * <p/>
 * Created by zhihui_chen on 14-8-4.
 */
public class BaseActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AppManager.getInstance().addActivity(this);
    }

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);

        // 返回通过Action
        View actionBack = findViewById(R.id.action_back);
        if (actionBack != null) {
            actionBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        AppManager.getInstance().finishActivity(this);
    }
}
