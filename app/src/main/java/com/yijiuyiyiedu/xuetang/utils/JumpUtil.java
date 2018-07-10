package com.yijiuyiyiedu.xuetang.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import com.yijiuyiyiedu.xuetang.R;


/**
 * Created by ${二星} on 2017/4/26 0026.
 * <p>
 * 一次创建，可迭代指定方法
 */

public class JumpUtil {

    // 泛型跳转方法
    public static void jump(Context a, Class<?> clazz) {
        Intent intent = new Intent(a, clazz);
        a.startActivity(intent);
    }

    // 切入动画
    public static void overiderAnimsition(Activity activity) {
        activity.finish();
        activity.overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_right_out);
    }

}
