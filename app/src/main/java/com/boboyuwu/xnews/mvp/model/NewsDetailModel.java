package com.boboyuwu.xnews.mvp.model;

import com.boboyuwu.xnews.api.HomeNewsApi;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.NewsDetail;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by wubo on 2017/9/25.
 */

public class NewsDetailModel{

    HomeNewsApi mHomeNewsApi;
    public NewsDetailModel(){
        mHomeNewsApi= NewsApplication.getAppComponent().getHomeNewsApi();
    }

    public Flowable<Map<String, NewsDetail>> getNewsDetail(String postId){
        return mHomeNewsApi.getNewDetail(postId);
    }


}
