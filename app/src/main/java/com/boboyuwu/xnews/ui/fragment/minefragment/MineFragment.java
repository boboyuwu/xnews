package com.boboyuwu.xnews.ui.fragment.minefragment;

import android.view.View;

import com.boboyuwu.common.util.RxBus;
import com.boboyuwu.common.util.RxBusEventKeys;
import com.boboyuwu.common.widget.SwitchView;
import com.boboyuwu.common.widget.SwitchView.OnStateChangedListener;
import com.boboyuwu.xnews.app.helper.DayNightHelper;
import com.boboyuwu.xnews.common.widget.ItemWidgetView;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;
import com.example.boboyuwu.zhihunews.R;
import com.orhanobut.logger.Logger;

/**
 * Created by wubo on 2017/8/28.
 * 我
 */

public class MineFragment extends SupportToolBarFragment<HomePageNewsPresenter> implements OnStateChangedListener {

    private ItemWidgetView mDayNightItemWidgetView;
    private SwitchView mSwitchView;

    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_mine;
    }



    @Override
    protected void setToolBar() {
        setToolBarTitle("XNews我");
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initView();
    }

    private void initView() {
        mSwitchView.setOpened(mDayNightHelper.getMode());
    }

    @Override
    protected void setListener() {
        mSwitchView.setOnStateChangedListener(this);
    }

    private void findViews() {
        mDayNightItemWidgetView = getView(R.id.dayNightItemWidgetView);
        mSwitchView = mDayNightItemWidgetView.getSwitchView();
    }

    @Override
    public void toggleToOn(View view) {
        Logger.i("toggleToOn");
        mSwitchView.setOpened(true);
        mDayNightHelper.setDayState(DayNightHelper.Night_MODE);
        mDayNightHelper.setDayMode();
        RxBus.get().post(RxBusEventKeys.RECREATE,true);
    }

    @Override
    public void toggleToOff(View view) {
        Logger.i("toggleToOff");
        mSwitchView.setOpened(false);
        mDayNightHelper.setDayState(DayNightHelper.LIGHT_MODE);
        mDayNightHelper.setDayMode();
        RxBus.get().post(RxBusEventKeys.RECREATE,true);
    }
}
