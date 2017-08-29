package com.boboyuwu.common.app;

import android.app.Application;

/**
 * Created by wubo on 2017/6/10.
 */

public class BaseApplication extends Application {

    protected static BaseApplication mBaseApplication;
    //protected static BaseApplication mBaseApplication;
    @Override
    public void onCreate() {
        super.onCreate();
        //init
        mBaseApplication=this;
    }


    public static BaseApplication getApplication(){
        return mBaseApplication;
    }


}
