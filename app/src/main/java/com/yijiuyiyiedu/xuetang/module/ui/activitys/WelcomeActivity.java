package com.yijiuyiyiedu.xuetang.module.ui.activitys;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;


public class WelcomeActivity extends Activity {

    @BindView(R.id.welcome_img)
    ImageView welcomeImg;
    private Context mContext;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppWelcome);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ButterKnife.bind(this);
        mContext = this;

//        GlideUtils.loadImage(mContext,R.mipmap.demo,welcomeImg);
        handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent it = new Intent(mContext, MainActivity.class);
                startActivity(it);
                finish();
            }
        }, 2000);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) handler.removeCallbacksAndMessages(null);
    }
}
