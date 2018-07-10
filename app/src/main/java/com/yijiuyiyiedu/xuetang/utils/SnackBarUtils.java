package com.yijiuyiyiedu.xuetang.utils;

import android.graphics.Color;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;


/**
 * Created by andy on 2017/5/15.
 */

public class SnackBarUtils {
    /**
     * 顶部弹框
     *
     * @param view
     * @param title
     * @param time
     * @param textColor
     * @param banner
     */
//    public static void showTopSnackBar(View view, String title, int time, String textColor, String banner){
//        TSnackbar snackbar = TSnackbar
//                .make(view, title,time);
//        snackbar.setActionTextColor(Color.WHITE);
//        View snackbarView = snackbar.getView();
//        snackbarView.setBackgroundColor(Color.parseColor(banner));
//        TextView textView = (TextView) snackbarView.findViewById(com.androidadvance.topsnackbar.R.id.snackbar_text);
//        textView.setTextSize(13);
//        textView.setTextColor(Color.parseColor(textColor));
//        snackbar.show();
//    }
    public static void showNormalSnackBar(View view, String title, int time, String textColor, String banner) {
        Snackbar snackbar = Snackbar.make(view, title, time);
        View snackbarView = snackbar.getView();
        snackbarView.setBackgroundColor(Color.parseColor(banner));
        TextView textView = (TextView) snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        textView.setTextColor(Color.parseColor(textColor));
        snackbar.show();

    }
}
