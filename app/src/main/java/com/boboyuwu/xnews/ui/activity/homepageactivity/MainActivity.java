package com.boboyuwu.xnews.ui.activity.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.activity.baseactivity.RxManageActivity;
import com.boboyuwu.xnews.ui.fragment.homepagefragment.HomePageNewsFragment;
import com.boboyuwu.xnews.ui.fragment.minefragment.MineFragment;
import com.boboyuwu.xnews.ui.fragment.prettyphotofragment.PrettyPhotoFragment;
import com.boboyuwu.xnews.ui.fragment.videofragment.VideoFragment;
import com.example.boboyuwu.zhihunews.R;

public class MainActivity extends RxManageActivity<HomePageNewsPresenter> implements OnTabChangeListener {
    private FragmentTabHost mFragmentTabHost;
    private String[] mTabs = {"首页", "美女", "视频", "我"};
    private int[] mUnSelectedImages = {R.mipmap.ic_home_normal, R.mipmap.ic_girl_normal, R.mipmap.ic_video_normal, R.mipmap.ic_care_normal};
    private int[] mSelectedImages = {R.mipmap.ic_home_selected, R.mipmap.ic_girl_selected, R.mipmap.ic_video_selected, R.mipmap.ic_care_selected};
    private Class[] mFragments = {HomePageNewsFragment.class, PrettyPhotoFragment.class, VideoFragment.class, MineFragment.class};


    private SparseArray<View> mBottomTabSparse = new SparseArray<>();

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initFragmentTabHost();
        initBottomTab();
    }

    private void initBottomTab() {
        setBottomTabSelected(0);
    }

    private void findViews() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    }

    public static void startMainActivity(Context context, Bundle bundle) {
        Intent intent = new Intent(context, MainActivity.class);
        if (bundle != null)
        intent.putExtra(Keys.BUNDLE, bundle);
        context.startActivity(intent);
    }

    private void initFragmentTabHost() {
        mFragmentTabHost.setup(MainActivity.this, getSupportFragmentManager(), android.R.id.tabcontent);
        for (int i = 0; i < mTabs.length; i++) {
            View tabView = getTabView(i);
            TabHost.TabSpec tabSpec = mFragmentTabHost.newTabSpec(mTabs[i]).setIndicator(tabView);
            mFragmentTabHost.addTab(tabSpec, mFragments[i], null);
            mBottomTabSparse.put(i, tabView);
        }
    }

    private View getTabView(int i) {
        View inflate = LayoutInflater.from(this).inflate( R.layout.item_bottom_tab_host, null,false);
        TextView text_tv =  inflate.findViewById(R.id.text_tv);
        ImageView icon_iv = inflate.findViewById(R.id.icon_iv);
        text_tv.setText(mTabs[i]);
        icon_iv.setImageResource(mUnSelectedImages[i]);
        return inflate;
    }

    @Override
    protected void setListener() {
        mFragmentTabHost.setOnTabChangedListener(this);

    }

    @Override
    protected void initInject() {
        getActivityComponent().injectActivity(this);
    }

    //newTabSpec 中设置的标签
    @Override
    public void onTabChanged(String tabId) {
        switch (tabId) {
            case "首页":
                setBottomTabSelected(0);
                break;
            case "美女":
                setBottomTabSelected(1);
                break;
            case "视频":
                setBottomTabSelected(2);
                break;
            case "我":
                setBottomTabSelected(3);
                break;
        }
    }

    /**
     * 设置底部选中以及未选中设置图片文字状态
     */
    private void setBottomTabSelected(int indexSelected) {
        for (int i = 0; i < mBottomTabSparse.size(); i++) {
            View tabView = mBottomTabSparse.get(i);
            TextView text_tv = tabView.findViewById(R.id.text_tv);
            ImageView icon_iv =tabView.findViewById(R.id.icon_iv);
            if (i == indexSelected) {
                text_tv.setTextColor(getResources().getColor(R.color.spark_orange));
                icon_iv.setImageResource(mSelectedImages[indexSelected]);
            } else {
                text_tv.setTextColor(getResources().getColor(R.color.gray));
                icon_iv.setImageResource(mUnSelectedImages[i]);
            }
        }
    }



}
