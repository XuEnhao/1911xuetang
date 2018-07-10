package com.yijiuyiyiedu.xuetang.module.ui.activitys;


import android.os.Bundle;
import android.view.KeyEvent;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.SwipeBackActivity;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;


public class NewsChannelActivity extends SwipeBackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_channel);
    }



    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            JumpUtil.overiderAnimsition(this);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }
}
