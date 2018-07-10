package com.yijiuyiyiedu.xuetang.utils;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;

import com.yijiuyiyiedu.xuetang.R;
import com.zyao89.view.zloading.ZLoadingDialog;
import com.zyao89.view.zloading.Z_TYPE;


/**
 * 作者：jiangtao
 * <p>
 * 时间： 2017/8/21
 * <p>
 * 描述：进度条
 */

public class ProgressDialogUtils {

    private Context mContext;
    private static ProgressDialogUtils instance;
    private ZLoadingDialog dialog;

    public ProgressDialogUtils(Context context) {
        this.mContext = context;
        dialog = new ZLoadingDialog(mContext);
    }

    public static ProgressDialogUtils getInstance(Context context) {
        if (instance == null) {
            instance = new ProgressDialogUtils(context);
        }
        return instance;
    }

    public void showDialog() {
        if (mContext != null && !((Activity) mContext).isFinishing()) {
                dialog.setLoadingBuilder(Z_TYPE.ROTATE_CIRCLE)//设置类型
                        .setLoadingColor(Color.parseColor("#6317A5"))//颜色
                        .setHintText("Loading...")
                        .setHintTextSize(16) // 设置字体大小 dp
                        .setHintTextColor(Color.GRAY)  // 设置字体颜色
                        .setDurationTime(0.5) // 设置动画时间百分比 - 0.5倍
                        .setCancelable(false)
                        .setCanceledOnTouchOutside(false)//点击空白不关闭
                        .setDialogBackgroundColor(Color.parseColor("#ffffff"))// 设置背景色，默认白色
                        .show();

        }
    }

    public void dismissDialog() {
            dialog.dismiss();
    }

    public ZLoadingDialog getDidlog() {
        return dialog;
    }


}
