package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.boboyuwu.common.basequickadapter.BaseAdapterHelper;
import com.boboyuwu.common.basequickadapter.QuickAdapter;
import com.boboyuwu.xnews.beans.ChannelNewsBean;
import com.boboyuwu.xnews.beans.HeadLineNews.T1348647909107Bean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.mvp.view.HomePageView;
import com.boboyuwu.xnews.ui.fragment.basefragment.LazyFragment;
import com.example.boboyuwu.zhihunews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 */


public class HomePageNewsTabFragment extends LazyFragment<HomePageNewsPresenter>  implements HomePageView{

    private static final String TAG = HomePageNewsTabFragment.class.getSimpleName();
    private RecyclerView mRecyclerview;

    /**
        提供一个已经lazy加载的标识防止需要Lazy的Fragment重复调用方法
        if(!mHasLazyLoad && getUserVisibleHint()){
        mLoadingAndRetryManager.showContent();
        mPresenter.getHomePageNewsList(mChannelNewsBean.getChannelType(),mChannelNewsBean.getChannelId(),"0");
       }
     */
    private boolean mHasLazyLoad;
    private ChannelNewsBean mChannelNewsBean;
    private QuickAdapter mQuickAdapter;

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
        getChannel();
        //第一个Fragment是没有执行onLazyLoadData,所以需要单独加载列表
        //其他的Fragemnt由于onLazyLoadData在init之前执行所以这里根据onLazyLoadData中标记判断一下加载第一个Fragment
        if(!mHasLazyLoad && getUserVisibleHint()){
           // mLoadingAndRetryManager.showContent();
            mPresenter.getHomePageNewsList(mChannelNewsBean.getChannelType(),mChannelNewsBean.getChannelId(),"0");
        }
        initView();
    }

    private void initView() {
        mRecyclerview = getView(R.id.recyclerview);
        mQuickAdapter = new QuickAdapter(mActivity.get(), R.layout.item_homepage_news_list) {
            @Override
            protected void convert(BaseAdapterHelper helper, Object item) {

            }
        };
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity.get()));
        mRecyclerview.setAdapter(mQuickAdapter);
    }

    private void getChannel() {
        Bundle arguments = getArguments();
        /*mChannelId = arguments.getString(Keys.CHANNEL_ID);
        mChannelType = arguments.getString(Keys.CHANNEL_TYPE);*/
        mChannelNewsBean = (ChannelNewsBean) arguments.getSerializable(Keys.CHANNEL);
    }

    @Override
    protected void onLazyLoadData() {
        mHasLazyLoad = true;
       // mLoadingAndRetryManager.showContent();
        mPresenter.getHomePageNewsList(mChannelNewsBean.getChannelType(),mChannelNewsBean.getChannelId(),"0");
    }

    @Override
    public void onLoadNewsList(List<T1348647909107Bean> list) {
                //模拟一下
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < 100; i++) {
            arrayList.add(i+"");
        }
        mQuickAdapter.replaceAll(arrayList);
    }
}

