package com.boboyuwu.xnews.mvp.presenter;


import com.boboyuwu.xnews.mvp.view.BaseView;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by wubo on 2017/6/10.
 * 所有的presenter继承BasePresenter，BasePresenter自动管理Rx,如果不继承BasePresenter，Rx订阅解绑需要自己去实现
 */

public class BasePresenter<T extends BaseView> {

    protected T mBaseView;
    private CompositeDisposable mCompositeDisposable=new CompositeDisposable();
    /*@Inject*/
    public BasePresenter(){

    }
    /**每一个Activity或者Fragment都生成了自己的对象,所以每个对象的mBaseView成员都赋值了自己
     当detachView的时候只是解除了自己对象里的成员变量,对别的对象成员变量并没有影响
     */
    public void attachView(T baseView) {
        mBaseView = baseView;
    }

    public void detachView() {
        mBaseView=null;
    }

    //添加rx 订阅关系
    public void addDispose(Disposable disposable){
        if(!disposable.isDisposed()){
            mCompositeDisposable.add(disposable);
        }
    }

    public void deleteDispose(){
        if(!mCompositeDisposable.isDisposed()){
            mCompositeDisposable.dispose();
        }
    }


}
