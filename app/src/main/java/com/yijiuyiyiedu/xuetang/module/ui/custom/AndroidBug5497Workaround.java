package com.yijiuyiyiedu.xuetang.module.ui.custom;

import android.app.Activity;
import android.graphics.Rect;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

import com.yijiuyiyiedu.xuetang.utils.LogUtil;

/**
 * Created by seek on 2017/11/29.
 * 全屏状态下 软键盘重叠布局
 */

public class AndroidBug5497Workaround {
    // For more information, see https://issuetracker.google.com/issues/36911528
    // To use this class, simply invoke assistActivity() on an Activity that already has its content view set.

    public static AndroidBug5497Workaround assistActivity(Activity activity, boolean isFullScreen) {
        return new AndroidBug5497Workaround(activity, isFullScreen);
    }

    private View mChildOfContent;
    private int usableHeightPrevious;
    private FrameLayout.LayoutParams frameLayoutParams;
    private boolean isFullScreen = false;
    private FrameLayout content;

    private AndroidBug5497Workaround(Activity activity, boolean isFullScreen) {
        this.isFullScreen = isFullScreen;
        content = (FrameLayout) activity.findViewById(android.R.id.content);
        mChildOfContent = content.getChildAt(0);

        mChildOfContent.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            public void onGlobalLayout() {
                possiblyResizeChildOfContent();
            }
        });
        frameLayoutParams = (FrameLayout.LayoutParams) mChildOfContent.getLayoutParams();
    }

    private void possiblyResizeChildOfContent() {
        int usableHeightNow = computeUsableHeight();
        if (usableHeightNow != usableHeightPrevious &&  mChildOfContent.getParent() != null) {
            int usableHeightSansKeyboard = ((ViewGroup) mChildOfContent.getParent()).getHeight();
            int heightDifference = usableHeightSansKeyboard - usableHeightNow;
            if (heightDifference > (usableHeightSansKeyboard / 4)) {
                // keyboard probably just became visible
                frameLayoutParams.height = usableHeightSansKeyboard - heightDifference;
            } else {
                // keyboard probably just became hidden
                frameLayoutParams.height = usableHeightSansKeyboard;
            }
            LogUtil.LogD("height",usableHeightNow+"");
            mChildOfContent.requestLayout();
            usableHeightPrevious = usableHeightNow;
        }
    }

    private int computeUsableHeight() {
        Rect r = new Rect();
        mChildOfContent.getWindowVisibleDisplayFrame(r);
        return isFullScreen ? r.bottom : r.bottom - r.top;
    }
}
