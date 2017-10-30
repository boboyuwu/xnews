package com.boboyuwu.xnews.mvp.view;

import com.boboyuwu.xnews.beans.VideoNews.VideoNewsBean;

import java.util.List;

/**
 * Created by wubo on 2017/9/29.
 */

public interface VideoView extends BaseView {
    void onLoadVideoNewsList(List<VideoNewsBean> videoNewses);
    void onLoadVideoNewsMoreList(List<VideoNewsBean> videoNewses);
}
