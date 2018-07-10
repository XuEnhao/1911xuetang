package com.yijiuyiyiedu.xuetang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.yijiuyiyiedu.xuetang.R;


/**
 * Created by Administrator on 2016/11/11 0011.
 */

public class UtilsShowWindow {

    public static PopupWindow mPopupWindow;
    private static AlertDialog.Builder builder;
    private static AlertDialog dialog;

    /**
     * mContext   上下文
     * popupView  PopupWindow的布局视图
     * location   PopupWindow的位置相对控件
     * backColor  PopupWindow的背景颜色  "#******"样式
     * is_need    PopupWindow点击外部是否响应事件
     * 该PopupWindow从屏幕的底部展示出来
     */
    public static void showNoticePop(final Context mContext, View popupView, View location, String backColor, int height, boolean is_need) {
        //初始化PopupWindow
        WindowManager wm = (WindowManager) mContext
                .getSystemService(Context.WINDOW_SERVICE);
        int mwidth = wm.getDefaultDisplay().getWidth();
        mPopupWindow = new PopupWindow(popupView, mwidth, ActionBar.LayoutParams.WRAP_CONTENT);
//        mPopupWindow.setContentView(popupView);
        mPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.parseColor(backColor)));
        mPopupWindow.setFocusable(is_need);
        mPopupWindow.setOutsideTouchable(true);
        mPopupWindow.update();
        if (is_need) {
            mPopupWindow.setSoftInputMode(PopupWindow.INPUT_METHOD_NEEDED);
            mPopupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        }
        //设置动画
        mPopupWindow.setAnimationStyle(R.style.popupwind_membermanage);
        mPopupWindow.getContentView().setFocusableInTouchMode(true);
        mPopupWindow.getContentView().setFocusable(true);

        // 设置返回键动作
        mPopupWindow.getContentView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_MENU && event.getRepeatCount() == 0
                        && event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (mPopupWindow != null && mPopupWindow.isShowing()) {
                        mPopupWindow = null;
                        mPopupWindow.dismiss();

                    }
                    return true;
                }
                return false;
            }
        });

        mPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                if (mPopupWindow != null) {
                    mPopupWindow.dismiss();
                }
                mPopupWindow = null;
            }
        });
        mPopupWindow.showAtLocation(location, Gravity.BOTTOM, 0, 0);
    }

    /**
     * 关闭PopupWindow
     */
    public static void dismissPop() {
        if (mPopupWindow != null) {
            mPopupWindow.dismiss();
        }
    }


    /**
     * mContext     上下文
     * title        标题
     * message      内容
     * negativeS    取消的字样
     * positiveS    确定的字样
     * negative     取消的点击事件
     * positive     确定的点击事件
     */
    public static void showDialog(Context mContext, String title, String message, String negativeS, String positiveS, DialogInterface.OnClickListener negative, DialogInterface.OnClickListener positive) {
    /*
        这里使用了 android.support.v7.app.AlertDialog.Builder
        可以直接在头部写 import android.support.v7.app.AlertDialog
        那么下面就可以写成 AlertDialog.Builder
    */
        builder = new AlertDialog.Builder(mContext);
        if (title != null) {
            builder.setTitle(title);
        }
        builder.setMessage(message);
        builder.setNegativeButton(negativeS, negative);
        builder.setPositiveButton(positiveS, positive);
        builder.show();
    }

    /**
     * 自定义Dialog
     * view  视图
     */
    public static void showMyDialog(Context mContext, View view) {
    /*
        这里使用了 android.support.v7.app.AlertDialog.Builder
        可以直接在头部写 import android.support.v7.app.AlertDialog
        那么下面就可以写成 AlertDialog.Builder
    */
        builder = new AlertDialog.Builder(mContext);
        dialog = builder.create();
        //这里设置了底部与方向
        dialog.getWindow().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        WindowManager.LayoutParams lp = dialog.getWindow().getAttributes();
        lp.windowAnimations = R.style.popupwind_membermanage;  //这的styleId，是从Styel.xml里面拿取的
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.getWindow().setAttributes(lp);

        dialog.setCanceledOnTouchOutside(false);
        dialog.setView(view);
        dialog.show();
    }

    /**
     * 关闭AlertDialog
     */
    public static void dismissDialog() {
        dialog.dismiss();
    }

    //popupwindow背景色
    public static void backgroundAlpha(float bgAlpha, Activity activity) {
        WindowManager.LayoutParams lp = activity.getWindow().getAttributes();
        lp.alpha = bgAlpha; //0.0-1.0
        if (bgAlpha == 1) {
            activity.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//不移除该Flag的话,在有视频的页面上的视频会出现黑屏的bug  
        } else {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//此行代码主要是解决在华为手机上半透明效果无效的bug  
        }
        activity.getWindow().setAttributes(lp);
    }

}
