package com.boboyuwu.xnews.ui.fragment.videofragment;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.View.OnClickListener;

import com.boboyuwu.common.basequickadapter.BaseAdapterHelper;
import com.boboyuwu.common.basequickadapter.QuickAdapter;
import com.boboyuwu.common.loadmorerecyclerview.EndlessRecyclerOnScrollListener;
import com.boboyuwu.common.loadmorerecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.boboyuwu.common.loadmorerecyclerview.LoadingFooter.StateEnum;
import com.boboyuwu.common.loadmorerecyclerview.LoadingFooter.StateInfo;
import com.boboyuwu.common.loadmorerecyclerview.NetworkUtils;
import com.boboyuwu.common.loadmorerecyclerview.RecyclerViewStateUtils;
import com.boboyuwu.xnews.beans.ChannelVideoBean;
import com.boboyuwu.xnews.beans.VideoNews.VideoNewsBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.mvp.presenter.VideoPresenter;
import com.boboyuwu.xnews.mvp.view.VideoView;
import com.boboyuwu.xnews.ui.fragment.basefragment.LazyFragment;
import com.example.boboyuwu.zhihunews.R;

import java.util.List;

/**
 * Created by wubo on 2017/9/29.
 */

public class VideoNewsTabFragment  extends LazyFragment<VideoPresenter> implements OnRefreshListener ,VideoView{


    private RecyclerView mRecyclerview;
    private ChannelVideoBean mChannelVideoBean;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private boolean mHasLazyLoad;
    private int mPageIndex = 1;
    private final int REQUEST_PAGESIZE = 20;
    private QuickAdapter<VideoNewsBean> mQuickAdapter;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;

    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video_news_list;
    }

    @Override
    protected void init() {
        super.init();
        getChannel();
        findViews();
        initView();
        if (!mHasLazyLoad && getUserVisibleHint()) {
            showLoading();
            mPresenter.getVideoNewsList(mChannelVideoBean.getChannelId(),String.valueOf(mPageIndex));
        }
    }

    private void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mActivity.get());
        mQuickAdapter = new QuickAdapter<VideoNewsBean>(mActivity.get(), R.layout.item_pretty_photo) {
            @Override
            protected void convert(BaseAdapterHelper helper, final VideoNewsBean item) {

            }
        };
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mQuickAdapter);
        mRecyclerview.setLayoutManager(linearLayoutManager);
        mRecyclerview.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerview.addOnScrollListener(mEndlessRecyclerOnScrollListener);
    }


    private EndlessRecyclerOnScrollListener mEndlessRecyclerOnScrollListener = new EndlessRecyclerOnScrollListener() {
        @Override
        public void onLoadNextPage(View view) {
            super.onLoadNextPage(view);
            StateEnum state = RecyclerViewStateUtils.getFooterViewState(mRecyclerview);
            if (state == StateEnum.Loading) {
                return;
            }
            if (state == StateEnum.NetWorkError) {
                return;
            }
            if (state == StateEnum.TheEnd) {
                return;
            } else {
                mPageIndex++;
                loadMore();
            }
        }
    };



    private void getChannel() {
        Bundle arguments = getArguments();
        mChannelVideoBean = arguments.getParcelable(Keys.CHANNEL);
    }

    private void findViews() {
        mRecyclerview = getView(R.id.recyclerview);
        mSwipeRefreshLayout = getView(R.id.swipeRefreshLayout);
    }

    @Override
    protected void onLazyLoadData() {
        mHasLazyLoad = true;
        showLoading();
        mPresenter.getVideoNewsList(mChannelVideoBean.getChannelId(),String.valueOf(mPageIndex));
    }


    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            loadMore();
        }
    };

    private void loadMore() {
        if (NetworkUtils.isNetAvailable(mActivity.get())) {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.Loading, ""), null);
            mPresenter.getVideoNewsMoreList(mChannelVideoBean.getChannelId(),String.valueOf(mPageIndex));
        } else {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.NetWorkError, ""), mOnClickListener);
        }
    }


    @Override
    public void onLoadMoreError(RxSubscriberState msg) {
        super.onLoadMoreError(msg);
        if (msg.getErrorType() == RxSubscriberState.NETWORK_ERROR) {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.NetWorkError, ""), mOnClickListener);
        } else {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.NetWorkError, ""), mOnClickListener);
        }
    }

    @Override
    public void onRefresh() {
        mPageIndex = 1;
        mPresenter.getVideoNewsList(mChannelVideoBean.getChannelId(),String.valueOf(mPageIndex));
    }

    @Override
    public void onLoadVideoNewsList(List<VideoNewsBean> videoNewses) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mQuickAdapter.replaceAll(videoNewses);
        showContent();
    }

    @Override
    public void onError(RxSubscriberState msg) {
        super.onError(msg);
        showRetry();
    }


    @Override
    protected void onRetryClick(View view) {
        super.onRetryClick(view);
        showLoading();
        mPresenter.getVideoNewsList(mChannelVideoBean.getChannelId(),String.valueOf(mPageIndex));
    }

    @Override
    public void onLoadVideoNewsMoreList(List<VideoNewsBean> videoNewses) {
        if (videoNewses.size() < REQUEST_PAGESIZE) {
            RecyclerViewStateUtils.setFooterViewState(mRecyclerview, new StateInfo(StateEnum.TheEnd, null));
        } else {
            RecyclerViewStateUtils.setFooterViewState(mRecyclerview, new StateInfo(StateEnum.Normal, null));
        }
        mQuickAdapter.addAll(videoNewses);
    }
}
