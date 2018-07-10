package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.os.Bundle;
import android.webkit.WebChromeClient;
import android.webkit.WebView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/3/28.
 */

public class PayWebViewActivity extends BaseActivity {
    @BindView(R.id.pay_webView)
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay_webview);
        ButterKnife.bind(this);
        initWebView();
    }



    private void initWebView() {
        webView.setVerticalScrollbarOverlay(true);
        //设置WebView支持JavaScript
        webView.getSettings().setJavaScriptEnabled(true);

        String url = "http://edu.1911thu.com/Pay/AppPay/AppPay?id=" + getIntent().getIntExtra("orderId", 0);
        webView.loadUrl(url);

        //在js中调用本地java方法
//        webView.addJavascriptInterface(new JsInterface(this), "AndroidWebView");

        //添加客户端支持
        webView.setWebChromeClient(new WebChromeClient());
    }
}
