package com.boboyuwu.xnews.ui.activity.baseactivity;

import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.boboyuwu.xnews.mvp.presenter.BaseActivityPresenter;
import com.example.boboyuwu.zhihunews.R;

/**
 * Created by wubo on 2017/6/10.
 * 设计设个Support类的目的在于支持常见的一些Bar格式,多变灵活设置Bar格式、样式
 * ToolBar设置的Menu本身text不支持设置字体颜色  这个类抽取与一些沉浸式等bar上样式的功能
 * 这个类需要好好设计一下
 */

public abstract class SupportToolBarActivity<P extends BaseActivityPresenter> extends RxManageActivity<P> {

    private Toolbar mToolbar;
    private TextView mTitleTv;
    private TextView mRight1Tv;
    private TextView mRight2Tv;
    private TextView mRight3Tv;

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
            mTitleTv = (TextView) mToolbar.findViewById(R.id.title_tv);
            mRight1Tv = (TextView) mToolbar.findViewById(R.id.right1_tv);
            mRight2Tv = (TextView) mToolbar.findViewById(R.id.right2_tv);
            mRight3Tv = (TextView) mToolbar.findViewById(R.id.right3_tv);
            mToolbar.setTitle("");
            setSupportActionBar(mToolbar);
        }
    }


    protected void setToolBarTitle(String title) {
        if (mToolbar == null) {
            throw new RuntimeException("this method must set the toolbar id be toolbar!");
        }
        if (mTitleTv != null) {
            mTitleTv.setText(title);
        }

    }

    protected void setToolBarColor(int color) {
        if (mToolbar == null) {
            throw new RuntimeException("this method must set the toolbar id be toolbar!");
        }
        mToolbar.setBackgroundColor(color);
    }


    protected void setToolBarRight1Text(String text, int color) {
        if (mRight1Tv != null) {
            mRight1Tv.setVisibility(View.VISIBLE);
            mRight1Tv.setTextColor(color);
            mRight1Tv.setText(text);
        }
    }

    protected void setToolBarRight2Text(String text, int color) {
        if (mRight2Tv != null) {
            mRight2Tv.setVisibility(View.VISIBLE);
            mRight2Tv.setTextColor(color);
            mRight2Tv.setText(text);
        }
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
            mRight1Tv.setVisibility(View.VISIBLE);
            mRight1Tv.setBackgroundResource(iconRes);
        }
    }

    protected void setToolBarRight2Icon(int iconRes) {
        if (mRight2Tv != null) {
            mRight2Tv.setVisibility(View.VISIBLE);
            mRight2Tv.setBackgroundResource(iconRes);
        }
    }

    protected void setToolBarRight3Icon(int iconRes) {
        if (mRight3Tv != null) {
            mRight3Tv.setVisibility(View.VISIBLE);
            mRight3Tv.setBackgroundResource(iconRes);
        }
    }


    //继承者可以在init中设置bar或者重写这个方法设置bar
    protected void setToolBar() {

    }
}
