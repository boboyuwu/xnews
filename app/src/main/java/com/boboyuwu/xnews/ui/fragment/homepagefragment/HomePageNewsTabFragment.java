package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.mvp.view.HomePageView;
import com.boboyuwu.xnews.ui.fragment.basefragment.LazyFragment;
import com.example.boboyuwu.zhihunews.R;

import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 */


public class HomePageNewsTabFragment extends LazyFragment<HomePageNewsPresenter>  implements HomePageView{

    private static final String TAG = HomePageNewsTabFragment.class.getSimpleName();
    private RecyclerView mRecyclerview;
    private String mChannelId;
    private String mChannelType;

    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_homepage_news_list;
    }

    @Override
    protected void init() {
        super.init();
        initView();
    }

    private void initView() {
        mRecyclerview = getView(R.id.recyclerview);
        Bundle arguments = getArguments();
        mChannelId = arguments.getString(Keys.CHANNEL_ID);
        mChannelType = arguments.getString(Keys.CHANNEL_TYPE);

    }

    @Override
    protected void onLazyLoadData() {
        mLoadingAndRetryManager.showContent();
        mPresenter.getHomePageNewsList(mChannelType,mChannelId,"0");
    }

    @Override
    public void onLoadNewsList(List<HeadLineNewsBean> list) {
        Log.e("www","www");
    }
}

