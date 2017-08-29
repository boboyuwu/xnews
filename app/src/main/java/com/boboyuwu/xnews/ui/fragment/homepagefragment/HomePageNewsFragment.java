package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;
import com.example.boboyuwu.zhihunews.R;

/**
 * Created by wubo on 2017/8/28.
 */

public class HomePageNewsFragment extends SupportToolBarFragment{
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

    }

    @Override
    protected void init() {
        super.init();

    }
}
