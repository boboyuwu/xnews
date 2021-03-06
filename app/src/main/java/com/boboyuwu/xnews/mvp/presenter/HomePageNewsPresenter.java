package com.boboyuwu.xnews.mvp.presenter;

import android.text.TextUtils;

import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.common.constants.Constants;
import com.boboyuwu.xnews.common.utils.RxSubscriber;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.common.utils.RxUtil;
import com.boboyuwu.xnews.greendao.data.ChannelNewsData;
import com.boboyuwu.xnews.mvp.model.HomePageNewsModel;
import com.boboyuwu.xnews.mvp.view.HomePageView;

import java.util.List;
import java.util.Map;

import javax.inject.Inject;

/**
 * Created by wubo on 2017/8/28.
 */

public class HomePageNewsPresenter extends BasePresenter<HomePageView> {

    /**
     * 由于传送MVP模式中,P作为M、V层桥梁,需要接收V对象,new出M对象，M跟V层是不接触的这样的好处在于
     * M层一般是请求接口,而P层做得到的接口数据处理然后将处理的数据传给V,解耦之后,就算请求接口方式整体改变
     * 后也不需要去Activity也就是V里改动,也不需要在P里改动,只需要更改M层就可以
     */

    HomePageNewsModel mHomePageNewsModel;

    @Inject
    public HomePageNewsPresenter() {
        mHomePageNewsModel = new HomePageNewsModel();
    }



    /**
     * 设置频道
     *
     * */

    public void setChannelList(List<ChannelNewsData> list){
        mGreenDaoHelper.setChannelList(list);
    }


    /**
     *   获取频道
     *
     * */
    public List<ChannelNewsData>  getChannelList(){
        return mGreenDaoHelper.getChannelList();
    }


    /**
     * 首页新闻列表分页加载
     */
    public void getHomePageMoreNewsList(final String channelType, final String channelId, final String pageIndex) {
        addDispose(mHomePageNewsModel.getHomePageNewsList(channelType, channelId, pageIndex)
                .compose(RxUtil.<Map<String, List<HeadLineNewsBean>>>schedulerFlowableOnIoThread())
                .subscribeWith(new RxSubscriber<Map<String, List<HeadLineNewsBean>>>(mBaseView, RxSubscriberState.builder()
                        .setLoadMode(RxSubscriberState.MORE_LOAD)
                        .build()) {
                    @Override
                    public void onNext(Map<String, List<HeadLineNewsBean>> headLineNews) {
                        mBaseView.onLoadMoreNewsList(headLineNews.get(channelId));
                    }

                }));

    }

    /**
     * 下拉刷新或者第一次加载
     */
    public void getHomePageNewsList(final String channelType, final String channelId, String pageIndex) {
        addDispose(mHomePageNewsModel.getHomePageNewsList(channelType, channelId, pageIndex)
                .compose(RxUtil.<Map<String, List<HeadLineNewsBean>>>schedulerFlowableOnIoThread())
                .subscribeWith(new RxSubscriber<Map<String, List<HeadLineNewsBean>>>(mBaseView, RxSubscriberState.builder()
                        .setLoadMode(RxSubscriberState.LOAD)
                        .build()) {
                    @Override
                    public void onNext(Map<String, List<HeadLineNewsBean>> headLineNews) {
                        if (TextUtils.equals(channelId, Constants.HOUSE_ID)) {
                            mBaseView.onLoadNewsList(headLineNews.get("北京"));
                            return;
                        }
                        mBaseView.onLoadNewsList(headLineNews.get(channelId));
                    }
                }));
    }


    /**
     *     首次从缓存中加载
     */
    public  void getHomePageNewsListFromCache(String channelId){
        mBaseView.onLoadNewsListFromCache(getHomePageNewsListFromCache(channelId,0));
    }




    /**
     * 分页从缓存加载
     */
    private List<HeadLineNewsBean> getHomePageNewsListFromCache(String channelId, int index) {
        List<HeadLineNewsBean> headLineNewsBeanList = mGreenDaoHelper.getHeadLineNewsBeanList(channelId);
        if(index==0){
            return headLineNewsBeanList;
        }else{
            return headLineNewsBeanList.subList(index-20, index );
        }

    }



    public void setHomePageNewsListToCache(String channelId,List<HeadLineNewsBean> list,int index){
        mGreenDaoHelper.setHeadLineNewsBeanList(channelId,list,index);
    }



}
