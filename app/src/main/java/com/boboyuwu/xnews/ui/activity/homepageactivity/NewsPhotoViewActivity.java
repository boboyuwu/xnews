package com.boboyuwu.xnews.ui.activity.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.boboyuwu.common.basefragentpageadapter.BaseViewPagerAdapter;
import com.boboyuwu.common.widget.photoview.PhotoView;
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean.AdsBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.activity.baseactivity.LoadingAndRetryActivity;
import com.bumptech.glide.Glide;
import com.example.boboyuwu.zhihunews.R;


/**
 * Created by wubo on 2017/9/26.
 */


public class NewsPhotoViewActivity extends LoadingAndRetryActivity<HomePageNewsPresenter> {

    private ViewPager mViewPager;
    private HeadLineNewsBean mHeadLineNewsBean;
    private TextView mPhotoTv;

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
        initView();

    }

    private void initView() {
        if(mHeadLineNewsBean!=null&&mHeadLineNewsBean.getAds()!=null&&mHeadLineNewsBean.getAds().size()>0){
            String title=!TextUtils.isEmpty(mHeadLineNewsBean.getAds().get(0).getTitle())?
                    mHeadLineNewsBean.getAds().get(0).getTitle():"";
            mPhotoTv.setText("1/"+mHeadLineNewsBean.getAds().size()+"  "+title);
        }
        initViewPager();
    }

    @Override
    protected void setToolBar() {
        super.setToolBar();
        setToolBarTitle("图片新闻");
        enableBackPress();
    }

    public static void startNewsPhotoViewActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, NewsPhotoViewActivity.class);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        context.startActivity(intent);
    }

    private void getExtras() {
        Bundle bundle = getIntent().getExtras();
        mHeadLineNewsBean = bundle.getParcelable(Keys.NEWS_PHOTO_ITEM);
    }

    private void initViewPager() {
        BaseViewPagerAdapter<AdsBean> adapter = new BaseViewPagerAdapter<AdsBean>(this, mHeadLineNewsBean.getAds()) {
            @Override
            protected View getItemView(ViewGroup container, int position, AdsBean adsBean) {
                PhotoView photoView = new PhotoView(container.getContext());
                Glide.with(NewsPhotoViewActivity.this)
                        .load(adsBean.getImgsrc())
                        .into(photoView);
                return photoView;
            }
        };
        mViewPager.setAdapter(adapter);
    }

    @Override
    protected void setListener() {
        super.setListener();
        mViewPager.addOnPageChangeListener(new OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                mPhotoTv.setText((position+1)+"/"+mHeadLineNewsBean.getAds().size()+"  "+mHeadLineNewsBean.getAds().get(position).getTitle());
            }
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    private void findViews() {
        mViewPager = getView(R.id.viewpager);
        mPhotoTv = getView(R.id.photo_tv);
    }
}
