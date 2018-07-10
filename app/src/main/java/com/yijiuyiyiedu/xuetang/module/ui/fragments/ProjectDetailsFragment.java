package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.net.http.SslError;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JsResult;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.persenter.CourseDetailsPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.ProjectDetailsActivity;
import com.yijiuyiyiedu.xuetang.utils.encryption.Base64Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/4/9.
 * 项目详情
 */

public class ProjectDetailsFragment extends BaseFragment {
    @BindView(R.id.projectDetails_webView)
    WebView webView;
    Unbinder unbinder;
    boolean isLoad = false;

    public static ProjectDetailsFragment getInstance(String projectid){
        ProjectDetailsFragment fragment = new ProjectDetailsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("projectId",projectid);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_project_details;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad) {
            isLoad = true;
            initWebView("http://edu.1911thu.com/Wechat/Project/appHf?id="+getArguments().getString("projectId"));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
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
//        String str1 = "PCFET0NUWVBFIGh0bWw+IDxodG1sIGxhbmc9ImVuIj4gPGhlYWQ+IDxtZXRhIGNoYXJzZXQ9IlVURi04Ij4gPG1ldGEgbmFtZT0idmlld3BvcnQiIGNvbnRlbnQ9IndpZHRoPWRldmljZS13aWR0aCwgaW5pdGlhbC1zY2FsZT0xLjAsIG1heGltdW0tc2NhbGU9MS4wLCB1c2VyLXNjYWxhYmxlPTAiPiA8c3R5bGU+IGltZ3t3aWR0aDoxMDAlO2hlaWdodDphdXRvO2Rpc3BsYXk6IGJsb2NrO30gPC9zdHlsZT4gPC9oZWFkPiA8Ym9keSBzdHlsZT0iYmFja2dyb3VuZC1jb2xvcjojZmZmZmZmIj4=";
//        String str2 = "PC9ib2R5PiA8c2NyaXB0IHR5cGU9J3RleHQvamF2YXNjcmlwdCc+IGZ1bmN0aW9uIGNsaWNrSW1nNElvcygpeyB2YXIgb2JqcyA9IGRvY3VtZW50LmdldEVsZW1lbnRzQnlUYWdOYW1lKCdpbWcnKTsgZm9yKHZhciBpPTA7aTxvYmpzLmxlbmd0aDtpKyspeyBvYmpzW2ldLm9uY2xpY2s9ZnVuY3Rpb24oKXsgd2luZG93LmxvY2F0aW9uLmhyZWY9J2lPUz09SlNUb2lPUz09Jyt0aGlzLmdldEF0dHJpYnV0ZSgnc2hvd2luZGV4Jyk7fTt9fSA8L3NjcmlwdD4gPC9odG1sPg==";
//
//        String s1 = Base64Utils.getUidFromBase(str1);
//        String s2 = Base64Utils.getUidFromBase(str2);
//        Log.d("tag", "initWebView: " + s1 + content + s2);
        webView.loadUrl(content);
//        webView.loadDataWithBaseURL(null, s1 + content + s2, "text/html", "utf-8", null);
    }

    class GameWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view,
                                                String url_Turntable) {
            view.loadUrl(url_Turntable);
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
