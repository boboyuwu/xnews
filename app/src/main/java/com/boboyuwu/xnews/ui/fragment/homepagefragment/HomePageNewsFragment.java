package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.boboyuwu.common.basefragentpageadapter.BaseTabLayoutFragmentAdapter;
import com.boboyuwu.common.util.RxBus;
import com.boboyuwu.common.util.RxBusEventKeys;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.ChannelNewsBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.common.utils.ChannelTypeUtil;
import com.boboyuwu.xnews.mvp.model.helper.GreenDaoHelper;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.activity.homepageactivity.AddChannelActivity;
import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;
import com.boboyuwu.xnews.ui.fragment.helper.HomePageNewsTabFragmentIml;
import com.example.boboyuwu.zhihunews.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;


/**
 * Created by wubo on 2017/8/28.
 */

public class HomePageNewsFragment extends SupportToolBarFragment<HomePageNewsPresenter> implements OnClickListener {

    private TabLayout mTabLayout;
    private ImageView mAddIv;

    private ViewPager mViewpager;
    private List<ChannelNewsBean> mChannelList;
    private List<Fragment> mFragments;
    private Observable<Boolean> mUpdateChannelObservable;
    private LinearLayout mTablayoutLinearLayout;

    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_homepage_news;
    }

    @Override
    protected void setToolBar() {
        setToolBarTitle("XNews新闻");
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initNewsChannelTab();
        initFragment();
        initView();
        initObservable();
        setListener();
    }

    private void initObservable() {
        mUpdateChannelObservable = RxBus.get().register(RxBusEventKeys.UPDATE_CHANNEL, Boolean.class);
        mUpdateChannelObservable.subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Exception {
                if(aBoolean){
                    initNewsChannelTab();
                    initFragment();
                    initView();
                }
            }
        });
    }

    private void setListener() {
        mAddIv.setOnClickListener(this);
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        HomePageNewsTabFragmentIml homePageNewsTabFragmentIml = new HomePageNewsTabFragmentIml();
        for (ChannelNewsBean channelNewsBean : mChannelList) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Keys.CHANNEL, channelNewsBean);
            mFragments.add(homePageNewsTabFragmentIml.createFragment(channelNewsBean.getChannelId(), bundle));
        }
    }

    private void initView() {
        setViewPager();
        setTabLayout();
    }

    private void setViewPager() {
        mViewpager.setAdapter(new BaseTabLayoutFragmentAdapter(getChildFragmentManager(), mFragments) {
            @Override
            public CharSequence getTitle(int position) {
                return mChannelList.get(position).getChannelName();
            }
        });
        mViewpager.setOffscreenPageLimit(1);
        //mViewpager.setPageTransformer();
    }

    private void setTabLayout() {
        mTabLayout.setTabTextColors(getResources().getColor(R.color.alpha_50_white), getResources().getColor(R.color.white));
        mTabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.white));
        mTabLayout.setupWithViewPager(mViewpager);
        mTablayoutLinearLayout.setBackgroundColor(getResources().getColor(R.color.spark_orange));
    }

    /**
     * 初始化News频道标签
     */
    private void initNewsChannelTab() {
        GreenDaoHelper greenDaoHelper = NewsApplication.getAppComponent().getGreenDaoHelper();
        List<ChannelNewsBean> channels = greenDaoHelper.getChannelList();
        mChannelList = new ArrayList<>();
        if (channels == null || (channels != null && channels.size() <= 0)) {
            List<String> channelName = Arrays.asList(getResources().getStringArray(R.array.news_channel_name_static));
            List<String> channelId = Arrays.asList(getResources().getStringArray(R.array.news_channel_id_static));
            for (int i = 0; i < channelName.size(); i++) {
                ChannelNewsBean channelNewsBean = new ChannelNewsBean();
                channelNewsBean.setChannelName(channelName.get(i));
                channelNewsBean.setChannelId(channelId.get(i));
                channelNewsBean.setChannelType(ChannelTypeUtil.getChannelType(channelId.get(i)));
                channelNewsBean.setIsFixChannel(true);
                channelNewsBean.setChannelManagerType(ChannelNewsBean.CHANNEL_TYPE_MINE);
                mChannelList.add(channelNewsBean);
            }
        } else {
            mChannelList.addAll(channels);
        }
        greenDaoHelper.setChannelList(mChannelList);
    }

    private void findViews() {
        mTabLayout = getView(R.id.tablayout);
        mAddIv = getView(R.id.add_iv);
        mViewpager = getView(R.id.viewpager);
        mTablayoutLinearLayout = getView(R.id.tablayout_ll);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.add_iv:
                AddChannelActivity.startAddChannelActivity(mActivity.get(), null);
                break;
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        RxBus.get().unregister(RxBusEventKeys.UPDATE_CHANNEL,mUpdateChannelObservable);
    }
}
