package com.yijiuyiyiedu.xuetang.utils;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.PopupWindow;

import com.bumptech.glide.load.resource.drawable.DrawableResource;
import com.yijiuyiyiedu.xuetang.R;


/**
 * Created by andy on 2017/6/1.
 */

public class UtilsShowPopup {
    public  PopupWindow popupWindow;
    public  PopupWindow normalPopupWindow;
    public  PopupWindow sizePopupWindow;

    public UtilsShowPopup() {
    }

    public PopupWindow getPopupWindow(){
        return popupWindow;
    }


    public  void showBottomPopup(Context context, final Window window, View popupView, View parentView) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int mWith = wm.getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popupView, mWith, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        ColorDrawable colorDrawable = new ColorDrawable(context.getResources().getColor(R.color.colorTintRed));
//        popupWindow.setBackgroundDrawable(colorDrawable);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popupwind_membermanage);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.update();

        popupWindow.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);

        if (popupWindow.isShowing()) {
            WindowUtils.setWindowBackground(window, 0.6f);
        }
        //关闭popup时将背景恢复
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowUtils.setWindowBackground(window, 1f);
            }
        });


    }
    public  void showCenterPopup(Context context, final Window window, View popupView, View parentView) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int mWith = wm.getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popupView, mWith, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
//        ColorDrawable colorDrawable = new ColorDrawable(context.getResources().getColor(R.color.pop_background));
//        popupWindow.setBackgroundDrawable(colorDrawable);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(R.style.popupwind_membermanage);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.update();

        popupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);

        if (popupWindow.isShowing()) {
            WindowUtils.setWindowBackground(window, 0.60f);
        }
        //关闭popup时将背景恢复
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowUtils.setWindowBackground(window, 1f);
            }
        });


    }

    /**
     * 在指定view下弹出
     * @param context
     * @param window
     * @param popupView
     * @param bottomView
     */
    public  void showViewBottomPopup(Context context, final Window window, View popupView,View bottomView,int height,float alpha) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int mWith = wm.getDefaultDisplay().getWidth();
        popupWindow = new PopupWindow(popupView, mWith, height);
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
        popupWindow.update();
        if (Build.VERSION.SDK_INT == 24) {
            // 获取控件的位置，安卓系统=7.0
            int[] location = new int[2];
            bottomView.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];

            popupWindow.showAtLocation(bottomView, Gravity.NO_GRAVITY, 0, location[1] + bottomView.getHeight());
        } else {
            popupWindow.showAsDropDown(bottomView);
        }
//        popupWindow.showAsDropDown(bottomView);
        if (popupWindow.isShowing()) {
            WindowUtils.setWindowBackground(window, alpha);
        }
        //关闭popup时将背景恢复
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                WindowUtils.setWindowBackground(window, 1f);
            }
        });


    }

    public  void showNormalPopup(Context context, final Window window, View popupView, View parentView, boolean showbackgroun) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        int mWith = wm.getDefaultDisplay().getWidth();
        normalPopupWindow = new PopupWindow(popupView, mWith, ViewGroup.LayoutParams.MATCH_PARENT);
        normalPopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        normalPopupWindow.setFocusable(true);
        normalPopupWindow.setOutsideTouchable(true);
        normalPopupWindow.update();
        if (Build.VERSION.SDK_INT == 24) {
            // 获取控件的位置，安卓系统=7.0
            int[] location = new int[2];
            parentView.getLocationOnScreen(location);
            int x = location[0];
            int y = location[1];

            popupWindow.showAtLocation(parentView, Gravity.NO_GRAVITY, 0, location[1] + parentView.getHeight());
        } else {
            popupWindow.showAsDropDown(parentView);
        }
//        normalPopupWindow.showAsDropDown(parentView);
        //是否改变屏幕背景色
        if (showbackgroun) {
            if (normalPopupWindow.isShowing()) {
                WindowUtils.setWindowBackground(window, 0.6f);
            }
            //关闭popup时将背景恢复
            normalPopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowUtils.setWindowBackground(window, 1f);
                }
            });
        }
    }


    /**
     * 带尺寸的
     */
    public  void showSizePopup(Context context, final Window window, int with, int height, View popupView, View parentView, boolean showbackgroun) {
        sizePopupWindow = new PopupWindow(popupView, with, height);
        sizePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sizePopupWindow.setFocusable(true);
        sizePopupWindow.setOutsideTouchable(false);
        sizePopupWindow.update();
        sizePopupWindow.showAtLocation(parentView, Gravity.CENTER, 0, 0);
//        normalPopupWindow.showAsDropDown(parentView);
        //是否改变屏幕背景色
        if (showbackgroun) {
            if (sizePopupWindow.isShowing()) {
                WindowUtils.setWindowBackground(window, 0.6f);
            }
            //关闭popup时将背景恢复
            sizePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowUtils.setWindowBackground(window, 1f);
                }
            });
        }
    }

    /**
     * 带尺寸的
     */
    public  void showSizeRightPopup(Context context, final Window window, View popupView, View parentView, boolean showbackgroun) {
        sizePopupWindow = new PopupWindow(popupView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT);
        sizePopupWindow.setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        sizePopupWindow.setFocusable(true);
        sizePopupWindow.setOutsideTouchable(false);
        sizePopupWindow.setAnimationStyle(R.style.AnimationRightFade);
        sizePopupWindow.update();
        sizePopupWindow.showAtLocation(parentView, Gravity.RIGHT, 0, 100);
//        normalPopupWindow.showAsDropDown(parentView);
        //是否改变屏幕背景色
        if (showbackgroun) {
            if (sizePopupWindow.isShowing()) {
                WindowUtils.setWindowBackground(window, 0.6f);
            }
            //关闭popup时将背景恢复
            sizePopupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                @Override
                public void onDismiss() {
                    WindowUtils.setWindowBackground(window, 1f);
                }
            });
        }
    }
}
