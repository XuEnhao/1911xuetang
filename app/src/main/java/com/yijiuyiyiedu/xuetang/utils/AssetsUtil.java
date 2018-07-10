package com.yijiuyiyiedu.xuetang.utils;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * guangzheng
 * 2016/6/1
 */
public class AssetsUtil {

    /**
     * 从assets中获取位图
     *
     * @param resource
     * @param fileName
     * @return
     */
    public static Bitmap getImageFromAssets(Resources resource, String fileName) {
        if (null == resource || TextUtils.isEmpty(fileName)) {
            return null;
        }
        Bitmap image = null;
        AssetManager am = resource.getAssets();
        InputStream is = null;
        try {
            is = am.open(fileName);
            image = BitmapFactory.decodeStream(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return image;

    }

    /**
     * 获取字符串
     *
     * @param context
     * @param fileName
     * @return
     */
    public static String getStringFromAssets(Context context, String fileName) {
        String result = "";

        InputStream in;
        try {
            in = context.getResources().getAssets().open(fileName);
            int length = in.available();
            byte[] buffer = new byte[length];
            in.read(buffer);
            result = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
