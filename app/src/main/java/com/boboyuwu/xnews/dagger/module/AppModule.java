package com.boboyuwu.xnews.dagger.module;

import android.content.Context;

import com.boboyuwu.xnews.app.helper.DayNightHelper;
import com.boboyuwu.xnews.app.helper.GreenDaoHelper;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by boboyuwu on 2017/8/29.
 *
 */

@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context){
        mContext = context;
    }
    @Singleton
    @Provides
    public Context provideContext(){
        return mContext;
    }

    @Singleton
    @Provides
    public GreenDaoHelper provideGreenDaoHelper(){
        return new GreenDaoHelper();
    }


    @Singleton
    @Provides
    public DayNightHelper provideDayNightHelper(){
        return new DayNightHelper();
    }



}
