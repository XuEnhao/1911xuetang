package com.yijiuyiyiedu.xuetang;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yijiuyiyiedu.xuetang.utils.LogUtil;


public class ServiceActivity extends AppCompatActivity implements MyService.OnServiceProgressListener {

    private MyService myService = null;
    private MyReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download_details);
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter("com.yijiuyiyiedu.xuetang");
        LocalBroadcastManager.getInstance(getApplicationContext()).registerReceiver(myReceiver, filter);
        onBindService(null);
    }

    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            myService = ((MyService.MyBinder) service).getService();
            myService.setOnServiceProgressChangedListener(ServiceActivity.this);
//            onCommunication(null);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            LogUtil.LogD("service","service disconnected.");
        }
    };
    public void onBindService(final View view) {
        Intent i = new Intent(this, MyService.class);
        bindService(i, connection, BIND_AUTO_CREATE);
    }

    public void onCommunication(View view) {
        myService.increaseNumber();
    }

    @Override
    public void onProgressChanged(int progress) {
        LogUtil.LogD("service","value via interface: " + progress);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        unbindService(connection);
        LocalBroadcastManager.getInstance(getApplicationContext()).unregisterReceiver(myReceiver);
    }

    /**
     * 广播接收Service消息
     */
    class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String value = intent.getStringExtra("extra_data");
            LogUtil.LogD("service","value via broadcast: " + value);
        }
    }
}
