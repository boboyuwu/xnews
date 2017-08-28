package com.boboyuwu.xnews.ui.activity.homepageactivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTabHost;
import android.view.View;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.boboyuwu.common.util.Constants;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.mvp.presenter.MainActivityPresenter;
import com.boboyuwu.xnews.ui.activity.baseactivity.RxManageActivity;
import com.boboyuwu.xnews.ui.fragment.homepagefragment.HomePageNewsFragment;
import com.boboyuwu.xnews.ui.fragment.minefragment.MineFragment;
import com.boboyuwu.xnews.ui.fragment.videofragment.VideoFragment;
import com.boboyuwu.xnews.ui.fragment.womanphotofragment.WomanPhotoFragment;
import com.example.boboyuwu.zhihunews.R;

public class MainActivity  extends RxManageActivity<MainActivityPresenter> implements OnTabChangeListener {
    private FragmentTabHost mFragmentTabHost;
    private String[] mTabs={"首页","美女","视频","我"};
    private int [] images={R.mipmap.ic_drawer_zhihu,R.mipmap.ic_drawer_wechat,R.mipmap.ic_drawer_wechat,R.mipmap.ic_drawer_zhihu,R.mipmap.ic_drawer_wechat,R.mipmap.ic_drawer_wechat};
    private Class[] mFragments={HomePageNewsFragment.class,WomanPhotoFragment.class, VideoFragment.class, MineFragment.class};
    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initFragmentTabHost();
    }

    private void findViews() {
        mFragmentTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
    }

    public static void startMainActivity(Context context,Bundle bundle){
        Intent intent = new Intent(context, MainActivity.class);
        if(bundle!=null)
        intent.putExtra(Keys.BUNDLE,bundle);
        context.startActivity(intent);
    }

    private void initFragmentTabHost() {
        for (int i = 0; i < mTabs.length; i++) {
            mFragmentTabHost.setup(MainActivity.this,getSupportFragmentManager(), android.R.id.tabcontent);
            TextView icon = new TextView(this);
            icon.setText(mTabs[i]);
            TabHost.TabSpec tabSpec= mFragmentTabHost.newTabSpec(mTabs[i]).setIndicator(getTabView(i));
            Bundle bundle = new Bundle();
            bundle.putString(Constants.TITLE,mTabs[i]);
            mFragmentTabHost.addTab(tabSpec,mFragments[i],bundle);
        }
    }

    private View getTabView(int i) {
        View inflate = View.inflate(this, R.layout.item_bottom_tab_host, null);
        TextView tv = (TextView) inflate.findViewById(R.id.text_tv);
        ImageView iv = (ImageView) inflate.findViewById(R.id.icon_iv);
        tv.setText(mTabs[i]);
        iv.setImageResource(images[i]);
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

    @Override
    public void onTabChanged(String tabId) {

    }
}
