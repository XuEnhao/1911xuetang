package com.yijiuyiyiedu.xuetang.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuenhao on 2018/6/19.
 * 下载数据库
 */

public class DownloadDataBase extends SQLiteOpenHelper {
    private static DownloadDataBase dbHelper = null;
    private SQLiteDatabase db;
    private static String TABLE_NAME = "DownLoadCa";
    private static String DATABASE_NAME = "1911_classroom.db";

    /**
     * status 0 完成状态 1下载中
     * video_address 视频地址
     * curriculum_id 课程id
     * parent_id 章节id
     * catalog_id 小节id
     * title title
     * size 视频大小
     */
    private static final String CREATE_TABLE = "create table "+TABLE_NAME+" ("
            + "curriculum_id integer, "
            + "chapter_id integer, "
            + "catalog_id integer, "
            + "video_address varchar(255), "
            + "title varchar(32), "
            + "status integer,"
            + "sort integer,"
            + "size varchar(32))";//数据库里的表

    // application 中调用 创建实例对象
    public static DownloadDataBase getInstance(Context context) {
        if (dbHelper == null)
            dbHelper = new DownloadDataBase(context, DATABASE_NAME, null, 1);
        return dbHelper;
    }

    public DownloadDataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
        LogUtil.LogD("DataBase","创建数据库");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    /**
     * 查询章列表
     * @param curriculum_id 课程id
     */
    public List<DownloadEntity> searchChapter(int curriculum_id){
        StringBuffer stringBuffer = null;
        open();
        Cursor cursor = db.query(TABLE_NAME, null, "curriculum_id",new String[]{""+curriculum_id}, null, null, null);
        List<DownloadEntity> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int catalog_id = cursor.getInt(cursor.getColumnIndex("catalog_id"));
                int curriculumId = cursor.getInt(cursor.getColumnIndex("curriculumId"));
                int chapterId = cursor.getInt(cursor.getColumnIndex("chapterId"));
                int status = cursor.getInt(cursor.getColumnIndex("status"));
                int sort = cursor.getInt(cursor.getColumnIndex("sort"));
                String videoAddress = cursor.getString(cursor.getColumnIndex("videoAddress"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String size = cursor.getString(cursor.getColumnIndex("size"));
                DownloadEntity entity = new DownloadEntity(curriculumId,chapterId,catalog_id,sort,status,videoAddress,title,size);
                list.add(entity);
            }
            LogUtil.LogE("数据库拿数据", "" + stringBuffer.toString());
        }
        cursor.close();
        closed();
        return list;
    }
    /**
     * 查询小节列表
     * @param chapter_id 章id
     */
    public List<DownloadEntity> searchCatalog(int chapter_id){
        open();
        Cursor cursor = db.query(TABLE_NAME, null, "chapter_id",new String[]{""+chapter_id}, null, null, null);
        List<DownloadEntity> list = new ArrayList<>();
        if (cursor != null && cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                int catalog_id = cursor.getInt(cursor.getColumnIndex("catalog_id"));
                int curriculumId = cursor.getInt(cursor.getColumnIndex("curriculumId"));
                int chapterId = cursor.getInt(cursor.getColumnIndex("chapterId"));
                int status = cursor.getInt(cursor.getColumnIndex("status"));
                int sort = cursor.getInt(cursor.getColumnIndex("sort"));
                String videoAddress = cursor.getString(cursor.getColumnIndex("videoAddress"));
                String title = cursor.getString(cursor.getColumnIndex("title"));
                String size = cursor.getString(cursor.getColumnIndex("size"));
                DownloadEntity entity = new DownloadEntity(curriculumId,chapterId,catalog_id,sort,status,videoAddress,title,size);
                list.add(entity);
            }
            LogUtil.LogE("数据库拿数据", "" + list.toString());
        }
        cursor.close();
        closed();
        return list;
    }


    /**
     * 删除节
     * @param catalog_id 节id
     */
    public int delCatalog(int catalog_id){
        StringBuffer stringBuffer = null;
        open();
        int delete = db.delete(TABLE_NAME, "catalog_id=?", new String[]{"" + catalog_id});
        closed();
        return delete;
    }



    // 清空的缓存数据  比如你可以在删除的时候顺便删掉本地缓存
    public void deleteUserBye() {
        open();
        // 清空表数据
        db.execSQL("DROP FROM " + TABLE_NAME);
        closed();
    }

    public void ClearUserBye() {
        open();
        // 清空表数据
        db.execSQL("DELETE FROM " + TABLE_NAME);
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
