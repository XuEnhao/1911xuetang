package com.yijiuyiyiedu.xuetang;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.tencent.rtmp.downloader.ITXVodDownloadListener;
import com.tencent.rtmp.downloader.TXVodDownloadManager;
import com.tencent.rtmp.downloader.TXVodDownloadMediaInfo;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

/**
 * Created by xuenhao on 2018/6/19.
 * 下载视频
 */

public class DownloadService extends Service {
    static final int Flag_Init = 0; // 初始状态
    static final int Flag_Down = 1; // 下载状态
    static final int Flag_Pause = 2; // 暂停状态
    static final int Flag_Done = 3; // 完成状态
    TXVodDownloadManager downloader ;
    String url;//下载地址
    String filetype;//文件类型
    DownloadListener downloadListner;
    private int progress = 0;// 下载进度
    private TXVodDownloadMediaInfo info;
    private Handler handler;
    public int getProgress() {
        return progress;
    }
    private int flag;// 下载状态标志
    private boolean isPause = false;
    public int getFlag() {

        return flag;
    }

    public void setDownloadListener(DownloadService.DownloadListener downloadLisentner) {
        this.downloadListner = downloadLisentner;
    }

    private static DownloadService instance;
    public static DownloadService getInstance() {
        return instance;
    }
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        String msg = intent.getExtras().getString("flag");//通过标识判断下载状态
        url = intent.getExtras().getString("url");
        filetype=intent.getExtras().getString("filetype");
        if(downloader == null)
        {
            downloader = TXVodDownloadManager.getInstance();
        }
        downloader.setListener(listener);

        if (msg.equals("start"))
        {
            isPause = false;
//            info = downloader.startDownloadUrl(url);
            increaseNumber();
        }
        else if (msg.equals("pause"))
        {
            isPause = true;
//            downloader.stopDownload();
        }
        else if (msg.equals("resume"))
        {
//            downloader.resume();
        }
        else if (msg.equals("stop"))
        {
            if (info!=null)
            downloader.stopDownload(info);
        }

        return START_STICKY;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //使用mainlooper 确保在UI线程执行
//        handler = new Handler(getMainLooper()) {
//            @Override
//            public void handleMessage(Message msg) {
//                if (listener != null) {
////                    listener.onDownloadProgress();
//                }
//            }
//        };
        LogUtil.LogI("downloadService", "service.........onCreate");
        instance = this;
        flag = Flag_Init;
    }

    /**
     * 递增操作，耗时
     */
    public void increaseNumber() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 1000; i++) {
                    try {
//                        handler.sendEmptyMessage(i);
                        if (!isPause){
                            LogUtil.LogD("downloadService","耗时操作： " + i);

                            //广播机制通信
                            Intent intent = new Intent("com.yijiuyiyiedu.xuetang");
                            intent.putExtra("extra_data", String.valueOf(i));
                            LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                            Thread.sleep(500);
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    private ITXVodDownloadListener listener = new ITXVodDownloadListener() {
        @Override
        public void onDownloadStart(TXVodDownloadMediaInfo txVodDownloadMediaInfo) {
            LogUtil.LogI("downloadService", "service.........onDownloadStart");
            if (downloadListner!=null)
            downloadListner.onStart(txVodDownloadMediaInfo);
        }

        @Override
        public void onDownloadProgress(TXVodDownloadMediaInfo txVodDownloadMediaInfo) {
            LogUtil.LogI("downloadService", "service.........onDownloadProgress");

            if (downloadListner!=null)
            downloadListner.onProgress(txVodDownloadMediaInfo);
        }

        @Override
        public void onDownloadStop(TXVodDownloadMediaInfo txVodDownloadMediaInfo) {
            LogUtil.LogI("downloadService", "service.........onDownloadStop");

            if (downloadListner!=null)
            downloadListner.onStop(txVodDownloadMediaInfo);
        }

        @Override
        public void onDownloadFinish(TXVodDownloadMediaInfo txVodDownloadMediaInfo) {
            LogUtil.LogI("downloadService", "service.........onDownloadFinish");

            if (downloadListner!=null)
            downloadListner.onFinish(txVodDownloadMediaInfo);
        }

        @Override
        public void onDownloadError(TXVodDownloadMediaInfo txVodDownloadMediaInfo, int i, String s) {
            LogUtil.LogI("downloadService", "service.........onDownloadError"+txVodDownloadMediaInfo.getUrl());

            if (downloadListner!=null)
                downloadListner.onError(txVodDownloadMediaInfo);
        }
    };

    public interface DownloadListener{
        void onStart(TXVodDownloadMediaInfo txVodDownloadMediaInfo);
        void onStop(TXVodDownloadMediaInfo txVodDownloadMediaInfo);
        void onFinish(TXVodDownloadMediaInfo txVodDownloadMediaInfo);
        void onProgress(TXVodDownloadMediaInfo txVodDownloadMediaInfo);
        void onError(TXVodDownloadMediaInfo txVodDownloadMediaInfo);
    }
    public class MyBinder extends Binder {
        public DownloadService getService() {
            return DownloadService.this;
        }
    }

}
