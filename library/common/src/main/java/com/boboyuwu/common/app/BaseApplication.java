package com.boboyuwu.common.app;

import android.app.Application;

/**
 * Created by wubo on 2017/6/10.
 */

public class BaseApplication extends Application {

    protected static BaseApplication mNewsApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        //init
        mNewsApplication=this;
    }



    public static BaseApplication getInstance(){
        return mNewsApplication;
    }


}
