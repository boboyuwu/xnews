package com.boboyuwu.xnews.dagger.module;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by boboyuwu on 2017/8/29.
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
}
