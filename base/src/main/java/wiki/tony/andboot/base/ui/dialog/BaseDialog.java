package wiki.tony.andboot.base.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import wiki.tony.andboot.base.R;
import wiki.tony.andboot.base.helper.UIHelper;


/**
 * 对话框 - 基类
 * <p/>
 * Created by zhihui_chen on 14-9-9.
 */
public abstract class BaseDialog extends Dialog implements InitResources {

    public BaseDialog(Context context) {
        this(context, R.style.customDialog);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // init resources
        beforeInitView();
        initView();
        initListener();
        initData();
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 绑定返回Action
        UIHelper.bindActionBack(this, findViewById(R.id.action_back));
    }

    /**
     * 对话框大小
     *
     * @param width
     * @param height
     */
    public void setSize(int width, int height) {
        getWindow().setLayout(width, height);
    }

}
