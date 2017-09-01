package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.widget.ImageView;

import com.boboyuwu.common.basefragentpageadapter.BaseTabLayoutFragmentAdapter;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.ChannelNewsBean;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.model.helper.GreenDaoHelper;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;
import com.boboyuwu.xnews.ui.fragment.helper.HomePageNewsTabFragmentIml;
import com.example.boboyuwu.zhihunews.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 */

public class HomePageNewsFragment extends SupportToolBarFragment<HomePageNewsPresenter> {

    private TabLayout mTabLayout;
    private ImageView mAddIv;

    private ViewPager mViewpager;
    private ArrayList<ChannelNewsBean> mChannelList;
    private ArrayList<Fragment> mFragments;

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
        setToolBarColor(getResources().getColor(R.color.spark_orange));
        setToolBarTitle("XNews新闻");
        setToolBarColor(getResources().getColor(R.color.spark_orange));
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initNewsChannelTab();
        initFragment();
        initView();
    }

    private void initFragment() {
        mFragments = new ArrayList<>();
        HomePageNewsTabFragmentIml homePageNewsTabFragmentIml = new HomePageNewsTabFragmentIml();
        for (ChannelNewsBean channelNewsBean : mChannelList) {
            Bundle bundle = new Bundle();
            bundle.putSerializable(Keys.CHANNEL,  channelNewsBean);
            //bundle.putString(Keys.CHANNEL_TYPE, channelNewsBean.getChannelType());
            mFragments.add(homePageNewsTabFragmentIml.createFragment(channelNewsBean.getChannelId(), bundle));
        }
    }

    private void initView() {
        setViewPager();
        setTabLayout();
    }

    private void setViewPager() {
        mViewpager.setAdapter(new BaseTabLayoutFragmentAdapter(getChildFragmentManager(),mFragments) {
            @Override
            public CharSequence getTitle(int position) {
                return mChannelList.get(position).getChannelName();
            }
        });
    }

    private void setTabLayout() {
        mTabLayout.setupWithViewPager(mViewpager);
    }

    /**
     * 初始化News频道标签
     */
    private void initNewsChannelTab() {
        GreenDaoHelper greenDaoHelper = NewsApplication.getAppComponent().getGreenDaoHelper();
        //greenDaoHelper.clearAllChannel();
        List<ChannelNewsBean> channels = greenDaoHelper.getChannel();
        mChannelList = new ArrayList<>();
        if (channels == null || (channels!=null && channels.size()<=0)) {
            List<String> channelName = Arrays.asList(getResources().getStringArray(R.array.news_channel_name_static));
            List<String> channelId = Arrays.asList(getResources().getStringArray(R.array.news_channel_id_static));
            for (int i = 0; i < channelName.size(); i++) {
                ChannelNewsBean channelNewsBean = new ChannelNewsBean();
                channelNewsBean.setChannelName(channelName.get(i));
                channelNewsBean.setChannelId(channelId.get(i));
                if (TextUtils.equals(channelId.get(i), "T1348647909107")) {
                    channelNewsBean.setChannelType(ChannelNewsBean.HEADLINE);
                } else if (TextUtils.equals(channelId.get(i), "5YyX5Lqs")) {
                    channelNewsBean.setChannelType(ChannelNewsBean.HOUSE);
                } else {
                    channelNewsBean.setChannelType(ChannelNewsBean.OTHER);
                }
                channelNewsBean.setIsFixChannel(true);
                mChannelList.add(channelNewsBean);
            }
        } else {
            mChannelList.addAll(channels);
            //清空之前所有存储的频道
           // greenDaoHelper.clearAllChannel();
        }
       // greenDaoHelper.setChannel(mChannelList);
    }

    private void findViews() {
        mTabLayout = getView(R.id.tablayout);
        mAddIv = getView(R.id.add_iv);
        mViewpager = getView(R.id.viewpager);
    }
}
