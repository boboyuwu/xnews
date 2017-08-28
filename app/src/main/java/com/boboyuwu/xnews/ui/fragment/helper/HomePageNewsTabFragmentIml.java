package com.boboyuwu.xnews.ui.fragment.helper;

import android.support.v4.app.Fragment;

import com.boboyuwu.xnews.ui.fragment.homepagefragment.HomePageNewsTabFragment;


/**
 * Created by wubo on 2017/7/10.
 */

public class HomePageNewsTabFragmentIml implements FragmentFactory{

        @Override
        public Fragment createFragment(String tab) {
            HomePageNewsTabFragment zhiHuDailyFragment = new HomePageNewsTabFragment();
            return zhiHuDailyFragment;
        }

        @Override
        public Fragment createFragment(int tab) {
            return null;
        }
}
