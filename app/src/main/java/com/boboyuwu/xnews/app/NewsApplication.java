package com.boboyuwu.xnews.app;

import com.boboyuwu.common.app.BaseApplication;
import com.boboyuwu.xnews.dagger.component.AppComponent;
import com.boboyuwu.xnews.dagger.component.DaggerAppComponent;
import com.boboyuwu.xnews.dagger.module.AppModule;

/**
 * Created by wubo on 2017/6/10.
 */

public class NewsApplication extends BaseApplication {

    private static AppComponent mAppComponent;
    @Override
    public void onCreate() {
        super.onCreate();
       //初始化一次
    }

    public static AppComponent getAppComponent(){
        if(null==mAppComponent){
            mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(getInstance())).build();
        }
        return mAppComponent;
    }
}
