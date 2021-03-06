package com.yijiuyiyiedu.xuetang.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yijiuyiyiedu.xuetang.module.entity.UserSearchCache;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by {xeh}
 */

public class _1911_classRoom extends SQLiteOpenHelper {

    private static _1911_classRoom dbHelper = null;
    private SQLiteDatabase db;
    private static String TABLE_USER_CACHE = "UserSearchCache";
    private static String DATABASE_NAME = "1911_classroom.db";

    // application 中调用 创建实例对象
    public static _1911_classRoom getInstance(Context context) {
        if (dbHelper == null)
            dbHelper = new _1911_classRoom(context, DATABASE_NAME, null, 1);
        return dbHelper;
    }

    public _1911_classRoom(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    // 创建数据库
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table if not exists " + TABLE_USER_CACHE + "(searchKey text)");
    }

    // 更新数据库
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    // 保存数据进来保存到数据库
    public void saveUserSearchCache(UserSearchCache userCache) {
        open();
        ContentValues values = new ContentValues();
        values.put("searchKey", userCache.getSearchKey());
        Log.e("searchKey：", userCache.toString());
        db.insert(TABLE_USER_CACHE, null, values);
        Log.e("insert", "success");
        db.close();
    }

    // 获取所有缓存信息
    public List<UserSearchCache> getUserSearchMsgCacheList() {
        open();
        List<UserSearchCache> userCacheList = new ArrayList<UserSearchCache>();
        Cursor cursor = db.query(TABLE_USER_CACHE, null, null, null, null, null, null);
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                UserSearchCache userCache = new UserSearchCache();
                String avatarUrl = cursor.getString(cursor.getColumnIndex("searchKey"));
                userCache.setSearchKey(avatarUrl);
                userCacheList.add(userCache);
            }
            Log.e("数据库拿数据", "" + userCacheList.toString());
        }
        cursor.close();
        closed();
        return userCacheList;
    }


    // 清空的缓存数据  比如你可以在删除的时候顺便删掉本地缓存
    public void deleteUserBye() {
        open();
        // 清空表数据
        db.execSQL("DROP FROM " + TABLE_USER_CACHE);
        closed();
    }

    public void ClearUserBye() {
        open();
        // 清空表数据
        db.execSQL("DELETE FROM " + TABLE_USER_CACHE);
        closed();
    }

    // 打开数据库
    private void open() {
        db = dbHelper.getWritableDatabase();
    }

    // 关闭数据库
    private void closed() {
        db.close();
    }
}
