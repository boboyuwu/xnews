package com.boboyuwu.xnews.ui.fragment.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.boboyuwu.xnews.ui.fragment.homepagefragment.HomePageNewsTabFragment;

/**
 * Created by wubo on 2017/7/10.
 */


public class HomePageNewsFragmentFactoryIml implements FragmentFactory {

    @Override
    public Fragment createFragment(String tab, Bundle bundle) {
        HomePageNewsTabFragment zhiHuDailyFragment = new HomePageNewsTabFragment();
        if(bundle!=null){
            zhiHuDailyFragment.setArguments(bundle);
        }
        return zhiHuDailyFragment;
    }

    @Override
    public Fragment createFragment(int tab, Bundle bundle) {
        return null;
    }
}
