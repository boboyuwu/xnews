package com.boboyuwu.xnews.api;

import io.reactivex.Flowable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Url;

/**
 * Created by wubo on 2017/9/26.
 */

public interface NewsDetailPhotoApi {
    /**
     * 详情中图片接口
     * */
    public static final String NEWS_PHOTO_HOST = "http://kaku.com/";


    @GET
    Flowable <ResponseBody> getNewsBodyHtmlPhoto(@Url String photoPath);

}
