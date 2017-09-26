package com.boboyuwu.xnews.ui.activity.baseactivity;

import com.boboyuwu.xnews.mvp.presenter.BasePresenter;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by wubo on 2017/6/13.
 * 具有Rx订阅管理的Activiity,基本使用rx请求的继承它来管理
 * 同步当onComplete 或 onError状态时默认会dispose
 *
 */

public abstract class RxManageActivity<P extends BasePresenter> extends BaseActivity<P> {
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
    protected void onDestroy() {
        super.onDestroy();
        deleteDispose();
    }
}
