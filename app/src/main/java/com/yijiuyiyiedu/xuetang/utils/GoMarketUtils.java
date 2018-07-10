package com.yijiuyiyiedu.xuetang.utils;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

/**
 * Created by xuenhao on 2018/6/25.
 */

public class GoMarketUtils {
    /**
     * 跳转应用宝
     * @param context
     * @param packageName
     */
    public static void goToMarket(Context context, String packageName) {
//        Uri uri = Uri.parse("market://details?id=" + packageName);
//        Intent goToMarket = new Intent(Intent.ACTION_VIEW, uri);
        Uri uri = Uri.parse("market://search?q=pub:+" + packageName);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
//        Intent goToMarket = new Intent(Intent.ACTION_VIEW);
        try {
            intent.setClassName("com.tencent.android.qqdownloader", "com.tencent.pangu.link.LinkProxyActivity");
            context.startActivity(intent);
            Log.d("goToMarket", "goToMarket: 跳转"+"");
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
            Log.d("goToMarket", "goToMarket: 出错"+"");

        }
    }

    /**
     * 跳转三星
     * @param context
     * @param packageName
     */
    public static void goToSamsungappsMarket(Context context, String packageName) {
        Uri uri = Uri.parse("http://www.samsungapps.com/appquery/appDetail.as?appId=" + packageName);
        Intent goToMarket = new Intent();
        goToMarket.setClassName("com.sec.android.app.samsungapps", "com.sec.android.app.samsungapps.Main");
        goToMarket.setData(uri);
        try {
            context.startActivity(goToMarket);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        }
    }


}
