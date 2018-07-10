package com.yijiuyiyiedu.xuetang.module.base;

import android.content.pm.ActivityInfo;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;

import com.umeng.analytics.MobclickAgent;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.NetWorkUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;
import com.yijiuyiyiedu.xuetang.utils.statustoolbar.Eyes;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import org.greenrobot.eventbus.EventBus;

import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Description: BaseActivity
 */
public abstract class BaseActivity<T extends BasePresenter> extends SupportActivity implements BGASwipeBackHelper.Delegate{
    private final String TAG = "BaseActivity";
    protected Unbinder unbinder;
    protected T mPresenter;
    private final String className = getClass().getSimpleName();
    protected BGASwipeBackHelper mSwipeBackHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.LogE(TAG, "------>onCreate");
        init();

    }

    protected void init() {
//        setTranslucentStatus(true);
        if (SharedPreferencesUtil.selectUserInfo(this)){
//            UriConstant.userId = SharedPreferencesUtil.getStringValue(this,"userId");
            UriConstant.token = SharedPreferencesUtil.getStringValue(this, "token");
        }
        BaseApplication.getInstance().registerActivity(this);
        initSwipeBackFinish();
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);//不可以旋转屏幕
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);//软键盘默认不弹出
        this.overridePendingTransition(R.anim.in_lefttoright, R.anim.base_slide_right_out1);
        //状态栏颜色
        Eyes.setStatusBarLightMode(this, getResources().getColor(R.color.colorWrite));
//        //判断网络
        judgeNetWork();
//        if (!judgeNetWork()){
//            NotNetWork();
//        }
    }


    private void judgeUserStatus() {
        if (SharedPreferencesUtil.getStringValue(this, "userStatus") != null) {

        }
    }
//    protected abstract void NotNetWork();

    /**
     * 判断是否有网络
     * @return
     */
    public boolean judgeNetWork() {
        if (!NetWorkUtil.isWifiConnected(this) &&
                !NetWorkUtil.is3gConnected(this) &&
                !NetWorkUtil.isNetConnected(this)) {

                UtilToast.showToast(this, "网络开了小差了");
                return false;
        }
        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
//        setTitleHeight(getRootView(this));
        LogUtil.LogE(TAG, "------>onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        //友盟统计时长
        LogUtil.LogE(TAG, "------>onResume");
        MobclickAgent.onPageStart(className); //手动统计页面("SplashScreen"为页面名称，可自定义)
        MobclickAgent.onResume(this); //统计时长
    }
    @Override
    protected void onPause() {
        super.onPause();
        //友盟统计时长
        LogUtil.LogE(TAG, "------>onPause");
        MobclickAgent.onPageEnd(className); //手动统计页面("SplashScreen"为页面名称，可自定义)，必须保证 onPageEnd 在 onPause 之前调用，因为SDK会在 onPause 中保存onPageEnd统计到的页面数据。
        MobclickAgent.onPause(this);
    }
    @Override
    protected void onRestart() {
        super.onRestart();
        LogUtil.LogE(TAG, "------>onRestart");
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
        WindowUtils.hintKeyboard(this);
        EventBus.getDefault().unregister(this);
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
    }
    /**
     * 初始化滑动返回。在 super.onCreate(savedInstanceState) 之前调用该方法
     */
    private void initSwipeBackFinish() {
        mSwipeBackHelper = new BGASwipeBackHelper(this, this);

        // 「必须在 Application 的 onCreate 方法中执行 BGASwipeBackHelper.init 来初始化滑动返回」
        // 下面几项可以不配置，这里只是为了讲述接口用法。

        // 设置滑动返回是否可用。默认值为 true
        mSwipeBackHelper.setSwipeBackEnable(true);
        // 设置是否仅仅跟踪左侧边缘的滑动返回。默认值为 true
        mSwipeBackHelper.setIsOnlyTrackingLeftEdge(true);
        // 设置是否是微信滑动返回样式。默认值为 true
        mSwipeBackHelper.setIsWeChatStyle(true);
        // 设置阴影资源 id。默认值为 R.drawable.bga_sbl_shadow
        mSwipeBackHelper.setShadowResId(R.drawable.bga_sbl_shadow);
        // 设置是否显示滑动返回的阴影效果。默认值为 true
        mSwipeBackHelper.setIsNeedShowShadow(true);
        // 设置阴影区域的透明度是否根据滑动的距离渐变。默认值为 true
        mSwipeBackHelper.setIsShadowAlphaGradient(true);
        // 设置触发释放后自动滑动返回的阈值，默认值为 0.3f
        mSwipeBackHelper.setSwipeBackThreshold(0.3f);
        // 设置底部导航条是否悬浮在内容上，默认值为 false
        mSwipeBackHelper.setIsNavigationBarOverlap(false);
    }

    /**
     * 是否支持滑动返回。这里在父类中默认返回 true 来支持滑动返回，如果某个界面不想支持滑动返回则重写该方法返回 false 即可
     *
     * @return
     */
    @Override
    public boolean isSupportSwipeBack() {
        return true;
    }

    /**
     * 正在滑动返回
     *
     * @param slideOffset 从 0 到 1
     */
    @Override
    public void onSwipeBackLayoutSlide(float slideOffset) {
    }

    /**
     * 没达到滑动返回的阈值，取消滑动返回动作，回到默认状态
     */
    @Override
    public void onSwipeBackLayoutCancel() {
    }

    /**
     * 滑动返回执行完毕，销毁当前 Activity
     */
    @Override
    public void onSwipeBackLayoutExecuted() {
        mSwipeBackHelper.swipeBackward();
    }

//    /**
//     * 放弃5。0以下的用户
//     * @param on
//     * @param statusColor 需要设置的颜色
//     */
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    public void setStatus(boolean on,int statusColor){
//        if (Build.VERSION.SDK_INT >=Build.VERSION_CODES.LOLLIPOP){
//            if (statusColor!=-1){
//                getWindow().setStatusBarColor(statusColor);
//            }
//            return;
//        }
//
//    }
}
