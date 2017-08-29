package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import com.boboyuwu.xnews.mvp.presenter.HomePageNewsTabPresenter;
import com.boboyuwu.xnews.ui.fragment.basefragment.LazyFragment;

/**
 * Created by wubo on 2017/8/28.
 */


public class HomePageNewsTabFragment extends LazyFragment<HomePageNewsTabPresenter> {
    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void onLazyLoadData() {

    }
}

