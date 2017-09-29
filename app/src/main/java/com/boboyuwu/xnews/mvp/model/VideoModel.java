package com.boboyuwu.xnews.mvp.model;

import com.boboyuwu.xnews.api.VideoApi;
import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.VideoNews;

import java.util.Map;

import io.reactivex.Flowable;

/**
 * Created by wubo on 2017/9/29.
 */

public class VideoModel {
    private final VideoApi mVideoApi;

    public VideoModel() {
        mVideoApi = NewsApplication.getAppComponent().getVideoApi();
    }
    public Flowable<Map<String, VideoNews>> getVideoNewsList(String type, String pageIndex) {
        return mVideoApi.getVideoList(type,pageIndex);
    }

}
