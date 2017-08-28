package com.boboyuwu.xnews.ui.activity.baseactivity;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.boboyuwu.xnews.mvp.presenter.BaseViewManager;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by wubo on 2017/6/13.
 * 具有Rx订阅管理的Activiity,子类加上泛型
 */

public abstract class RxManageActivity<P extends BaseViewManager> extends BaseActivity<P> {
    private final CompositeDisposable mCompositeDisposable=new CompositeDisposable();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        //onActivityCreate();
        init();
        setListener();
    }


    //添加rx 订阅关系
    protected void addDispose(Disposable disposable){
        if(!disposable.isDisposed()){
            mCompositeDisposable.add(disposable);
        }
    }

    private void deleteDispose(){
        if(!mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
    }


    protected void setListener(){

    }

    //所有初始化都在这里面做
    protected  void init(){

    }

    //初始化之前做的一些事情
  /*  protected void onActivityCreate(){

    }*/
    protected abstract int getLayout();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        deleteDispose();
    }
}
