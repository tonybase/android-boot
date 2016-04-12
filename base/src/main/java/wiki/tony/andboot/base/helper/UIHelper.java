package wiki.tony.andboot.base.helper;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.view.View;

/**
 * UI 辅助工具
 * <p/>
 * Created by Tony on 9/30/15.
 */
public class UIHelper {

    /**
     * 中止activity运行
     *
     * @param activity
     * @param o
     */
    public static boolean finalActivity(Activity activity, Object o) {
        if (activity != null && o == null) {
            activity.finish();
            return true;
        }
        return false;
    }

    /**
     * 重新启动当前Activity
     *
     * @param activity
     */
    public static void restartActivity(Activity activity) {
        Intent intent = activity.getIntent();
        activity.finish();
        activity.startActivity(intent);
    }

    /**
     * 绑定返回 自动finish
     *
     * @param activity
     * @param actionBack
     */
    public static void bindActionBack(final Activity activity, View actionBack) {
        if (activity == null || actionBack == null) {
            return;
        }
        actionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                activity.finish();
            }
        });
    }

    /**
     * 绑定返回 自动finish
     *
     * @param dialog
     * @param actionBack
     */
    public static void bindActionBack(final Dialog dialog, View actionBack) {
        if (dialog == null || actionBack == null) {
            return;
        }
        actionBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
    }

}
