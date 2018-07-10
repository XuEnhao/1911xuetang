package com.yijiuyiyiedu.xuetang;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.support.v4.content.LocalBroadcastManager;

import com.yijiuyiyiedu.xuetang.utils.FastBlur;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

public class MyService extends Service {
    private OnServiceProgressListener listener;
    private Handler handler;
    private boolean isLoad = false;

    public MyService() {
    }


    @Override
    public void onCreate() {
        //使用mainlooper 确保在UI线程执行
        LogUtil.LogD("service","已成功注册： " );
        handler = new Handler(getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                if (listener != null) {
                    listener.onProgressChanged(msg.what);
                }
            }
        };
        if (!isLoad){
            isLoad = true;
            increaseNumber();
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        return START_STICKY;
    }

    public interface OnServiceProgressListener {
        void onProgressChanged(int progress);
    }

    public void setOnServiceProgressChangedListener(OnServiceProgressListener listener) {
        this.listener = listener;
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
                        handler.sendEmptyMessage(i);
                        LogUtil.LogD("service","耗时操作： " + i);

                        //广播机制通信
                        Intent intent = new Intent("com.yijiuyiyiedu.xuetang");
                        intent.putExtra("extra_data", String.valueOf(i));
                        LocalBroadcastManager.getInstance(getApplicationContext()).sendBroadcast(intent);
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        return new MyBinder();
    }

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }


}
