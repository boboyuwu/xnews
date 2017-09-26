package com.boboyuwu.xnews.ui.activity.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.activity.baseactivity.LoadingAndRetryActivity;
import com.example.boboyuwu.zhihunews.R;

/**
 *  Created by wubo on 2017/9/26.
 */


public class NewsPhotoViewActivity extends LoadingAndRetryActivity<HomePageNewsPresenter>{

    private ViewPager mViewPager;

    @Override
    protected int getLayout() {
        return R.layout.activity_news_photo_view;
    }

    @Override
    protected void initInject() {
        getActivityComponent().injectActivity(this);
    }

    @Override
    protected void init() {
        super.init();
        getExtras();
        findViews();
        initViewPager();
    }

    public static void startNewsPhotoViewActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NewsPhotoViewActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    private void getExtras() {

    }

    private void initViewPager() {
        //mViewPager.setAdapter();
    }

    private void findViews() {
        mViewPager = getView(R.id.viewpager);
    }
}
