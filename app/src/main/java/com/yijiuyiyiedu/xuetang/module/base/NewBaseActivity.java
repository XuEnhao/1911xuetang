package com.yijiuyiyiedu.xuetang.module.base;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import butterknife.Unbinder;
import cn.bingoogolapple.swipebacklayout.BGASwipeBackHelper;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Description: BaseActivity
 */
public abstract class NewBaseActivity<T extends BasePresenter> extends AppCompatActivity implements BGASwipeBackHelper.Delegate {

    protected Unbinder unbinder;
    protected T mPresenter;
    protected BGASwipeBackHelper mSwipeBackHelper;
    protected Toolbar mToolbar;

    private final String TAG = "NewBaseActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        initSwipeBackFinish();
        super.onCreate(savedInstanceState);
        LogUtil.LogE(TAG, "------>onCreate");
        init();
    }

    protected void init() {
//        setTranslucentStatus(true);
        // 主题变化
//        onPreCreate();
        BaseApplication.getInstance().registerActivity(this);
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


    @Override
    protected void onStart() {
        super.onStart();
        LogUtil.LogE(TAG, "------>onStart");
        // 改变 状态栏
//        setTitleHeight(getRootView(this));
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
    protected void onDestroy() {
        super.onDestroy();
        LogUtil.LogE(TAG, "------>onDestroy");
        BaseApplication.getInstance().unregisterActivity(this);
        if (unbinder != null)
            unbinder.unbind();
        mPresenter = null;
    }
    @Override
    public void onBackPressed() {
        // 正在滑动返回的时候取消返回按钮事件
        if (mSwipeBackHelper.isSliding()) {
            return;
        }
        mSwipeBackHelper.backward();
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

//    private void setTitleHeight(View view) {
//        if (view != null) {
//            ColorRelativeLayout title = (ColorRelativeLayout) view.findViewById(R.id.title);
//            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.KITKAT) {
//                if (title != null) {
//                    ViewGroup.LayoutParams lp = title.getLayoutParams();
//                    lp.height = ScreenUtil.dip2px(this, 48);
//                    title.setLayoutParams(lp);
//                    title.setPadding(0, 0, 0, 0);
//                }
//            }
//        }
//    }


    protected static View getRootView(Activity context) {
        return ((ViewGroup) context.findViewById(android.R.id.content)).getChildAt(0);
    }
}
