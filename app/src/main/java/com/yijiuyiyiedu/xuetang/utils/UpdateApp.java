package com.yijiuyiyiedu.xuetang.utils;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

import com.yijiuyiyiedu.xuetang.module.ui.activitys.MainActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by andy on 2017/3/3.
 */

public class UpdateApp {

    /* 下载中 */
    private static final int DOWNLOAD = 1;
    /* 下载结束 */
    private static final int DOWNLOAD_FINISH = 2;
    //判断是否更新中
    public static String tag = "1";//未点击更新

    /* 下载保存路径 */
    private String mSavePath;
    /* 记录进度条数量 */
    private int progress = 0;
    /* 是否取消更新 */
    private boolean cancelUpdate = false;


    private String ver;//版本号
    private String urlapk;//apk地址
    private String notes;//升级说明
    private Context mContext;
    private int len;
    private NotificationManager manager;
    private Notification notif;


    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                // 正在下载
                case DOWNLOAD:
//                    notif.contentView.setTextViewText(R.id.content_view_text1, "正在下载...  "+progress+"%");
//                    notif.contentView.setProgressBar(R.id.content_view_progress, 100, progress, false);
                    LogUtil.LogD("log", "--------------" + progress);
                    manager.notify(0, notif);
                    break;
                case DOWNLOAD_FINISH:
                    // 安装文件
//                    notif.contentView.setTextViewText(R.id.content_view_text1, "下载完成！");
//                    notif.contentView.setProgressBar(R.id.content_view_progress, 100, 100, false);
                    manager.notify(0, notif);
                    tag = "2";//更新完成开始安装
                    installApk();
                    break;
                default:
                    break;
            }
        }
    };

    public UpdateApp(String ver, String url, String notes, Context context) {
        this.mContext = context;
        this.ver = ver;
        this.urlapk = url;
        this.notes = notes;
    }

    public void upDate() {
        Toast.makeText(mContext, "后台更新中，请稍后...", Toast.LENGTH_LONG).show();
        Intent intent = new Intent(mContext, MainActivity.class);
        PendingIntent pIntent = PendingIntent.getActivity(mContext, 0, intent, 0);
        manager = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
        notif = new Notification();
//        notif.icon = R.drawable.ic_app;//app图标
        notif.tickerText = "更新通知";
        //通知栏显示所用到的布局文件
//        notif.contentView = new RemoteViews(mContext.getPackageName(), R.layout.uploade);// 更新用到的布局文件
        notif.contentIntent = pIntent;
        manager.notify(0, notif);
        // 下载文件
        downloadApk();
    }

    /**
     * 下载apk文件
     */
    private void downloadApk() {
        // 启动新线程下载软件

        new downloadApkThread().start();
    }


    int downloadCount = 0;

    /**
     * 下载app
     */
    private class downloadApkThread extends Thread {
        @Override
        public void run() {
            try {

                // 判断SD卡是否存在，并且是否具有读写权限
                if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
                    // 获得存储卡的路径
//                    String sdpath = Environment.getExternalStorageDirectory() + File.separator;
                    mSavePath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/downloads/";
                    URL url = new URL(urlapk);
                    // 创建连接
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.connect();
                    // 获取文件大小
                    int length = conn.getContentLength();
                    // 创建输入流
                    InputStream is = conn.getInputStream();

                    File file = new File(mSavePath);
                    // 判断文件目录是否存在
                    if (!file.exists()) {
                        file.mkdir();
                    }
                    String apkpath = mSavePath + "1911学堂.apk";
                    File apkFile = new File(apkpath);
                    FileOutputStream fos = new FileOutputStream(apkFile);
                    int count = 0;
                    // 缓存
                    byte buf[] = new byte[1024];
                    // 写入到文件中
                    do {
                        int numread = is.read(buf);
                        count += numread;
                        // 计算进度条位置
                        progress = (int) (((float) count / length) * 100);
                        LogUtil.LogD("progress", "progress===" + progress);
                        LogUtil.LogD("progress", "count===" + count + numread);
                        // 更新进度
                        //为了防止频繁的通知导致应用吃紧，百分比增加10才通知一次
                        if (downloadCount == 0 || progress - 5 > downloadCount) {
                            downloadCount += 5;
                            mHandler.sendEmptyMessage(DOWNLOAD);
                        }
                        if (numread <= 0) {
                            // 下载完成
                            mHandler.sendEmptyMessage(DOWNLOAD_FINISH);
                            break;
                        }
                        // 写入文件
                        fos.write(buf, 0, numread);
                    } while (!cancelUpdate);// 点击取消就停止下载.
                    fos.close();
                    is.close();
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    /**
     * 安装APK文件
     */
    private void installApk() {

        File apkfile = new File(mSavePath, "1911学堂.apk");
        if (!apkfile.exists()) {
            return;
        }
        // 通过Intent安装APK文件
        ;
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setDataAndType(Uri.parse("file://" + apkfile.toString()), "application/vnd.android.package-archive");

        mContext.startActivity(intent);

    }
}
