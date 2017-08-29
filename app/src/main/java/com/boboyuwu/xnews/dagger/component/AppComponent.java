package com.boboyuwu.xnews.dagger.component;


import com.boboyuwu.xnews.dagger.module.AppModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by wubo on 2017/6/13.
 * 这里要做成单例模式，这些Helper对象都是只需要获取一次,全局使用的
 */

@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {
    //SPHelper getSPHelper();
    //RetrofitHelper getRetrofitHelper();
    //DBHelper getDBHelper();
}
