package io.ganguo.app.basic.ui.extend;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;

import io.ganguo.app.basic.R;

/**
 * Created by zhihui_chen on 14-9-9.
 */
public class BaseDialog extends Dialog {

    public BaseDialog(Context context) {
        super(context);
    }

    public BaseDialog(Context context, int theme) {
        super(context, theme);
    }

    public BaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    @Override
    protected void onStart() {
        super.onStart();

        // 返回通过Action
        View actionBack = findViewById(R.id.action_back);
        if (actionBack != null) {
            actionBack.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    dismiss();
                }
            });
        }
    }
}
