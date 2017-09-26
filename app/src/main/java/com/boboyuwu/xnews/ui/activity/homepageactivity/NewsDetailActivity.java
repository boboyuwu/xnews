package com.boboyuwu.xnews.ui.activity.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.boboyuwu.xnews.beans.NewsDetail;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.NewsDetailPresenter;
import com.boboyuwu.xnews.mvp.view.NewsDetailView;
import com.boboyuwu.xnews.ui.activity.baseactivity.LoadingAndRetryActivity;
import com.example.boboyuwu.zhihunews.R;
import com.orhanobut.logger.Logger;

import okhttp3.ResponseBody;

/**
 * Created by wubo on 2017/9/21.
 */

public class NewsDetailActivity extends LoadingAndRetryActivity<NewsDetailPresenter> implements NewsDetailView {

    private String mNewsId;
    private WebView mWebview;
    private TextView mPtimeTv;
    private TextView mTitleTv;
    private ProgressBar mProgressBar;

    public static void startNewsDetailActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NewsDetailActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_news_detail;
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initWebView();
        getExtras();
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

        mWebview.setBackgroundColor(getResources().getColor(R.color.webview_bg_color));
    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        mNewsId = bundle.getString(Keys.NEWS_ID);
        mPresenter.getNewsDetail(mNewsId);
    }

    private void findViews() {
        mWebview = getView(R.id.webview);
        mTitleTv = getView(R.id.title_tv);
        mPtimeTv = getView(R.id.ptime_tv);
        mProgressBar = getView(R.id.progressBar);
    }

    @Override
    protected void initInject() {
        getActivityComponent().injectActivity(this);
    }


    @Override
    public void onLoadNewsDetail(NewsDetail newsDetail) {
        String webViewColor = mDayNightHelper.getMode() ? "#303030" : "FFFFFFFF";
        String webViewTextColor = mDayNightHelper.getMode() ? "#aaa" : "#FF000000";
        String htmlBody =
                "<html>\n" +
                        "<head><meta charset=\"utf-8\"><title></title><style>body" +
                        "{background-color:" + webViewColor + ";}" +
                        "p{color:" + webViewTextColor + ";font-family:\"Times New Roman\";font-size:42px;}" +
                        "</style></head><body>" + newsDetail.getBody()
                        + "</body></html>";
        mTitleTv.setText(newsDetail.getTitle());
        mPtimeTv.setText(newsDetail.getSource() + "  " + newsDetail.getPtime());
        mWebview.loadDataWithBaseURL(null, htmlBody, "text/html", "utf-8", null);
        Logger.i("newsDetail:" + newsDetail.toString());
    }

    @Override
    public void onLoadNewsBodyHtmlPhoto(ResponseBody photoPath) {

    }


}
