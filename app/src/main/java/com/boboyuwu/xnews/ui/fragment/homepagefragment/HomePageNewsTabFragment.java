package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.boboyuwu.common.basequickadapter.BaseAdapterHelper;
import com.boboyuwu.common.basequickadapter.QuickAdapter;
import com.boboyuwu.common.loadmorerecyclerview.EndlessRecyclerOnScrollListener;
import com.boboyuwu.common.loadmorerecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.boboyuwu.common.loadmorerecyclerview.NetworkUtils;
import com.boboyuwu.common.loadmorerecyclerview.RecyclerViewStateUtils;
import com.boboyuwu.common.widget.LoadingFooter.StateEnum;
import com.boboyuwu.common.widget.LoadingFooter.StateInfo;
import com.boboyuwu.xnews.beans.ChannelNewsBean;
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.mvp.view.HomePageView;
import com.boboyuwu.xnews.ui.fragment.basefragment.LazyFragment;
import com.bumptech.glide.Glide;
import com.example.boboyuwu.zhihunews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 */


public class HomePageNewsTabFragment extends LazyFragment<HomePageNewsPresenter>  implements HomePageView, OnRefreshListener {

    private static final String TAG = HomePageNewsTabFragment.class.getSimpleName();
    private RecyclerView mRecyclerview;


    private int loadDataPageCurr=0;
    /**
        提供一个已经lazy加载的标识防止需要Lazy的Fragment重复调用方法
        if(!mHasLazyLoad && getUserVisibleHint()){
        mLoadingAndRetryManager.showContent();
        mPresenter.getHomePageMoreNewsList(mChannelNewsBean.getChannelType(),mChannelNewsBean.getChannelId(),"0");
       }
     */
    private boolean mHasLazyLoad;
    private ChannelNewsBean mChannelNewsBean;
    private QuickAdapter mQuickAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
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
        findViews();
        initView();
        setListener();
        //第一个Fragment是没有执行onLazyLoadData,所以需要单独加载列表
        //其他的Fragemnt由于onLazyLoadData在init之前执行所以这里根据onLazyLoadData中标记判断一下加载第一个Fragment
        if(!mHasLazyLoad && getUserVisibleHint()){
           // mLoadingAndRetryManager.showContent();
            mPresenter.getHomePageNewsList(mChannelNewsBean.getChannelType(),mChannelNewsBean.getChannelId(),String.valueOf(loadDataPageCurr));
        }

    }

    private void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mRecyclerview.addOnScrollListener(mEndlessRecyclerOnScrollListener);
    }


    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener=new EndlessRecyclerOnScrollListener(){
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            StateEnum state = RecyclerViewStateUtils.getFooterViewState(mRecyclerview);
            if (state == StateEnum.Loading) {
                return;
            }
            if(state==StateEnum.NetWorkError) {
                //loadMoreError();
                return ;
            }
            if (state == StateEnum.TheEnd) {
                return;
            } else {
                loadDataPageCurr+=20;
                loadMore();
            }
        }
    };

    private void loadMore() {
        if(NetworkUtils.isNetAvailable(mActivity.get())){
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, 10, new StateInfo(StateEnum.Loading,"正在加载中......"), null);
            mPresenter.getHomePageMoreNewsList(mChannelNewsBean.getChannelType(),mChannelNewsBean.getChannelId(),String.valueOf(loadDataPageCurr));
        }else{
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, 10, new StateInfo(StateEnum.NetWorkError,"网络异常,请点击重试!"), mOnClickListener);
        }
    }



    private void findViews() {
        mSwipeRefreshLayout = getView(R.id.swipeRefreshLayout);
        mRecyclerview = getView(R.id.recyclerview);
    }

    private void initView() {
        //初始化recyclerview
        mQuickAdapter = new QuickAdapter<HeadLineNewsBean>(mActivity.get(), R.layout.item_homepage_news_list) {

            @Override
            protected void convert(BaseAdapterHelper helper, HeadLineNewsBean item) {
                procssNormalItem(helper,item);
            }
        };
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mQuickAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity.get()));
        mRecyclerview.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(mActivity.get(),DividerItemDecoration.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

    }

    private void procssNormalItem(BaseAdapterHelper helper, HeadLineNewsBean item) {
        helper.getTextView(R.id.title_tv).setText(item.getLtitle());
        helper.getTextView(R.id.digest_tv).setText(item.getDigest());
        helper.getTextView(R.id.time_tv).setText(item.getPtime());
        Glide.with(this).load(item.getImgsrc())
                .into(helper.getImageView(R.id.img_iv));

    }

    private void getChannel() {
        Bundle arguments = getArguments();
        mChannelNewsBean = (ChannelNewsBean) arguments.getSerializable(Keys.CHANNEL);
    }


    @Override
    protected void onLazyLoadData() {
        mHasLazyLoad = true;
       // mLoadingAndRetryManager.showContent();
        mPresenter.getHomePageNewsList(mChannelNewsBean.getChannelType(),mChannelNewsBean.getChannelId(),String.valueOf(loadDataPageCurr));
    }



    @Override
    public void onLoadNewsList(List<HeadLineNewsBean> list) {
        //排序时间
        RecyclerViewStateUtils.setFooterViewState( mRecyclerview,new StateInfo(StateEnum.Normal,null));
        sortData(list);
        mQuickAdapter.replaceAll(list);
    }

    private void  sortData(List<HeadLineNewsBean> list) {
        Collections.sort(list, new Comparator<HeadLineNewsBean>() {
            @Override
            public int compare(HeadLineNewsBean headLineNewsBean, HeadLineNewsBean t1) {
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 =mSimpleDateFormat.parse(headLineNewsBean.getPtime());
                    date2 = mSimpleDateFormat.parse(t1.getPtime());
                } catch (ParseException e) {

                }
                return Long.valueOf(date2.getTime()).compareTo(date1.getTime());
            }
        });
    }

    @Override
    public void onLoadMoreNewsList(List<HeadLineNewsBean> list) {
        RecyclerViewStateUtils.setFooterViewState( mRecyclerview,new StateInfo(StateEnum.Normal,null));
        mQuickAdapter.getData().addAll(list);
        mQuickAdapter.notifyItemRangeInserted(mQuickAdapter.getData().size(),list.size());
    }

    @Override
    public void onRefresh() {
        loadDataPageCurr=0;
        mPresenter.getHomePageNewsList(mChannelNewsBean.getChannelType(),mChannelNewsBean.getChannelId(),String.valueOf(loadDataPageCurr));
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private OnClickListener mOnClickListener=new OnClickListener() {
        @Override
        public void onClick(View view) {
            loadMore();
        }
    };

    @Override
    public void onLoadMoreError(RxSubscriberState msg) {
        super.onLoadMoreError(msg);
        if(msg.getErrorType()==RxSubscriberState.NETWORK_ERROR){
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, 10, new StateInfo(StateEnum.NetWorkError,"网络异常,请点击重试!"), mOnClickListener);
        }else{
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, 10, new StateInfo(StateEnum.NetWorkError,"请求失败,请点击重试!"), mOnClickListener);
        }
    }
}

