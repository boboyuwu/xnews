package com.boboyuwu.xnews.ui.fragment.basefragment;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.boboyuwu.xnews.mvp.presenter.BasePresenter;


/**
 * Created by wubo on 2017/6/15.
 * 为节省性能,配置一个懒加载Fragment，所以继承这类必须实现onLazyLoadData()懒加载这个方法
 *
 *
 * setUserVisibleHint是执行在Fragment所有生命周期之前的，所以必须结合Fragment创建后状态进行初始化
 * 否则Fragment没有创建是拿不到Bundle等传递过来的值的
 */

public abstract class LazyFragment<P extends BasePresenter> extends LoadingAndRetryFragment<P>{

    private boolean mHasViewCreate;
    private boolean mHasViewCreateAndUserVisible;

    //true显示 false隐藏 执行在onAttch之前
    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if(isVisibleToUser&&mHasViewCreate){
            mHasViewCreateAndUserVisible=true;
        }else{
            mHasViewCreateAndUserVisible=false;
        }

        if(mHasViewCreateAndUserVisible){
            onLazyLoadData();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mHasViewCreate=true;
    }


    //子类调用这个方法就可以实现懒加载,那么第一个Fragment怎么办呢?
    // 在init方法中判断isVisibleToUser  以及初始数据是否为Null如果不为null表示请求过接口 如果为null并且isVisibleToUser为true即可加载数据
    protected abstract void onLazyLoadData();
}
