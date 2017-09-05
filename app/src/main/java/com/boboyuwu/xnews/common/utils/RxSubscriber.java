package com.boboyuwu.xnews.common.utils;

/**
 * Created by wubo on 2017/9/5.
 */

import android.text.TextUtils;
import android.util.Log;

import com.boboyuwu.common.loadmorerecyclerview.NetworkUtils;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.mvp.view.BaseView;
import com.example.boboyuwu.zhihunews.R;

import io.reactivex.subscribers.ResourceSubscriber;

/********************使用例子********************/
/*_apiService.login(mobile, verifyCode)
        .//省略
        .subscribe(new RxSubscriber<User user>(mContext,false) {
        @Override
        public void _onNext(User user) {
                // 处理user
        }
        @Override
        public void _onError(String msg) {
        ToastUtil.showShort(mActivity, msg);
        });*/
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
        Log.e("onError",e.toString());
        if(mRxSubscriberState!=null&&!TextUtils.isEmpty(mRxSubscriberState.getErrorMsg())){
            mRxSubscriberState.getBuilder().setErrorType(RxSubscriberState.NORMAL_ERROR);
            processError();
        }else if(!NetworkUtils.isNetAvailable(NewsApplication.getApplication())){
            mRxSubscriberState.getBuilder().setErrorType(RxSubscriberState.NETWORK_ERROR);
            mRxSubscriberState.getBuilder().setErrorMsg(NewsApplication.getApplication().getResources().getString(R.string.network_unconnect));
            processError();
        }else {
            mRxSubscriberState.getBuilder().setErrorType(RxSubscriberState.HTTP_ERROR);
            mRxSubscriberState.getBuilder().setErrorMsg(e.toString());
            processError();
        }

    }

    public void processError(){
        if(mRxSubscriberState.getLoadMode()==RxSubscriberState.LOAD){
            mBaseView.onError(mRxSubscriberState);
        }else if(mRxSubscriberState.getLoadMode()== RxSubscriberState.MORE_LOAD){
            mBaseView.onLoadMoreError(mRxSubscriberState);
        }
        /*switch (errorType){
            case NORMAL_ERROR:
                if(mRxSubscriberState.getLoadMode()==RxSubscriberState.LOAD){
                    mBaseView.onError(mRxSubscriberState);
                }else if(mRxSubscriberState.getLoadMode()==RxSubscriberState.MORE_LOAD){
                    mBaseView.onLoadMoreError(mRxSubscriberState);
                }
                break;
            case NETWORK_ERROR:
                if(mRxSubscriberState.getLoadMode()==RxSubscriberState.LOAD){
                    mBaseView.onError(mRxSubscriberState);
                }else if(mRxSubscriberState.getLoadMode()== RxSubscriberState.MORE_LOAD){
                    mBaseView.onLoadMoreError(mRxSubscriberState);
                }
                break;
            case HTTP_ERROR:
                mRxSubscriberState.getBuilder().setErrorMsg(NewsApplication.getApplication().getResources().getString(R.string.network_unconnect));
                if(mRxSubscriberState.getLoadMode()==RxSubscriberState.LOAD){
                    mBaseView.onError(mRxSubscriberState);
                }else if(mRxSubscriberState.getLoadMode()== RxSubscriberState.MORE_LOAD){
                    mBaseView.onLoadMoreError(mRxSubscriberState);
                }
                break;
        }*/
    }

}