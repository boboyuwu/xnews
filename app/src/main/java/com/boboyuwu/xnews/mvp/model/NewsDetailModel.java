package com.boboyuwu.xnews.mvp.model;

import com.boboyuwu.xnews.api.HomeNewsApi;
import com.boboyuwu.xnews.api.NewsDetailPhotoApi;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.NewsDetail;

import java.util.Map;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;

/**
 * Created by wubo on 2017/9/25.
 */

public class NewsDetailModel{

    HomeNewsApi mHomeNewsApi;
    NewsDetailPhotoApi mNewsDetailPhotoApi;
    public NewsDetailModel(){
        mHomeNewsApi= NewsApplication.getAppComponent().getHomeNewsApi();
        mNewsDetailPhotoApi=NewsApplication.getAppComponent().getNewsDetailPhotoApi();
    }

    public Flowable<Map<String, NewsDetail>> getNewsDetail(String postId){
        return mHomeNewsApi.getNewDetail(postId);
    }

    public Flowable <ResponseBody> getNewsBodyHtmlPhoto(String photoPath){
            return mNewsDetailPhotoApi.getNewsBodyHtmlPhoto(photoPath);
    }

}
