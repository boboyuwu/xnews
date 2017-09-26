package com.boboyuwu.xnews.common.utils;

/**
 * Created by wubo on 2017/9/5.
 * 管理Error信息的类
 */

public class RxSubscriberState {

    private Builder mBuilder;
    //首次加载状态
    public static final int LOAD =1;
    //加载更多状态
    public static final int MORE_LOAD =2;

    public static final int NORMAL_ERROR=-1;
    public static final int NETWORK_ERROR=-2;
    public static final int HTTP_ERROR=-3;

    private RxSubscriberState(){

    }
    private RxSubscriberState(Builder builder){
        mBuilder = builder;
    }

    public String getErrorMsg(){
        return mBuilder.mErrorMsg;
    }
    public int getErrorType(){
        return mBuilder.mErrorType;
    }

    public int getLoadMode(){
        return mBuilder.mLoadMode;
    }

    public Builder getBuilder(){
        return mBuilder;
    }

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder{
        private int mErrorType;
        private int mLoadMode;
        private String mErrorMsg;


        public Builder setErrorMsg(String errorMsg){
            mErrorMsg = errorMsg;
            return this;
        }
        public Builder setErrorType(int errorType){
            mErrorType = errorType;
            return this;
        }
        public Builder setLoadMode(int loadMode){
            mLoadMode = loadMode;
            return this;
        }
        public RxSubscriberState build(){
            return new RxSubscriberState(this);
        }
    }
}
