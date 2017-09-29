package com.boboyuwu.xnews.mvp.model;

import com.boboyuwu.xnews.api.PrettyPhotoApi;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.PrettyPhotos;

import io.reactivex.Flowable;

/**
 * Created by wubo on 2017/9/28.
 */

public class PrettyPhotoModel {

    private final PrettyPhotoApi mPrettyPhotoApi;

    public PrettyPhotoModel() {
        mPrettyPhotoApi = NewsApplication.getAppComponent().getPrettyPhotoApi();
    }

    public Flowable<PrettyPhotos> getPrettyPhotoNewsList(String pageIndex) {
        return mPrettyPhotoApi.getPrettyPhotoList(pageIndex);
    }



}
