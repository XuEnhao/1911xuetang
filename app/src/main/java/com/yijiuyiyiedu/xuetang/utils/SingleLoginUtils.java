package com.yijiuyiyiedu.xuetang.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PassLoginActivity;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by xuenhao on 2018/6/22.
 * 单点登录
 */

public class SingleLoginUtils {

    public static void showDialog(final Context mContext) {
        if (mContext != null) {
            if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
                builder.setMessage("您的账号已在另一台设备登录，您被迫下线");
                builder.setTitle("被迫下线");
                builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferencesUtil.put(mContext, "token", "");//清除用户数据
                        UriConstant.clear();
                        Intent it = new Intent(mContext, PassLoginActivity.class);
                        it.putExtra("jump", 0);
                        mContext.startActivity(it);
                        EventBus.getDefault().postSticky(new EditMyselfInfoBus("1"));
                        dialog.dismiss();
                    }
                });
                //不关闭写法
                builder.setCancelable(false);
//                    AlertDialog alertDialog = builder.create();
                builder.show();
            }
        }
    }
}
