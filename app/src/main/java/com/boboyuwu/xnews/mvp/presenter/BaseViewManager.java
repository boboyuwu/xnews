package com.boboyuwu.xnews.mvp.presenter;


import com.boboyuwu.xnews.mvp.view.BaseView;

/**
 * Created by wubo on 2017/6/11.
 * 管理mvp的v的生命周期
 */

public interface BaseViewManager <T extends BaseView>{

    void attachView(T baseView);
    void detachView();
}
