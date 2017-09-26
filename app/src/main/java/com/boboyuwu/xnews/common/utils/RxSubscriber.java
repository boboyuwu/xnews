package com.boboyuwu.xnews.common.utils;

/**
 * Created by wubo on 2017/9/5.
 * 统一处理Error信息的subscriber
 */

import android.text.TextUtils;

import com.boboyuwu.common.loadmorerecyclerview.NetworkUtils;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.mvp.view.BaseView;
import com.example.boboyuwu.zhihunews.R;

import io.reactivex.subscribers.ResourceSubscriber;

public abstract class RxSubscriber<T> extends ResourceSubscriber<T> {

    private final BaseView mBaseView;
    private RxSubscriberState mRxSubscriberState;

    public RxSubscriber(BaseView baseView) {
        this(baseView,null);
    }
    public RxSubscriber(BaseView baseView,RxSubscriberState rxSubscriberState) {
        mBaseView = baseView;
        mRxSubscriberState = rxSubscriberState;
    }

    @Override
    public void onComplete() {

    }

    @Override
    public void onError(Throwable e) {
        //自己设置了错误信息
        if(mRxSubscriberState!=null&&!TextUtils.isEmpty(mRxSubscriberState.getErrorMsg())){
            mRxSubscriberState.getBuilder().setErrorType(RxSubscriberState.NORMAL_ERROR);
            processError();
        }else if(!NetworkUtils.isNetAvailable(NewsApplication.getApplication())){
            mRxSubscriberState.getBuilder().setErrorType(RxSubscriberState.NETWORK_ERROR);
            mRxSubscriberState.getBuilder().setErrorMsg(NewsApplication.getApplication().getResources().getString(R.string.network_unconnect));
            processError();
        }else {
            mRxSubscriberState.getBuilder().setErrorType(RxSubscriberState.HTTP_ERROR);
            mRxSubscriberState.getBuilder().setErrorMsg(NewsApplication.getApplication().getResources().getString(R.string.network_service_error));
            processError();
        }

    }

    public void processError(){
        if(mRxSubscriberState.getLoadMode()==RxSubscriberState.LOAD){
            mBaseView.onError(mRxSubscriberState);
        }else if(mRxSubscriberState.getLoadMode()== RxSubscriberState.MORE_LOAD){
            mBaseView.onLoadMoreError(mRxSubscriberState);
        }
    }

}