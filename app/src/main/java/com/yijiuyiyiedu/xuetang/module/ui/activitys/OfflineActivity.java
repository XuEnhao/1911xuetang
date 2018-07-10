package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/6/19.
 * 线下活动
 */

public class OfflineActivity extends BaseActivity {
    @BindView(R.id.friend_statusBar)
    View statusBar;
    @BindView(R.id.customCourse_webView)
    WebView webView;
    private Context mContext;
    @BindView(R.id.hr_title)
    TextView hrTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_course);
        ButterKnife.bind(this);
        mContext = this;
        hrTitle.setText("线下活动");
        initWebView(Constant.UNDER_LINE_ACTIVITY);
    }

    private void statusBar() {
        StatusBarUtil.setTranslucentForImageView((Activity) mContext,null);
        int statusBarHeight = StatusBarUtil.getStatusBarHeight(mContext);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = statusBarHeight;
    }
    @SuppressLint("NewApi")
    private void initWebView(String content) {


        if (Build.VERSION.SDK_INT >= 19) {
            webView.getSettings().setCacheMode(
                    WebSettings.LOAD_CACHE_ELSE_NETWORK);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.TEXT_AUTOSIZING);
        } else {
            webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NORMAL);
        }
        webView.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
//                activity.setTitle("Loading...");
//                activity.setProgress(newProgress * 100);
//                if (newProgress == 100) {
//                    activity.setTitle(R.string.app_name);
//                }
            }

            @Override
            public boolean onJsAlert(WebView view, String url, String message,
                                     JsResult result) {
                return super.onJsAlert(view, url, message, result);
            }
        });
        webView.setWebViewClient(new GameWebViewClient());
        WebSettings s = webView.getSettings();
        s.setJavaScriptEnabled(true);
        //设置 缓存模式
        s.setCacheMode(WebSettings.LOAD_DEFAULT);
// 开启 DOM storage API 功能
        s.setDomStorageEnabled(true);
//        webView.loadUrl("https://www.baidu.com/");
        webView.loadUrl(content);
//        webView.loadDataWithBaseURL(null, s1 + content + s2, "text/html", "utf-8", null);
    }
    @OnClick(R.id.hr_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }

    class GameWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,
                                                String url_Turntable) {
            if (url_Turntable.contains("app:")){

                JumpUtil.jump(mContext,CallBusinessActivity.class);
            }
            return true;
        }

        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler,
                                       SslError error) {
            handler.proceed();
        }

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
        }

        @Override
        public void onPageFinished(WebView view, String url) {
            super.onPageFinished(view, url);
        }

    }

}
