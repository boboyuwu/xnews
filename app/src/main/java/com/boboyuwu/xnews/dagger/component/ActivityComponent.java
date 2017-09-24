package com.boboyuwu.xnews.dagger.component;


import com.boboyuwu.xnews.ui.activity.homepageactivity.AddChannelActivity;
import com.boboyuwu.xnews.ui.activity.homepageactivity.MainActivity;
import com.boboyuwu.xnews.ui.activity.homepageactivity.NewsDetailActivity;
import com.boboyuwu.xnews.ui.activity.homepageactivity.SplashActivity;

import dagger.Component;

/**
 * Created by wubo on 2017/6/13.
 */

@Component 
public interface ActivityComponent {
      void injectActivity(MainActivity mainActivity);
      void injectActivity(SplashActivity splashActivity);
      void injectActivity(AddChannelActivity addChannelActivity);
      void injectActivity(NewsDetailActivity newsDetailActivity);

}
