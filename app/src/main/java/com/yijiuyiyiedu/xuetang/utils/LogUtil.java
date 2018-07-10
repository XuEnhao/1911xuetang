/*******************************************************************************
 * Copyright (c) 2015 by dennis Corporation all right reserved.
 * 2015年11月20日16:39:01 
 *
 *******************************************************************************/
package com.yijiuyiyiedu.xuetang.utils;

import android.util.Log;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;

/**
 * LogUtils
 */
public class LogUtil {

    private static boolean flag = Constant.DEBUG;

    public static void LogE(String TAG, String msg) {
        if (flag) {
            Log.e(TAG, msg);
        }
    }

    public static void LogD(String TAG, String msg) {
        if (flag) {
            Log.d(TAG, msg);
        }
    }

    public static void LogI(String TAG, String msg) {
        if (flag) {
            Log.i(TAG, msg);
        }
    }
}
