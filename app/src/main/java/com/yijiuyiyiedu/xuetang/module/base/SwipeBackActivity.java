package com.yijiuyiyiedu.xuetang.module.base;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.widget.SwipeBackLayout;


/**
 * 1、想要实现向右滑动删除Activity效果只需要继承SwipeBackActivity即可，如果当前页面含有ViewPager
 * 只需要调用SwipeBackLayout的setViewPager()方法即可
 * 2、设置activity的主题为android:theme="@style/CustomTransparent
 */
public abstract class SwipeBackActivity extends BaseActivity {
    protected SwipeBackLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setTheme(R.style.CustomTransparent);
        layout = (SwipeBackLayout) LayoutInflater.from(this).inflate(
                R.layout.base_swipe, null);
        layout.attachToActivity(this);
    }

    @Override
    public void startActivity(Intent intent) {
        super.startActivity(intent);
        overridePendingTransition(R.anim.base_slide_right_in, R.anim.base_slide_right_out);
    }

    @Override
    public void onBackPressedSupport() {
        super.onBackPressedSupport();
    }


    //    // Press the back button in mobile phone
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
////        overridePendingTransition(0, R.anim.base_slide_right_out);
//    }


}
