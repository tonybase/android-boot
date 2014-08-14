package io.ganguo.app.basic.common;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by zhihui_chen on 14-8-4.
 */
public class UIHelper {
    private static Toast toast;

    /**
     * 弹出Toast消息
     *
     * @param charSequence
     */
    public static void toastMessage(Context context, CharSequence charSequence) {
        if (toast == null) {
            toast = Toast.makeText(context, charSequence, Toast.LENGTH_SHORT);
        } else {
            toast.setText(charSequence);
        }
        toast.show();
    }

    /**
     * 资源ID信息显示
     *
     * @param context
     * @param msg
     */
    public static void toastMessage(Context context, int resId) {
        if (toast == null) {
            toast = Toast.makeText(context, resId, Toast.LENGTH_SHORT);
        } else {
            toast.setText(resId);
        }
        toast.show();
    }

    /**
     * 指定消息显示时间
     *
     * @param context
     * @param msg
     * @param time
     */
    public static void toastMessage(Context context, CharSequence charSequence,
                                    int time) {
        if (toast == null) {
            toast = Toast.makeText(context, charSequence, time);
        } else {
            toast.setText(charSequence);
        }
        toast.show();
    }
}
