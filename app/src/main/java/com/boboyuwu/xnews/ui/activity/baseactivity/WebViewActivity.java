package com.boboyuwu.xnews.ui.activity.baseactivity;

import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.BasePresenter;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.example.boboyuwu.zhihunews.R;

import java.util.HashMap;

/**
 * Created by wubo on 2017/9/24.
 * 提供基本webview样式的基类
 */

public class WebViewActivity<P extends BasePresenter> extends SupportToolBarActivity<HomePageNewsPresenter> {

    private WebView mWebview;
    private String mUrl;
    private ProgressBar mProgressBar;
    private HashMap<String,String> mHttpHeaders=new HashMap<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void init() {
        super.init();
        findViews();
        getExtras();
        initWebView();
    }



    private void initWebView() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件


        webSettings.setDomStorageEnabled(true); // 开启 DOM storage API 功能
        webSettings.setDatabaseEnabled(true);   //开启 database storage API 功能

        webSettings.setAllowFileAccess(true); //设置可以访问文件

        setWebViewSettings(webSettings);
        setHttpHeaders(mHttpHeaders);

        mWebview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }

        });

        mWebview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                mProgressBar.setVisibility(View.VISIBLE);
                return super.shouldOverrideUrlLoading(view, request);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                mProgressBar.setVisibility(View.GONE);
            }
        });

        mWebview.loadUrl(mUrl,mHttpHeaders);
    }



    /**
     * 设置请求头
     * */
    protected void setHttpHeaders(HashMap httpHeaders){

    }


    /**
     * 设置额外功能
     */
    protected void setWebViewSettings(WebSettings settings){

    }


    protected void getExtras() {
        Bundle bundle = getIntent().getExtras();
        mUrl = bundle.getString(Keys.WEB_URL);
    }

    private void findViews() {
        mWebview = getView(R.id.webview);
        mProgressBar = getView(R.id.progressBar);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(mWebview.canGoBack()){
            mWebview.goBack();
            return;
        }
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mWebview.destroy();
        System.exit(0);
    }
}
