package com.yijiuyiyiedu.xuetang.module.ui.custom;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.IHeaderView;
import com.lcodecore.tkrefreshlayout.OnAnimEndListener;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

/**
 * Created by xuenhao on 2018/6/25.
 * 下拉刷新view
 */

public class LoadView extends FrameLayout implements IHeaderView {
    private ImageView loadingView;
    private ImageView refreshView;
    public LoadView(@NonNull Context context) {
        this(context,null);
    }

    public LoadView(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public LoadView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView();
    }

    /**
     * 初始化布局
     */
    private void initView() {
        View rootView = View.inflate(getContext(), R.layout.activity_loading, null);
        loadingView = (ImageView) rootView.findViewById(R.id.loading_img);//占位图
        refreshView = (ImageView) rootView.findViewById(R.id.loading_anim);//动画
        addView(rootView);
    }

    @Override
    public View getView() {
        return this;
    }

    @Override
    public void onPullingDown(float fraction, float maxHeadHeight, float headHeight) {
        loadingView.setVisibility(VISIBLE);
        refreshView.setVisibility(GONE);
        LogUtil.LogD("LoadView", "onPullingDown: loadingView.setVisibility(VISIBLE)  refreshView.setVisibility(GONE)");
    }

    @Override
    public void onPullReleasing(float fraction, float maxHeadHeight, float headHeight) {
        if (loadingView.getVisibility() == GONE) {
            loadingView.setVisibility(VISIBLE);
            refreshView.setVisibility(GONE);
        }

        LogUtil.LogD("LoadView", "onPullReleasing: loadingView.setVisibility(VISIBLE)  refreshView.setVisibility(GONE)");

    }

    @Override
    public void startAnim(float maxHeadHeight, float headHeight) {
        refreshView.setVisibility(VISIBLE);
        loadingView.setVisibility(GONE);
        ((AnimationDrawable) refreshView.getDrawable()).start();
        LogUtil.LogD("LoadView", "startAnim: refreshView.setVisibility(VISIBLE)  loadingView.setVisibility(GONE)");

    }

    @Override
    public void onFinish(OnAnimEndListener listener) {
        listener.onAnimEnd();
    }

    @Override
    public void reset() {
        loadingView.setVisibility(VISIBLE);
        refreshView.setVisibility(GONE);
        LogUtil.LogD("LoadView", "reset: loadingView.setVisibility(VISIBLE)  refreshView.setVisibility(GONE)");

    }
}
