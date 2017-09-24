package com.boboyuwu.xnews.ui.activity.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.activity.baseactivity.WebViewActivity;

/**
 * Created by wubo on 2017/9/21.
 */

public class NewsDetailActivity extends WebViewActivity<HomePageNewsPresenter> {
    @Override
    protected void initInject() {
        getActivityComponent().injectActivity(this);
    }


    @Override
    protected void init() {
        super.init();
    }



    public static void startNewsDetailActivity(Context context, Bundle bundle){
        Intent intent = new Intent(context, NewsDetailActivity.class);
        if(bundle!=null){
            Intent intent1 = intent.putExtra(Keys.BUNDLE, intent);
        }
        context.startActivity(intent);
    }


}
