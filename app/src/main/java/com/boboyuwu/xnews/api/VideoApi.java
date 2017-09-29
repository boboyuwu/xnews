package com.boboyuwu.xnews.api;

import com.boboyuwu.xnews.beans.VideoNews;

import java.util.Map;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wubo on 2017/9/29.
 */

public interface VideoApi {

    @GET("nc/video/list/{type}/n/{pageIndex}-20.html")
    public Flowable<Map<String, VideoNews>> getVideoList(@Path("type") String type, @Path("pageIndex") String pageIndex);

}
