package com.boboyuwu.xnews.ui.fragment.helper;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.boboyuwu.xnews.ui.fragment.videofragment.VideoNewsTabFragment;

/**
 * Created by wubo on 2017/9/29.
 */

public class VideoNewsFragmentFactoryIml implements FragmentFactory{
    @Override
    public Fragment createFragment(String tab, Bundle bundle) {
        VideoNewsTabFragment videoNewsTabFragment = new VideoNewsTabFragment();
        if(bundle!=null){
            videoNewsTabFragment.setArguments(bundle);
        }
        return videoNewsTabFragment;
    }

    @Override
    public Fragment createFragment(int tab, Bundle bundle) {
        return null;
    }
}
