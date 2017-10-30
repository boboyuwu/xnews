package com.boboyuwu.xnews.ui.fragment.prettyphotofragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
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
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean.AdsBean;
import com.boboyuwu.xnews.beans.PrettyPhotos.ResultsBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.mvp.presenter.PrettyPhotoPresenter;
import com.boboyuwu.xnews.mvp.view.PrettyPhotoView;
import com.boboyuwu.xnews.ui.activity.homepageactivity.NewsPhotoViewActivity;
import com.boboyuwu.xnews.ui.fragment.basefragment.LoadingAndRetryFragment;
import com.bumptech.glide.Glide;
import com.example.boboyuwu.zhihunews.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 * 美女首页
 */

public class PrettyPhotoFragment extends LoadingAndRetryFragment<PrettyPhotoPresenter> implements PrettyPhotoView, OnRefreshListener {

    private RecyclerView mRecyclerview;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;

    private int mPageIndex = 1;
    private QuickAdapter<ResultsBean> mQuickAdapter;
    private final int REQUEST_PAGESIZE = 20;
    private SwipeRefreshLayout mSwipeRefreshLayout;

    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_woman;
    }

    @Override
    protected void setToolBar() {
        setToolBarTitle("XNews美女");
    }

    @Override
    protected void init() {
        super.init();
        initView();
        showLoading();
        mPresenter.getPrettyPhotoNewsList(String.valueOf(mPageIndex));
    }

    private void initView() {
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        initRecyclerview();
    }

    @Override
    protected void preInit() {
        super.preInit();
        findViews();
    }

    @Override
    protected View initManagerView() {
        return mRecyclerview;
    }

    private void initRecyclerview() {
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        mQuickAdapter = new QuickAdapter<ResultsBean>(mActivity.get(), R.layout.item_pretty_photo) {
            @Override
            protected void convert(BaseAdapterHelper helper, final ResultsBean item) {
                Glide.with(mActivity.get())
                        .load(item.getUrl())
                        .placeholder(new ColorDrawable(getResources().getColor(R.color.alpha_15_gray)))
                        .into(helper.getImageView(R.id.iv_photo));
                helper.itemView.setOnClickListener(new OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        HeadLineNewsBean headLineNewsBean = new HeadLineNewsBean();
                        ArrayList<AdsBean> list = new ArrayList<>();
                        AdsBean adsBean = new AdsBean();
                        adsBean.setImgsrc(item.getUrl());
                        list.add(adsBean);
                        headLineNewsBean.setAds(list);
                        Bundle bundle = new Bundle();
                        bundle.putParcelable(Keys.NEWS_PHOTO_ITEM, headLineNewsBean);
                        NewsPhotoViewActivity.startNewsPhotoViewActivity(mActivity.get(), bundle);
                    }
                });
            }
        };
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mQuickAdapter);
        mRecyclerview.setLayoutManager(staggeredGridLayoutManager);
        mRecyclerview.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerview.addOnScrollListener(mEndlessRecyclerOnScrollListener);
    }

    private void findViews() {
        mRecyclerview = getView(R.id.recyclerview);
        mSwipeRefreshLayout = getView(R.id.swipeRefreshLayout);
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


    private void loadMore() {
        if (NetworkUtils.isNetAvailable(mActivity.get())) {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.Loading, ""), null);
            mPresenter.getPrettyPhotoMoreNewsList(String.valueOf(mPageIndex));
        } else {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.NetWorkError, ""), mOnClickListener);
        }
    }





    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View view) {
            loadMore();
        }
    };

    @Override
    public void onError(RxSubscriberState msg) {
        super.onError(msg);
        showRetry();
    }

    @Override
    public void onLoadMoreError(RxSubscriberState msg) {
        super.onLoadMoreError(msg);
        if (msg.getErrorType() == RxSubscriberState.NETWORK_ERROR) {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.NetWorkError, getResources().getString(R.string.network_unconnect)), mOnClickListener);
        } else {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.NetWorkError, getResources().getString(R.string.network_service_error)), mOnClickListener);
        }
    }

    @Override
    protected void onRetryClick(View view) {
        super.onRetryClick(view);
        showLoading();
        mPresenter.getPrettyPhotoNewsList(String.valueOf(mPageIndex));
    }

    @Override
    public void onLoadPrettyPhotoList(List<ResultsBean> results) {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
        mQuickAdapter.replaceAll(results);
        showContent();
    }

    @Override
    public void onLoadMorePrettyPhotoList(List<ResultsBean> results) {
        if (results.size() < REQUEST_PAGESIZE) {
            RecyclerViewStateUtils.setFooterViewState(mRecyclerview, new StateInfo(StateEnum.TheEnd, null));
        } else {
            RecyclerViewStateUtils.setFooterViewState(mRecyclerview, new StateInfo(StateEnum.Normal, null));
        }
        mQuickAdapter.addAll(results);
    }

    @Override
    public void onRefresh() {
        mPageIndex = 1;
        mPresenter.getPrettyPhotoNewsList(String.valueOf(mPageIndex));
    }
}
