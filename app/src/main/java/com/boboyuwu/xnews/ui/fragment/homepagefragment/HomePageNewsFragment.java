package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import android.widget.ImageView;
import android.widget.TableLayout;

import com.boboyuwu.xnews.mvp.presenter.HomePageNewsTabPresenter;
import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;
import com.example.boboyuwu.zhihunews.R;

/**
 * Created by wubo on 2017/8/28.
 */

public class HomePageNewsFragment extends SupportToolBarFragment<HomePageNewsTabPresenter> {

    private TableLayout mTableyaout;
    private ImageView mAddIv;

    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_homepage_news;
    }

    @Override
    protected void setToolBar() {
        setToolBarColor(getResources().getColor(R.color.spark_orange));
        setToolBarTitle("XNews新闻");
    }

    @Override
    protected void init() {
        super.init();
        findViews();
    }

    private void findViews() {
        mTableyaout = getView(R.id.tableyaout);
        mAddIv = getView(R.id.add_iv);
    }
}
