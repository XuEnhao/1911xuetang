package com.yijiuyiyiedu.xuetang.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.text.TextUtils;
import android.util.Base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

/**
 * Created by Administrator on 2016/6/4.
 */
public class SharedPreferencesUtil {

    private static final String TABLE_NAME = "auto";

    private static void paraCheck(SharedPreferences sp, String key) {
        if (sp == null) {
            throw new IllegalArgumentException();
        }
        if (TextUtils.isEmpty(key)) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * 存放布尔型信息
     *
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, boolean value) {
        SharedPreferences preferences = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 存放整型数值信息
     *
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, int value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            editor.commit();
        } else {

        }

    }

    /**
     * 存放字符串信息
     *
     * @param context
     * @param key
     * @param value
     */
    public static void put(Context context, String key, String value) {
        SharedPreferences preferences = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 读取布尔信息
     *
     * @param context
     * @param key
     * @return
     */
    public static boolean getBooleanValue(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
        return preferences.getBoolean(key, false);
    }

    /**
     * 读取整型信息
     *
     * @param context
     * @param key
     * @return
     */
    public static int getIntValue(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
        return preferences.getInt(key, 0);
    }

    /**
     * 读取字符串信息
     *
     * @param context
     * @param key
     * @return
     */
    public static String getStringValue(Context context, String key) {
        SharedPreferences preferences = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
        return preferences.getString(key, "");
    }

    /**
     * 存放bitmap
     */
    public static boolean putBitmap(Context context, String key, Bitmap bitmap) {
        SharedPreferences sp = context.getSharedPreferences(TABLE_NAME, Context.MODE_PRIVATE);
        paraCheck(sp, key);
        if (bitmap == null || bitmap.isRecycled()) {
            return false;
        } else {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos);
            String imageBase64 = new String(Base64.encode(baos.toByteArray(),
                    Base64.DEFAULT));
            SharedPreferences.Editor e = sp.edit();
            e.putString(key, imageBase64);
            return e.commit();
        }
    }

    /**
     * 取bitmap
     */
    public static Bitmap getBitmap(Context context, String key, Bitmap defaultValue) {
        SharedPreferences sp = context.getSharedPreferences(TABLE_NAME,
                Context.MODE_PRIVATE);
        paraCheck(sp, key);
        String imageBase64 = sp.getString(key, "");
        if (TextUtils.isEmpty(imageBase64)) {
            return defaultValue;
        }
        byte[] base64Bytes = Base64.decode(imageBase64.getBytes(),
                Base64.DEFAULT);
        ByteArrayInputStream bais = new ByteArrayInputStream(base64Bytes);
        Bitmap ret = BitmapFactory.decodeStream(bais);
        if (ret != null) {
            return ret;
        } else {
            return defaultValue;
        }
    }

    /**
     * 判断用户是否登录
     *
     * @param context
     * @return
     */
    public static boolean selectUserInfo(Context context) {
        if (TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(context, "token"))) {
            return false;
        } else {
            return true;
        }
    }
}
