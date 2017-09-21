package com.boboyuwu.xnews.app;

import com.boboyuwu.common.app.BaseApplication;
import com.boboyuwu.common.loadandretrymanager.LoadingAndRetryManager;
import com.boboyuwu.xnews.dagger.component.AppComponent;
import com.boboyuwu.xnews.dagger.component.DaggerAppComponent;
import com.boboyuwu.xnews.dagger.module.AppModule;
import com.example.boboyuwu.zhihunews.R;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by wubo on 2017/6/10.
 */

public class NewsApplication extends BaseApplication {

    private static AppComponent mAppComponent;
    private static RefWatcher mRefWatcher;

    @Override
    public void onCreate() {
        super.onCreate();
       //初始化一次
        initLogger();
        initLeakCanary();
        initLoadAndRetryManager();
    }

    /**
     * 初始化加载重试和空内容布局
     */
    private void initLoadAndRetryManager(){
        LoadingAndRetryManager.BASE_RETRY_LAYOUT_ID   = R.layout.base_retry;
        LoadingAndRetryManager.BASE_LOADING_LAYOUT_ID = R.layout.base_loading;
        LoadingAndRetryManager.BASE_EMPTY_LAYOUT_ID   = R.layout.base_empty;
    }

    private void initLeakCanary() {
        mRefWatcher = LeakCanary.install(this);
    }

    public RefWatcher getRefWatcher(){
        return mRefWatcher;
    }

    private void initLogger() {
   /*     FormatStrategy formatStrategy = PrettyFormatStrategy.newBuilder()
                .showThreadInfo(false)  // (Optional) Whether to show thread info or not. Default true
                .methodCount(0)         // (Optional) How many method line to show. Default 2
                .build();

        Logger.addLogAdapter(new AndroidLogAdapter(formatStrategy){

        });*/

    }


    public static AppComponent getAppComponent(){
        if(null==mAppComponent){
            mAppComponent= DaggerAppComponent.builder().appModule(new AppModule(getApplication())).build();
        }
        return mAppComponent;
    }
}
