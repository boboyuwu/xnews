package com.boboyuwu.xnews.ui.fragment.basefragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.boboyuwu.common.util.ToastUtils;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.app.helper.DayNightHelper;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.dagger.component.DaggerFragmentComponent;
import com.boboyuwu.xnews.dagger.component.FragmentComponent;
import com.boboyuwu.xnews.mvp.presenter.BasePresenter;
import com.boboyuwu.xnews.mvp.view.BaseView;

import java.lang.ref.WeakReference;

import javax.inject.Inject;

/**
 * Created by wubo on 2017/6/14.
 */

public abstract class BaseFragment<P extends BasePresenter> extends Fragment implements BaseView {
    protected WeakReference<AppCompatActivity> mActivity;
    private View mContainerView;
    @Inject
    protected P mPresenter;

    //protected GreenDaoHelper mGreenDaoHelper = NewsApplication.getAppComponent().getGreenDaoHelper();
    protected DayNightHelper mDayNightHelper= NewsApplication.getAppComponent().getDayNightHelper();
    //activity依附上来
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        mActivity= new WeakReference(context);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initInject();
        if(mPresenter==null)
            throw new RuntimeException("请实现initInject方法,使用getActivityComponent()获取ActivityComponent对象调用injectFragment" +
                    "方法将自己传递进去!");
        mPresenter.attachView(this);
    }

    protected FragmentComponent getFragmentComponent(){
        return DaggerFragmentComponent.builder().build();
    }

    /**
     *  使用了Dagger2框架所以强制集成的类实现此方法初始化注入对象
     */
    protected abstract void initInject() ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View inflate = inflater.inflate(getLayout(), container, false);
        return inflate;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mContainerView = view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //当view创建成功
        preInit();
        init();
        setListener();
    }

    protected void setListener() {

    }

    protected abstract int getLayout();

    protected void init() {

    }

    //初始化之前执行
    protected void preInit(){

    }

    //统一获取view的方式
    @SuppressWarnings("unchecked")
    protected final <E extends View> E getView (int id) {
        try {
            return (E)mContainerView.findViewById(id);
        } catch (ClassCastException ex) {
            throw ex;
        }
    }


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
    public void dissMissDialog() {

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mPresenter.detachView();
        mPresenter.deleteDispose();
    }
}
