package com.boboyuwu.xnews.ui.activity.homepageactivity;


import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.view.animation.AccelerateInterpolator;
import android.widget.TextView;

import com.boboyuwu.xnews.mvp.presenter.BaseViewManager;
import com.boboyuwu.xnews.ui.activity.baseactivity.RxManageActivity;
import com.example.boboyuwu.zhihunews.R;

/**
 * Created by wubo on 2017/6/10.
 */

public class SplashActivity <P extends BaseViewManager>extends RxManageActivity <P>{

    private TextView mLogoTv;

    @Override
    protected int getLayout() {
        return R.layout.activity_splash;
    }


    @Override
    protected void initInject() {
        getActivityComponent().injectActivity(this);
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        doAnimation();
    }

    private void doAnimation() {
        PropertyValuesHolder alpha = PropertyValuesHolder.ofFloat("alpha", 0.3f, 1f);
        PropertyValuesHolder scaleX = PropertyValuesHolder.ofFloat("scaleX", 0.3f, 1f);
        PropertyValuesHolder scaleY = PropertyValuesHolder.ofFloat("scaleY", 0.3f, 1f);
        ObjectAnimator objectAnimator = ObjectAnimator.ofPropertyValuesHolder(mLogoTv, alpha, scaleX, scaleY);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setTarget(objectAnimator);
        animatorSet.setInterpolator(new AccelerateInterpolator());
        animatorSet.setDuration(2000);
        animatorSet.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                MainActivity.startMainActivity(SplashActivity.this,null);
                finish();
            }

            @Override
            public void onAnimationCancel(Animator animator) {

            }

            @Override
            public void onAnimationRepeat(Animator animator) {

            }
        });
        animatorSet.start();
    }

    private void findViews() {
        mLogoTv = getView(R.id.logo_tv);
    }
}
