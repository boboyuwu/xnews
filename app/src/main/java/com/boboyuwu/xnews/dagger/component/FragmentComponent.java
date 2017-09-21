package com.boboyuwu.xnews.dagger.component;


import com.boboyuwu.xnews.ui.fragment.homepagefragment.HomePageNewsFragment;
import com.boboyuwu.xnews.ui.fragment.homepagefragment.HomePageNewsTabFragment;
import com.boboyuwu.xnews.ui.fragment.minefragment.MineFragment;
import com.boboyuwu.xnews.ui.fragment.videofragment.VideoFragment;
import com.boboyuwu.xnews.ui.fragment.prettyphotofragment.PrettyPhotoFragment;

import dagger.Component;

/**
 * Created by wubo on 2017/6/15.
 */
@Component
public interface FragmentComponent {

    void injectFragment(HomePageNewsFragment homePageNewsFragment);
    void injectFragment(HomePageNewsTabFragment homePageNewsTabFragment);
    void injectFragment(PrettyPhotoFragment prettyPhotoFragment);
    void injectFragment(VideoFragment videoFragment);
    void injectFragment(MineFragment mineFragment);
}
