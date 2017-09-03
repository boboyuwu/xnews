package com.boboyuwu.xnews.ui.fragment.womanphotofragment;

import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;
import com.example.boboyuwu.zhihunews.R;

/**
 * Created by wubo on 2017/8/28.
 */

public class WomanPhotoFragment extends SupportToolBarFragment<HomePageNewsPresenter>{
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
}
