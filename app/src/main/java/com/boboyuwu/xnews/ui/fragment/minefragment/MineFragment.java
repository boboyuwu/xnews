package com.boboyuwu.xnews.ui.fragment.minefragment;

import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;

/**
 * Created by wubo on 2017/8/28.
 */

public class MineFragment extends SupportToolBarFragment{
    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return 0;
    }

    @Override
    protected void setToolBar() {

    }
}
