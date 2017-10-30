package com.boboyuwu.xnews.ui.fragment.basefragment;

import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;

import com.boboyuwu.common.loadandretrymanager.LoadingAndRetryManager;
import com.boboyuwu.common.loadandretrymanager.OnLoadingAndRetryListener;
import com.boboyuwu.common.widget.loadingviewlib.view.LVEatBeans;
import com.boboyuwu.xnews.mvp.presenter.BasePresenter;
import com.example.boboyuwu.zhihunews.R;

/**
 * Created by wubo on 2017/9/21.
 * 提供加载状态控制的Fragment基类
 */

public abstract class LoadingAndRetryFragment <P extends BasePresenter> extends SupportToolBarFragment<P>{
    private LoadingAndRetryManager mLoadingAndRetryManager;
    private LVEatBeans mLvEatBeans;


    @Override
    protected void init() {
        super.init();
        mLoadingAndRetryManager = LoadingAndRetryManager.generate(getActivity(), mOnLoadingAndRetryListener);
        mLoadingAndRetryManager.showContent();
    }

    /**
     * 如果不想管理默认的整个界面调用这个方法返回需要设置的view
     * */
    protected View initManagerView(){
        return null;
    }


    /**
     * 空状态、加载中、网络错误状态...
     * */
    protected void showContent(){
        mLvEatBeans.stopAnim();
        mLoadingAndRetryManager.showContent();
    }

    protected void showLoading(){
        mLvEatBeans.startAnim();
        mLoadingAndRetryManager.showLoading();
    }


    protected void showEmpty(){
        mLvEatBeans.stopAnim();
        mLoadingAndRetryManager.showEmpty();
    }

    protected  void showRetry(){
        mLvEatBeans.stopAnim();
        mLoadingAndRetryManager.showRetry();
    }

    private OnLoadingAndRetryListener mOnLoadingAndRetryListener=new OnLoadingAndRetryListener() {
        @Override
        public void setRetryEvent(View retryView) {
            retryView.findViewById(R.id.bt_operate).setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onRetryClick(view);
                }
            });
        }

        @Override
        public void setLoadingEvent(View loadingView) {
            loadingView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onLoadingClick(view);
                }
            });
        }

        @Override
        public void setEmptyEvent(View emptyView) {
            emptyView.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View view) {
                    onEmptyClick(view);
                }
            });
        }

        @Override
        public int generateLoadingLayoutId() {
            return LoadingAndRetryFragment.this.generateLoadingLayoutId();
        }

        @Override
        public int generateRetryLayoutId() {
            return LoadingAndRetryFragment.this.generateRetryLayoutId();
        }

        @Override
        public int generateEmptyLayoutId() {
            return LoadingAndRetryFragment.this.generateEmptyLayoutId();
        }

        @Override
        public View generateLoadingLayout() {
            return LoadingAndRetryFragment.this.generateLoadingLayout();
        }

        @Override
        public View generateRetryLayout() {
            return LoadingAndRetryFragment.this.generateRetryLayout();
        }

        @Override
        public View generateEmptyLayout() {
            return LoadingAndRetryFragment.this.generateEmptyLayout();
        }
    };
    /**
     * 重设数据为空..view
     * */
    protected  View generateEmptyLayout(){
        return null;
    }
    /**
     * 重设数据为空..view
     * */
    protected  View generateRetryLayout(){
        return null;
    }
    /**
     * 重设数据为空..view
     * */
    protected  View generateLoadingLayout(){
        mLvEatBeans = new LVEatBeans(mActivity.get());
        mLvEatBeans.setLayoutParams(new MarginLayoutParams(LayoutParams.WRAP_CONTENT,LayoutParams.WRAP_CONTENT));
        mLvEatBeans.setViewColor(getResources().getColor(R.color.loading_view_color));
        mLvEatBeans.setEyeColor(getResources().getColor(R.color.loading_eyes_color));
        return mLvEatBeans;
    }
    /**
     * 重设数据为空..布局
     * */
    protected  int generateEmptyLayoutId(){
        return LoadingAndRetryManager.NO_LAYOUT_ID;
    }


    /**
     * 重设网络失败..布局
     * */
    protected  int generateRetryLayoutId(){
        return LoadingAndRetryManager.NO_LAYOUT_ID;
    }


    /**
     * 重设加载中..布局
     * */
    protected int generateLoadingLayoutId() {
        return LoadingAndRetryManager.NO_LAYOUT_ID;
    }

    /**
     * 状态为空,点击重试
     * */
    protected void onEmptyClick(View view) {


    }
    /**
     * 正在加载中,点击按钮
     * */
    protected void onLoadingClick(View view) {


    }

    /**
     * 网络错误情况下,点击重试
     * */
    protected void onRetryClick(View view) {

    }

}
