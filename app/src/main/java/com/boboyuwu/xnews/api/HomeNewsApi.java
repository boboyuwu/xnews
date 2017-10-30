package com.boboyuwu.xnews.api;

import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.beans.NewsDetailBean;

import java.util.List;
import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wubo on 2017/7/5.
 * <p>
 * 首页网络请求api
 */


public interface HomeNewsApi {
    //基类接口
    /**
     * 首页新闻列表接口
     */
    public static final String NEWS_HOST = "http://c.m.163.com/";


    /**
     * 获取新闻列表
     */
    @GET("nc/article/{channelType}/{channelId}/{pageIndex}-20.html")
    public Flowable<Map<String, List<HeadLineNewsBean>>>getHomePageNewsList(@Path("channelType") String channelType,
                                                                            @Path("channelId")String channelId,
                                                                            @Path("pageIndex")String pageIndex);


    /**
     * 获取新闻列表详情
     * */
    @GET("nc/article/{channelType}/{channelId}/{pageIndex}-20.html")
    public Flowable<Map<String, List<HeadLineNewsBean>>>getHomePageNewsDetail(@Path("channelType") String channelType,
                                                                            @Path("channelId")String channelId,
                                                                            @Path("pageIndex")String pageIndex);

    @GET("nc/article/{postId}/full.html")
    public Flowable<Map<String, NewsDetailBean>> getNewDetail(
            @Path("postId") String postId);
}
