package com.boboyuwu.xnews.dagger.module;

import com.boboyuwu.xnews.mvp.view.HomePageView;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wubo on 2017/6/15.
 */

@Module
public class HomePageNewsFragmentModule {
    private HomePageView mHomePageView;

    public HomePageNewsFragmentModule(HomePageView homePageView){

        mHomePageView = homePageView;
    }

    @Provides
    public HomePageView provideHomePageView(){
        return mHomePageView;
    }
}
