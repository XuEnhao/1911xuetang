package com.yijiuyiyiedu.xuetang.utils;

import android.Manifest;
import android.app.Activity;
import android.support.v4.app.ActivityCompat;

/**
 *
 */

public class PermissionUtils {

    /**
     * 友盟分享的权限
     *
     * @param activity
     */
    public static void sharePermission(Activity activity) {
        String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE,
                Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP,
                Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.WRITE_APN_SETTINGS};
        ActivityCompat.requestPermissions(activity, mPermissionList, 11);
    }
}
