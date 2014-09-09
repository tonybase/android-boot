package io.ganguo.app.basic.common;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * 界面辅助工具
 * <p/>
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
     * 弹出Toast消息
     *
     * @param charSequence
     */
    public static void toastMessageMiddle(Context context, CharSequence charSequence) {
        if (toast == null) {
            toast = Toast.makeText(context, charSequence, Toast.LENGTH_SHORT);
        } else {
            toast.setText(charSequence);
        }
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }

    /**
     * 弹出Toast消息
     *
     * @param resId
     */
    public static void toastMessageMiddle(Context context, int resId) {
        toastMessageMiddle(context, context.getResources().getText(resId));
    }

    /**
     * 资源ID信息显示
     *
     * @param context
     * @param resId
     */
    public static void toastMessage(Context context, int resId) {
        toastMessage(context, context.getResources().getText(resId));
    }

    /**
     * 指定消息显示时间
     *
     * @param context
     * @param charSequence
     * @param time
     */
    public static void toastMessage(Context context, CharSequence charSequence, int time) {
        if (toast == null) {
            toast = Toast.makeText(context, charSequence, time);
        } else {
            toast.setText(charSequence);
        }
        toast.show();
    }

    /**
     * 当ScrollView与ListView冲突的时候，用这个方法设置listView的高
     *
     * @param listView
     */
    public static void setListViewHeightBasedOnChildren(ListView listView) {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int totalHeight = 0;
        for (int i = 0; i < listAdapter.getCount(); i++) {
            View listItem = listAdapter.getView(i, null, listView);
            listItem.measure(0, 0);
            totalHeight += listItem.getMeasuredHeight();
        }

        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalHeight
                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));
        listView.setLayoutParams(params);
    }

    /**
     * 选择数字
     *
     * @param context
     * @param text
     * @param min
     * @param max
     */
    public static void numberPicker(Context context, final EditText text, int min, int max) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        int position = 0;
        final String[] items = new String[max - min + 1];
        for (int i = 0; i < items.length; i++) {
            String value = String.valueOf(i + 30);
            items[i] = value;
            if (text.getText().toString().equals(value)) {
                position = i;
            }
        }
        builder.setSingleChoiceItems(items, position, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                text.setText(items[which]);
                dialog.dismiss();
            }
        });
        builder.show();
    }

    /**
     * @param context
     * @param date    文本编辑器，规定日期格式：yyyy-MM-dd
     */
    public static void datePicker(Context context, final EditText date) {
        String[] dateArray = null;
        if (date.getText() != null && !date.getText().toString().equals("")) {
            dateArray = date.getText().toString().split("-");
        } else {
            dateArray = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        DatePicker datePicker = new DatePicker(context);
        datePicker.setCalendarViewShown(true);
        // datePicker.setMaxDate(System.currentTimeMillis());
        datePicker.init(Integer.valueOf(dateArray[0]), Integer.valueOf(dateArray[1]) - 1, Integer.valueOf(dateArray[2]), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker picker, int year, int month, int day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                date.setText(sdf.format(calendar.getTime()));
            }
        });
        builder.setView(datePicker);
        builder.setNeutralButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }


    /**
     * @param context
     * @param date    文本编辑器，规定日期格式：yyyy-MM-dd
     */
    public static void datePicker(Context context, final TextView date) {
        String[] dateArray = null;
        if (date.getText() != null && !date.getText().toString().equals("")) {
            dateArray = date.getText().toString().split("-");
        } else {
            dateArray = new SimpleDateFormat("yyyy-MM-dd").format(new Date()).split("-");
        }
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        DatePicker datePicker = new DatePicker(context);
        datePicker.setCalendarViewShown(true);
        // datePicker.setMaxDate(System.currentTimeMillis());
        datePicker.init(Integer.valueOf(dateArray[0]), Integer.valueOf(dateArray[1]) - 1, Integer.valueOf(dateArray[2]), new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker picker, int year, int month, int day) {
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, month);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                date.setText(sdf.format(calendar.getTime()));
            }
        });
        builder.setView(datePicker);
        builder.setNeutralButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }

    /**
     * 获取本地drawable 转换到bitmap
     *
     * @param resId
     * @param width
     * @param height
     * @return
     */
    public static Bitmap getBitMapFromDrawable(Context context, int resId, int width, int height) {
        Bitmap bm = BitmapFactory.decodeResource(context.getResources(), resId);
        if (width <= 0 || height <= 0) {
            return bm;
        }
        return Bitmap.createScaledBitmap(bm, width, height, false);
    }
}
