package com.boboyuwu.xnews.ui.fragment.basefragment;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.TextView;

import com.boboyuwu.common.util.SizeUtils;
import com.boboyuwu.xnews.mvp.presenter.BasePresenter;
import com.example.boboyuwu.zhihunews.R;


/**
 * Created by wubo on 2017/7/5.
 */

public abstract class SupportToolBarFragment<P extends BasePresenter> extends RxManageFragment<P> implements OnClickListener {

    private Toolbar mToolbar;
    private TextView mToolBarTitleTv;
    private TextView mRight1Tv;
    private TextView mRight2Tv;
    private TextView mRight3Tv;
    private ImageView mBackIv;

    @Override
    protected void init() {
        super.init();
        initToolBar();
        setToolBar();
    }

    //继承这个activity的toolbar id设置为资源id中的toolBar
    private void initToolBar() {
        mToolbar = getView(R.id.toolBar);
        if (mToolbar != null) {
            mToolbar.setBackgroundColor(getResources().getColor(R.color.toolbar_bg_color));
            mToolBarTitleTv = mToolbar.findViewById(R.id.toolbar_title_tv);
            mRight1Tv =  mToolbar.findViewById(R.id.right1_tv);
            mRight2Tv = mToolbar.findViewById(R.id.right2_tv);
            mRight3Tv =  mToolbar.findViewById(R.id.right3_tv);
            mBackIv = mToolbar.findViewById(R.id.back_iv);
            mRight1Tv.setOnClickListener(this);
            mRight2Tv.setOnClickListener(this);
            mRight3Tv.setOnClickListener(this);
            mActivity.get().setSupportActionBar(mToolbar);
        }
    }


    protected void enableBackPress(){
        mBackIv.setVisibility(View.VISIBLE);
        mBackIv.setOnClickListener(this);
    }
    protected void onBackClick() {
        mActivity.get().finish();
    }


    protected void setToolBarTitle(String title) {
        if (mToolbar == null) {
            throw new RuntimeException("this method must set the toolbar id be toolbar!");
        }
        if (mToolBarTitleTv != null) {
            mToolBarTitleTv.setText(title);
        }

    }

    protected void setToolBarColor(int color) {
        if (mToolbar == null) {
            throw new RuntimeException("this method must set the toolbar id be toolbar!");
        }
        mToolbar.setBackgroundColor(color);
    }



    protected void setToolBarRight1Text(String text){
        setToolBarRight1Text(text,getResources().getColor(R.color.white));
    }

    protected void setToolBarRight1Text(String text, int color) {
        if (mRight1Tv != null) {
            mRight1Tv.setVisibility(View.VISIBLE);
            mRight1Tv.setTextColor(color);
            mRight1Tv.setText(text);
        }
    }

    protected void setToolBarRight2Text(String text){
        setToolBarRight2Text(text,getResources().getColor(R.color.white));
    }


    protected void setToolBarRight2Text(String text, int color) {
        if (mRight2Tv != null) {
            mRight2Tv.setVisibility(View.VISIBLE);
            mRight2Tv.setTextColor(color);
            mRight2Tv.setText(text);
        }
    }


    protected void setToolBarRight3Text(String text){
        setToolBarRight3Text(text,getResources().getColor(R.color.white));
    }

    protected void setToolBarRight3Text(String text, int color) {
        if (mRight3Tv != null) {
            mRight3Tv.setVisibility(View.VISIBLE);
            mRight3Tv.setTextColor(color);
            mRight3Tv.setText(text);
        }
    }

    protected void setToolBarRight1Icon(int iconRes) {
        if (mRight1Tv != null) {
            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(SizeUtils.dp2px(22), SizeUtils.dp2px(22));
            mRight1Tv.setLayoutParams(marginLayoutParams);
            mRight1Tv.setVisibility(View.VISIBLE);
            mRight1Tv.setBackgroundResource(iconRes);
        }
    }

    protected void setToolBarRight2Icon(int iconRes) {
        if (mRight2Tv != null) {
            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(SizeUtils.dp2px(22), SizeUtils.dp2px(22));
            mRight2Tv.setLayoutParams(marginLayoutParams);
            mRight2Tv.setVisibility(View.VISIBLE);
            mRight2Tv.setBackgroundResource(iconRes);
        }
    }

    protected void setToolBarRight3Icon(int iconRes) {
        if (mRight3Tv != null) {
            MarginLayoutParams marginLayoutParams = new MarginLayoutParams(SizeUtils.dp2px(22), SizeUtils.dp2px(22));
            mRight3Tv.setLayoutParams(marginLayoutParams);
            mRight3Tv.setVisibility(View.VISIBLE);
            mRight3Tv.setBackgroundResource(iconRes);
        }
    }


    //继承者可以在init中设置bar或者重写这个方法设置bar
    protected void setToolBar() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.back_iv:
                onBackClick();
                break;
            case R.id.right1_tv:
                onRight1Click();
                break;
            case R.id.right2_tv:
                onRight2Click();
                break;
            case R.id.right3_tv:
                onRight3Click();
                break;
        }
    }

    /**
     *  需要右侧按钮点击事件的请具体实现这些方法
     * */
    protected void onRight3Click() {

    }

    protected void onRight2Click() {

    }

    protected  void onRight1Click(){

    }

}
