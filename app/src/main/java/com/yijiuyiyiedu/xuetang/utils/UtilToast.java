package com.yijiuyiyiedu.xuetang.utils;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class UtilToast {


    private static Toast toast;

    public static void showToast(Context context, String msg) {
        if (toast == null) {
            toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
        }
        toast.setText(msg);

        toast.show();
    }


    public static void showMyToast(Context context,String msg, final int cnt) {
        if (context!=null|| !((Activity)context).isFinishing()){
            if (toast == null) {
                toast = Toast.makeText(context, "", Toast.LENGTH_SHORT);
            }
            toast.setText(msg);
            toast.show();
            final Timer timer =new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.show();
                }
            },0,3000);
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    toast.cancel();
                    timer.cancel();
                }
            }, cnt );
        }
    }

}

