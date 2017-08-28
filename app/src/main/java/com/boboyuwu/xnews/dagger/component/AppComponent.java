package com.boboyuwu.xnews.dagger.component;


import com.boboyuwu.xnews.dagger.module.AppModule;
import com.boboyuwu.xnews.mvp.model.helper.SPHelper;

import dagger.Component;

/**
 * Created by wubo on 2017/6/13.
 */

@Component(modules = AppModule.class)
public interface AppComponent {

    //这里实现获取一些单例的东西比如封装的：数据库操作、网络请求、SP操作、
    SPHelper getSPHelper();
}
