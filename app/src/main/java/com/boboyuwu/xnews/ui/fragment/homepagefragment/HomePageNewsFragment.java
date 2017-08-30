package com.boboyuwu.xnews.ui.fragment.homepagefragment;

import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TableLayout;

import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.ChannelNewsBean;
import com.boboyuwu.xnews.mvp.model.helper.GreenDaoHelper;
import com.boboyuwu.xnews.mvp.presenter.HomePageNewsPresenter;
import com.boboyuwu.xnews.ui.fragment.basefragment.SupportToolBarFragment;
import com.example.boboyuwu.zhihunews.R;

import java.util.Arrays;
import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 */

public class HomePageNewsFragment extends SupportToolBarFragment<HomePageNewsPresenter> {

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
        setToolBarColor(getResources().getColor(R.color.spark_orange));
    }

    @Override
    protected void init() {
        super.init();
        findViews();
        initNewsChannelTab();
    }

    /**
     * 初始化News频道标签
     * */
    private void initNewsChannelTab() {
        GreenDaoHelper greenDaoHelper = NewsApplication.getAppComponent().getGreenDaoHelper();
        List<ChannelNewsBean> channel = greenDaoHelper.getChannel();
        if(channel==null){
            List<String> channelName = Arrays.asList(getResources().getStringArray(R.array.news_channel_name_static));
            List<String> channelId = Arrays.asList(getResources().getStringArray(R.array.news_channel_id_static));
            for (int i = 0; i < channelName.size(); i++) {
                ChannelNewsBean channelNewsBean = new ChannelNewsBean();
                channelNewsBean.setChannelName(channelName.get(i));
                channelNewsBean.setChannelId(channelId.get(i));
                if(TextUtils.equals(channelId.get(i),"T1348647909107")){
                    channelNewsBean.setChannelType(ChannelNewsBean.HEADLINE);
                }else if(TextUtils.equals(channelId.get(i),"5YyX5Lqs")){
                    channelNewsBean.setChannelType(ChannelNewsBean.HOUSE);
                }else{
                    channelNewsBean.setChannelType(ChannelNewsBean.OTHER);
                }
                channelNewsBean.setIsFixChannel(true);
            }
        }
    }

    private void findViews() {
        mTableyaout = getView(R.id.tableyaout);
        mAddIv = getView(R.id.add_iv);
    }
}
