package com.boboyuwu.xnews.dagger.component;


import com.boboyuwu.xnews.dagger.module.ActivityModule;
import com.boboyuwu.xnews.ui.activity.homepageactivity.MainActivity;

import dagger.Component;

/**
 * Created by wubo on 2017/6/13.
 */

@Component (modules = ActivityModule.class)
public interface ActivityComponent {
      void injectActivity(MainActivity mainActivity);
}
