package com.boboyuwu.xnews.ui.activity.baseactivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.boboyuwu.common.util.ToastUtils;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.app.helper.DayNightHelper;
import com.boboyuwu.xnews.app.helper.GreenDaoHelper;
import com.boboyuwu.xnews.common.constants.Keys;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.dagger.component.ActivityComponent;
import com.boboyuwu.xnews.dagger.component.DaggerActivityComponent;
import com.boboyuwu.xnews.mvp.presenter.BasePresenter;
import com.boboyuwu.xnews.mvp.view.BaseView;

import javax.inject.Inject;

/**
 * Created by wubo on 2017/6/10.
 * activity基类规范每个activity格式
 * base中抽出最核心的,最原始的,不包含第三方的所有共用的东西
 */

public abstract class BaseActivity <P extends BasePresenter>extends AppCompatActivity implements BaseView {
    @Inject
    protected P mPresenter;

    protected GreenDaoHelper mGreenDaoHelper = NewsApplication.getAppComponent().getGreenDaoHelper();
    protected DayNightHelper mDayNightHelper= NewsApplication.getAppComponent().getDayNightHelper();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initInject();

        if(mPresenter==null) {
            throw new RuntimeException("请实现initInject方法,使用getActivityComponent()获取ActivityComponent对象调用injectActivity" +
                    "方法将自己传递进去!");
        }
        mPresenter.attachView(this);

        preInit();
        init();
        setListener();
    }

    protected void setListener() {

    }


    protected abstract int getLayout();


    /**
     * 所有初始化都在这里面做
     * */
    protected  void init(){

    }

    /**
     * 所有初始化之前的操作都在这里面做
     * */
    protected void preInit() {

    }

    protected void jumpActivity(Class clazz, Bundle bundle){
        Intent intent= new Intent(this,clazz);
        intent.putExtra(Keys.BUNDLE,bundle);
        startActivity(intent);
    }



    //注入对象
    protected abstract void initInject() ;


    protected ActivityComponent getActivityComponent(){
        return DaggerActivityComponent.builder().build();
    }


    //统一获取view的方式
    @SuppressWarnings("unchecked")
    protected final <E extends View> E getView (int id) {
        try {
            return (E)findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }

    //去掉顶部状态栏,全屏


    @Override
    public void onError(RxSubscriberState msg) {
        ToastUtils.showShort(msg.getErrorMsg());
    }

    @Override
    public void onLoadMoreError(RxSubscriberState msg) {

    }

    @Override
    public void showDialog() {

    }

    @Override
    public void disMissDialog() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter.deleteDispose();
    }
}
