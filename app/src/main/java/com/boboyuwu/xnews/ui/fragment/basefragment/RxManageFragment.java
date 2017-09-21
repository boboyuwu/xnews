package com.boboyuwu.xnews.ui.fragment.basefragment;


import com.boboyuwu.xnews.mvp.presenter.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by wubo on 2017/6/14.
 * 提供使用Rx管理的基类
 */

public abstract class RxManageFragment <P extends BasePresenter>extends BaseFragment<P> {
    private final CompositeDisposable mCompositeDisposable=new CompositeDisposable();

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

    @Override
    public void onDestroy() {
        super.onDestroy();
        deleteDispose();
    }
}
