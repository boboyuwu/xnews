package com.boboyuwu.xnews.ui.activity.baseactivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.BasePresenter;
import com.example.boboyuwu.zhihunews.R;

import java.util.HashMap;

/**
 * Created by wubo on 2017/9/24.
 * 提供只基本加载Url地址的webview样式的基类
 */

public class WebViewActivity<P extends BasePresenter> extends LoadingAndRetryActivity<P> {

    protected WebView mWebview;
    private String mUrl;
    private ProgressBar mProgressBar;
    protected HashMap<String,String> mHttpHeaders=new HashMap<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_webview;
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        getExtras();
        initWebView();
        enableBackPress();
    }



    private void initWebView() {
        WebSettings webSettings = mWebview.getSettings();
        webSettings.setJavaScriptEnabled(true);

        //设置字体
        mWebview.getSettings().setDefaultFontSize(42);

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
        setJavascriptObject(mWebview);

        mWebview.setWebChromeClient(new WebChromeClient() {

            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                super.onProgressChanged(view, newProgress);
                mProgressBar.setProgress(newProgress);
            }

            @Override
            public void onReceivedTitle(WebView view, String title) {
                super.onReceivedTitle(view, title);
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

        if(!TextUtils.isEmpty(mUrl)){
            mWebview.loadUrl(mUrl,mHttpHeaders);
        }
    }


    /**
     * 提供设置javascript对象等一些设置
     * */
    protected void setJavascriptObject(WebView webview) {


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
        if(mWebview.canGoBack()){
            mWebview.goBack();
            return;
        }
        finish();
    }

    @Override
    protected void initInject() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
      /*  ((ViewGroup)mWebview.getParent()).removeView(mWebview);
        mWebview.destroy();
        mWebview=null;
        System.exit(0);*/
    }


}
