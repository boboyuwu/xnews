package com.boboyuwu.common.loadmorerecyclerview;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewStub;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.boboyuwu.common.R;

import static com.boboyuwu.common.loadmorerecyclerview.LoadingFooter.StateEnum.Normal;


/**
 * Created by cundong on 2015/10/9.
 * <p/>
 * ListView/GridView/RecyclerView 分页加载时使用到的FooterView
 */
public class LoadingFooter extends RelativeLayout {

    protected StateEnum mStateEnum = Normal;
    private View mLoadingView;
    private View mNetworkErrorView;
    private View mTheEndView;
    private ProgressBar mLoadingProgress;
    private TextView mLoadingText;
    private TextView mTheEndText;
    private TextView mNetworkErrorText;

    public LoadingFooter(Context context) {
        super(context);
        init(context);
    }

    public LoadingFooter(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public LoadingFooter(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void init(Context context) {
        inflate(context, R.layout.sample_common_list_footer, this);
        setOnClickListener(null);

        State stateInfo = new StateInfo(Normal,"");
        setState(stateInfo, true);
    }

    public StateEnum getState() {
        return mStateEnum;
    }

    public void setState(State status ) {
        setState(status, true);
    }

    /**
     * 设置状态
     *
     * @param status
     * @param showView 是否展示当前View
     */
    public void setState(State status, boolean showView) {
       /* if (mStateEnum == status.getStateEnum()) {
            return;
        }*/
        mStateEnum = status.getStateEnum();

        switch (status.getStateEnum()) {

            case Normal:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                break;
            case Loading:
                setOnClickListener(null);
                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mLoadingView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.loading_viewstub);
                    mLoadingView = viewStub.inflate();

                    mLoadingProgress = (ProgressBar) mLoadingView.findViewById(R.id.loading_progress);
                    mLoadingText = (TextView) mLoadingView.findViewById(R.id.loading_text);
                    if(!TextUtils.isEmpty(status.getText())){
                        mLoadingText.setText(status.getText());
                    }
                } else {
                    mLoadingView.setVisibility(VISIBLE);
                    if(!TextUtils.isEmpty(status.getText())){
                        mLoadingText.setText(status.getText());
                    }
                }

                mLoadingView.setVisibility(showView ? VISIBLE : GONE);

                mLoadingProgress.setVisibility(View.VISIBLE);

                break;
            case TheEnd:
                setOnClickListener(null);
                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mNetworkErrorView != null) {
                    mNetworkErrorView.setVisibility(GONE);
                }

                if (mTheEndView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.end_viewstub);
                    mTheEndView = viewStub.inflate();
                    mTheEndText = (TextView) mTheEndView.findViewById(R.id.loading_text);
                    if(!TextUtils.isEmpty(status.getText())){
                        mTheEndText.setText(status.getText());
                    }
                } else {
                    mTheEndView.setVisibility(VISIBLE);
                    if(!TextUtils.isEmpty(status.getText())){
                        mTheEndText.setText(status.getText());
                    }
                }

                mTheEndView.setVisibility(showView ? VISIBLE : GONE);
                break;
            case NetWorkError:

                if (mLoadingView != null) {
                    mLoadingView.setVisibility(GONE);
                }

                if (mTheEndView != null) {
                    mTheEndView.setVisibility(GONE);
                }

                if (mNetworkErrorView == null) {
                    ViewStub viewStub = (ViewStub) findViewById(R.id.network_error_viewstub);
                    mNetworkErrorView = viewStub.inflate();
                    mNetworkErrorText = (TextView) mNetworkErrorView.findViewById(R.id.loading_text);
                    if(!TextUtils.isEmpty(status.getText())){
                        mNetworkErrorText.setText(status.getText());
                    }
                } else {
                    mNetworkErrorView.setVisibility(VISIBLE);
                    if(!TextUtils.isEmpty(status.getText())){
                        mNetworkErrorText.setText(status.getText());
                    }
                }

                mNetworkErrorView.setVisibility(showView ? VISIBLE : GONE);
                break;
            default:

                break;
        }
    }

    /**只提供基础的状态*/
    public static enum StateEnum {
        Normal/**正常*/, TheEnd/**加载到最底了*/, Loading/**加载中..*/, NetWorkError/**网络异常*/
    }

    /**暂时为了扩展定义一个接口*/
    public  interface State{
        StateEnum getStateEnum();
        void  setStateEnum(StateEnum stateEnum);
        String getText();
        void setText(String text);
    }
    /**封装State状态一堆信息,提供改变状态的text*/
    public static class StateInfo implements State{
          private StateEnum mStateEnum;
          private String mText;

        public StateInfo(StateEnum stateEnum,String text){
            this.mStateEnum=stateEnum;
            this.mText=text;
        }
        public StateEnum getStateEnum() {
            return mStateEnum;
        }

        public void setStateEnum(StateEnum stateEnum) {
            mStateEnum = stateEnum;
        }

        public String getText() {
            return mText;
        }

        public void setText(String text) {
            this.mText = text;
        }
    }

}