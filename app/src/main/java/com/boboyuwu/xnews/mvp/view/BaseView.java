package com.boboyuwu.xnews.mvp.view;

import com.boboyuwu.xnews.common.utils.RxSubscriberState;

/**
 * Created by wubo on 2017/6/10.
 * 定义view的基类
 * 基本每个activity都会发生的行为
 */

public interface BaseView {

    //加载数据时发生错误
    void onError(RxSubscriberState msg);
    //加载更多数据时发生了错误
    void onLoadMoreError(RxSubscriberState msg);
    void showDialog();
    void dissMissDialog();

}
