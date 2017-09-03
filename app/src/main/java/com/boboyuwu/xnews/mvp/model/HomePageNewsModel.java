package com.boboyuwu.xnews.mvp.model;

import com.boboyuwu.xnews.api.HomeNewsApi;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by wubo on 2017/8/30.
 */

public class HomePageNewsModel {
    HomeNewsApi mHomeNewsApi;
    public HomePageNewsModel(){
        mHomeNewsApi= NewsApplication.getAppComponent().getHomeNewsApi();
    }

    /**
     * 获取首页新闻列表
     * */
    public Flowable<Map<String, List<HeadLineNewsBean>>>getHomePageNewsList(String channelType, String channelId, String pageIndex){
        return mHomeNewsApi.getHomePageNewsList(channelType,channelId,pageIndex);
    }
}
