package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

import com.boboyuwu.common.basequickadapter.BaseAdapterHelper;
import com.boboyuwu.common.basequickadapter.MultiItemTypeSupport;
import com.boboyuwu.common.basequickadapter.QuickAdapter;
import com.boboyuwu.common.loadmorerecyclerview.EndlessRecyclerOnScrollListener;
import com.boboyuwu.common.loadmorerecyclerview.HeaderAndFooterRecyclerViewAdapter;
import com.boboyuwu.common.loadmorerecyclerview.LoadingFooter.StateEnum;
import com.boboyuwu.common.loadmorerecyclerview.LoadingFooter.StateInfo;
import com.boboyuwu.common.loadmorerecyclerview.NetworkUtils;
import com.boboyuwu.common.loadmorerecyclerview.RecyclerViewStateUtils;
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean.ImgextraBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.greendao.data.ChannelNewsData;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.mvp.view.HomePageView;
import com.boboyuwu.xnews.ui.activity.homepageactivity.NewsDetailActivity;
import com.boboyuwu.xnews.ui.activity.homepageactivity.NewsPhotoViewActivity;
import com.boboyuwu.xnews.ui.fragment.basefragment.LazyFragment;
import com.bumptech.glide.Glide;
import com.example.boboyuwu.zhihunews.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 */


public class HomePageNewsTabFragment extends LazyFragment<HomePageNewsPresenter> implements HomePageView, OnRefreshListener {

    private static final String TAG = HomePageNewsTabFragment.class.getSimpleName();
    private RecyclerView mRecyclerview;

    private final int TYPE_NEWS=1;
    private final int TYPE_PhotoNews=2;


    private int mLoadDataPageCurr = 0;
    /**
     * 提供一个已经lazy加载的标识防止需要Lazy的Fragment重复调用方法
     * if(!mHasLazyLoad && getUserVisibleHint()){
     * mLoadingAndRetryManager.showContent();
     * mPresenter.getHomePageMoreNewsList(mChannelNewsData.getChannelType(),mChannelNewsData.getChannelId(),"0");
     * }
     */
    private boolean mHasLazyLoad;
    private ChannelNewsData mChannelNewsData;
    private QuickAdapter mQuickAdapter;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private HeaderAndFooterRecyclerViewAdapter mHeaderAndFooterRecyclerViewAdapter;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private final int REQUEST_PAGESIZE=20;
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
        //第一个Fragment是没有执行onLazyLoadData,所以需要单独加载列表
        //其他的Fragemnt由于onLazyLoadData在init之前执行所以这里根据onLazyLoadData中标记判断一下加载第一个Fragment
        //后面mHasLazyLoad可以换成请求的时间戳等
        loadHomePageNewsFromCache();
        if (!mHasLazyLoad && getUserVisibleHint()) {
            showLoading();
            mPresenter.getHomePageNewsList(mChannelNewsData.getChannelType(), mChannelNewsData.getChannelId(), String.valueOf(mLoadDataPageCurr));
        }
    }


    //从缓存中先读取
    private void loadHomePageNewsFromCache() {
        mPresenter.getHomePageNewsListFromCache(mChannelNewsData.getChannelId());
    }

    @Override
    protected void setListener() {
        mSwipeRefreshLayout.setOnRefreshListener(this);
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
                mLoadDataPageCurr += REQUEST_PAGESIZE;
                loadMore();
            }
        }
    };

    private void loadMore() {
        if (NetworkUtils.isNetAvailable(mActivity.get())) {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.Loading, ""), null);
            mPresenter.getHomePageMoreNewsList(mChannelNewsData.getChannelType(), mChannelNewsData.getChannelId(), String.valueOf(mLoadDataPageCurr));
        } else {
            RecyclerViewStateUtils.setFooterViewState(mActivity.get(), mRecyclerview, REQUEST_PAGESIZE, new StateInfo(StateEnum.NetWorkError, ""), mOnClickListener);
        }
    }


    private void findViews() {
        mSwipeRefreshLayout = getView(R.id.swipeRefreshLayout);
        mRecyclerview = getView(R.id.recyclerview);
    }

    private void initView() {
        //初始化recyclerview
        mQuickAdapter = new QuickAdapter<HeadLineNewsBean>(mActivity.get(), mMultiItemTypeSupport) {
            @Override
            protected void convert(BaseAdapterHelper helper, HeadLineNewsBean item) {
                switch (helper.getItemViewType()){
                    case TYPE_NEWS+Integer.MAX_VALUE/2:
                        processNews(helper, item);
                        break;
                    case TYPE_PhotoNews+Integer.MAX_VALUE/2:
                        processPhotoNews(helper, item);
                        break;
                }
            }
        };
        mHeaderAndFooterRecyclerViewAdapter = new HeaderAndFooterRecyclerViewAdapter(mQuickAdapter);
        mRecyclerview.setLayoutManager(new LinearLayoutManager(mActivity.get()));
        mRecyclerview.setAdapter(mHeaderAndFooterRecyclerViewAdapter);
        mRecyclerview.addItemDecoration(new DividerItemDecoration(mActivity.get(), DividerItemDecoration.VERTICAL));
        mSwipeRefreshLayout.setColorSchemeResources(
                android.R.color.holo_blue_light,
                android.R.color.holo_red_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_green_light);

    }


    private MultiItemTypeSupport<HeadLineNewsBean> mMultiItemTypeSupport=new MultiItemTypeSupport<HeadLineNewsBean>() {
        @Override
        public int getLayoutId(int viewType) {
            int layoutId=0;
            switch (viewType){
                case TYPE_NEWS:
                    layoutId=R.layout.item_homepage_list_news;
                    break;
                case TYPE_PhotoNews:
                    layoutId=R.layout.item_homepage_list_photo_news;
                    break;
            }

            return layoutId;
        }

        @Override
        public int getItemViewType(int position, HeadLineNewsBean headLineNewsBean) {
            if (!TextUtils.isEmpty(headLineNewsBean.getDigest())){
                return TYPE_NEWS;
            }else{
                return TYPE_PhotoNews;
            }
        }
    };

    private void processPhotoNews(BaseAdapterHelper helper, final HeadLineNewsBean item) {
        helper.getTextView(R.id.photo_title_tv).setText(item.getTitle());
        helper.getTextView(R.id.photo_time_tv).setText(item.getPtime());
        helper.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable(Keys.NEWS_PHOTO_ITEM,item);
                NewsPhotoViewActivity.startNewsPhotoViewActivity(mActivity.get(),bundle);
            }
        });
        /**
         * 处理一个或多个图片
         * */
        LinearLayout linearLayout = helper.getView(R.id.photo_ll);
        if (linearLayout.getChildCount() > 0) {
            linearLayout.removeAllViews();
        }

        if (item.getImgextra() == null || item.getImgextra().size()==0) {
            if(!TextUtils.isEmpty(item.getImgsrc())){
                ArrayList<ImgextraBean> imgextras = new ArrayList();
                ImgextraBean imgextraBean = new ImgextraBean();
                imgextraBean.setImgsrc(item.getImgsrc());
                imgextras.add(imgextraBean);
                item.setImgextra(imgextras);
            }
        }
        //当getImgsrc为null时这个可能会null
        if (item.getImgextra() != null) {
            for (int i = 0; i < item.getImgextra().size(); i++) {
                ImageView photoImageView = getPhotoImageView(item.getImgextra().size()==1);
                linearLayout.addView(photoImageView);
                if(!TextUtils.isEmpty(item.getImgextra().get(i).getImgsrc()))
                Glide.with(this).load(item.getImgextra().get(i).getImgsrc())
                        .placeholder(new ColorDrawable(getResources().getColor(R.color.alpha_15_gray)))
                        .into(photoImageView);
            }
        }
    }

    private ImageView getPhotoImageView(boolean onlyOneImg) {
        ImageView imageView = new ImageView(mActivity.get());
        if(onlyOneImg)
            imageView.setScaleType(ScaleType.CENTER_CROP);
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT);
        layoutParams.leftMargin = 10;
        layoutParams.rightMargin = 10;
        layoutParams.weight = 1;
        imageView.setLayoutParams(layoutParams);
        return imageView;
    }


    private void processNews(BaseAdapterHelper helper, final HeadLineNewsBean item) {
        helper.getTextView(R.id.news_title_tv).setText(item.getLtitle());
        helper.getTextView(R.id.news_digest_tv).setText(item.getDigest());
        helper.getTextView(R.id.news_time_tv).setText(item.getPtime());
        helper.itemView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString(Keys.NEWS_ID,item.getPostid());
                NewsDetailActivity.startNewsDetailActivity(mActivity.get(),bundle);
            }
        });
        Glide.with(this).load(item.getImgsrc())
                .placeholder(new ColorDrawable(getResources().getColor(R.color.alpha_15_gray)))
                .into(helper.getImageView(R.id.news_iv));
    }

    private void getChannel() {
        Bundle arguments = getArguments();
        mChannelNewsData = (ChannelNewsData) arguments.getSerializable(Keys.CHANNEL);
    }


    @Override
    protected void onLazyLoadData() {
        if(!mHasLazyLoad){
            showLoading();
            mPresenter.getHomePageNewsList(mChannelNewsData.getChannelType(), mChannelNewsData.getChannelId(), String.valueOf(mLoadDataPageCurr));
        }
    }


    @Override
    public void onLoadNewsList(List<HeadLineNewsBean> list) {
        //排序时间
        if(mSwipeRefreshLayout.isRefreshing()){
            mSwipeRefreshLayout.setRefreshing(false);
        }
        if(list.size()>0){
            sortData(list);
            mPresenter.setHomePageNewsListToCache(mChannelNewsData.getChannelId(),list,mLoadDataPageCurr);
            mQuickAdapter.replaceAll(list);
            showContent();
            mHasLazyLoad = true;
        }else{
            showEmpty();
        }
    }

    private void sortData(List<HeadLineNewsBean> list) {
        Collections.sort(list, new Comparator<HeadLineNewsBean>() {
            @Override
            public int compare(HeadLineNewsBean headLineNewsBean, HeadLineNewsBean t1) {
                Date date1 = null;
                Date date2 = null;
                try {
                    date1 = mSimpleDateFormat.parse(headLineNewsBean.getPtime());
                    date2 = mSimpleDateFormat.parse(t1.getPtime());
                } catch (ParseException e) {

                }
                return Long.valueOf(date2.getTime()).compareTo(date1.getTime());
            }
        });
    }

    @Override
    public void onLoadMoreNewsList(List<HeadLineNewsBean> list) {
        if(list.size()<REQUEST_PAGESIZE){
            RecyclerViewStateUtils.setFooterViewState(mRecyclerview, new StateInfo(StateEnum.TheEnd, null));
        }else{
            RecyclerViewStateUtils.setFooterViewState(mRecyclerview, new StateInfo(StateEnum.Normal, null));
        }
        //排序时间  mQuickAdapter.getData().size(), list.size()
        mQuickAdapter.getData().addAll(list);
        sortData( mQuickAdapter.getData());
        mQuickAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadNewsListFromCache(List<HeadLineNewsBean> list) {
        mQuickAdapter.replaceAll(list);
    }

    @Override
    public void onRefresh() {
        mLoadDataPageCurr = 0;
        mPresenter.getHomePageNewsList(mChannelNewsData.getChannelType(), mChannelNewsData.getChannelId(), String.valueOf(mLoadDataPageCurr));
        //mSwipeRefreshLayout.setRefreshing(false);
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
    protected void onRetryClick(View view) {
        super.onRetryClick(view);
        showLoading();
        mPresenter.getHomePageNewsList(mChannelNewsData.getChannelType(), mChannelNewsData.getChannelId(), String.valueOf(mLoadDataPageCurr));
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
}

