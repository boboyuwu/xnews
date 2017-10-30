package com.boboyuwu.xnews.api;

import com.boboyuwu.xnews.beans.PrettyPhotos;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by wubo on 2017/9/28.
 */

public interface PrettyPhotoApi {

    /**
     * 美女列表接口
     */
    public static final String PHOTOS_HOST = "http://gank.io/api/";


    @GET("data/福利/20/{pageIndex}")
    public Flowable<PrettyPhotos> getPrettyPhotoList(@Path("pageIndex") String pageIndex);
}
