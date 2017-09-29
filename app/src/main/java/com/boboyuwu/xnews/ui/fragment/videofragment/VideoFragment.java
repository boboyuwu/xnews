package com.boboyuwu.xnews.ui.fragment.videofragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.boboyuwu.common.basefragentpageadapter.BaseTabLayoutFragmentAdapter;
import com.boboyuwu.xnews.beans.ChannelVideoBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;
import com.boboyuwu.xnews.ui.fragment.helper.FragmentFactory;
import com.boboyuwu.xnews.ui.fragment.helper.VideoNewsFragmentFactoryIml;
import com.example.boboyuwu.zhihunews.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 * 视频模块
 */

public class VideoFragment extends SupportToolBarFragment<HomePageNewsPresenter>{

    private TabLayout mTablayout;
    private ViewPager mViewpager;
    private List<ChannelVideoBean> mVideoList;
    private ArrayList<Fragment> mFragments;

    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video;
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initVideoChannelTab();
        initFragment();
        initView();
    }

    private void initView() {
        setViewPager();
        setTabLayout();
    }

    private void setTabLayout() {
        mTablayout.setTabTextColors(getResources().getColor(R.color.tablayout_text_color_unselect), getResources().getColor(R.color.tablayout_text_color_select));
        mTablayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.tablayout_indicator_bg_color));
        mTablayout.setupWithViewPager(mViewpager);
        mTablayout.setBackgroundColor(getResources().getColor(R.color.toolbar_bg_color));
    }

    private void setViewPager() {
        mViewpager.setAdapter(new BaseTabLayoutFragmentAdapter(getChildFragmentManager(), mFragments) {
            @Override
            public CharSequence getTitle(int position) {
                return mVideoList.get(position).getChannelName();
            }
        });
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        FragmentFactory videoNewsFragmentFactoryIml = new VideoNewsFragmentFactoryIml();
        for (ChannelVideoBean channelVideoBean : mVideoList) {
            Bundle bundle = new Bundle();
            bundle.putParcelable(Keys.CHANNEL, channelVideoBean);
            mFragments.add(videoNewsFragmentFactoryIml.createFragment(channelVideoBean.getChannelId(), bundle));
        }
    }

    private void initVideoChannelTab() {
        List<String> channelName = Arrays.asList(getResources().getStringArray(R.array.video_channel_name));
        List<String> channelId = Arrays.asList(getResources().getStringArray(R.array.video_channel_id));
        mVideoList = new ArrayList<>();
        for (int i = 0; i < channelName.size(); i++) {
            ChannelVideoBean channelVideoBean = new ChannelVideoBean();
            channelVideoBean.setChannelId(channelId.get(i));
            channelVideoBean.setChannelName(channelName.get(i));
            mVideoList.add(channelVideoBean);
        }
    }

    private void findViews() {
        mTablayout = getView(R.id.tablayout);
        mViewpager = getView(R.id.viewpager);
    }
}
