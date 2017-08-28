package com.boboyuwu.xnews.dagger.module;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by wubo on 2017/6/13.
 */

@Module
public class AppModule {
    private Context mContext;

    public AppModule(Context context){
        mContext = context;
    }

    @Provides
    public Context provideContext(){
        return mContext;
    }


}
