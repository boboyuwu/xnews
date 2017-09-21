package com.boboyuwu.xnews.ui.fragment.basefragment;

import android.view.View;
import android.view.View.OnClickListener;

import com.boboyuwu.common.loadandretrymanager.LoadingAndRetryManager;
import com.boboyuwu.common.loadandretrymanager.OnLoadingAndRetryListener;
import com.boboyuwu.xnews.mvp.presenter.BasePresenter;
import com.example.boboyuwu.zhihunews.R;

/**
 * Created by wubo on 2017/9/21.
 * 提供加载状态控制的Fragment基类
 */

public abstract class LoadingAndRetryFragment <P extends BasePresenter> extends RxManageFragment<P>{
    private LoadingAndRetryManager mLoadingAndRetryManager;

    @Override
    protected void preInit() {
        super.preInit();
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(this, mOnLoadingAndRetryListener);
        mLoadingAndRetryManager.showContent();
    }

    /**
     * 显示、隐藏、加载中状态...
     * */
    protected void showContent(){
        mLoadingAndRetryManager.showContent();
    }

    protected void showLoading(){
        mLoadingAndRetryManager.showLoading();
    }


    protected void showEmpty(){
        mLoadingAndRetryManager.showEmpty();
    }

    protected  void showRetry(){
        mLoadingAndRetryManager.showRetry();
    }

    private OnLoadingAndRetryListener mOnLoadingAndRetryListener=new OnLoadingAndRetryListener() {
        @Override
        public void setRetryEvent(View retryView) {
            retryView.findViewById(R.id.bt_operate).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRetryClick();
                }
            });
        }
    };

    /**
     * 网络错误情况下,点击重试
     * */
    protected void onRetryClick() {

    }
}
