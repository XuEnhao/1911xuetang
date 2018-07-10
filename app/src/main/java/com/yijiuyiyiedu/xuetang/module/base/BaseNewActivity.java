package com.yijiuyiyiedu.xuetang.module.base;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.NetWorkUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import butterknife.Unbinder;

/**
 * Description: BaseActivity
 */
public abstract class BaseNewActivity<T extends BasePresenter> extends Activity {
    private final String TAG = "BaseNewActivity";
    protected Unbinder unbinder;
    protected T mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.LogE(TAG, "------>onCreate");
        init();

    }

    protected void init() {
        setTranslucentStatus(true);
        BaseApplication.getInstance().registerActivity(this);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//不可以旋转屏幕
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//软键盘默认不弹出
        this.overridePendingTransition(R.anim.in_lefttoright, R.anim.base_slide_right_out1);
        //判断网络
        judgeNetWork();
    }

    private void judgeUserStatus() {
        if (SharedPreferencesUtil.getStringValue(this, "userStatus") != null) {

        }
    }

    private void judgeNetWork() {
        if (!NetWorkUtil.isWifiConnected(this) &&
                !NetWorkUtil.is3gConnected(this) &&
                !NetWorkUtil.isNetConnected(this)) {

            UtilToast.showToast(this, "网络开了小差了");
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
//        setTitleHeight(getRootView(this));
        LogUtil.LogE(TAG, "------>onStart");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.LogE(TAG, "------>onRestart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        LogUtil.LogE(TAG, "------>onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        LogUtil.LogE(TAG, "------>onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        LogUtil.LogE(TAG, "------>onStop");
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            JumpUtil.overiderAnimsition(this);
            return true;
        } else {
            return super.onKeyDown(keyCode, event);
        }
    }

    @Override
    protected void onDestroy() {
        BaseApplication.getInstance().unregisterActivity(this);
        super.onDestroy();
        LogUtil.LogE(TAG, "------>onDestroy");
        if (unbinder != null)
            unbinder.unbind();
        mPresenter = null;
    }

    /**
     * 设置沉浸式
     *
     * @param on
     */
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            Window window = getWindow();
//            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.setStatusBarColor(getResources().getColor(R.color.hpplay_transparent));
//            //底部导航栏
//            //window.setNavigationBarColor(activity.getResources().getColor(colorResId));
//        }
    }

    /**
     * 放弃5。0以下的用户
     * @param on
     * @param statusColor 需要设置的颜色
     */
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public void setStatus(boolean on,int statusColor){
        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
            if (statusColor!=-1){
                getWindow().setStatusBarColor(statusColor);
            }
            return;
        }

    }
}
