package com.boboyuwu.xnews.mvp.view;

import com.boboyuwu.xnews.beans.NewsDetail;

/**
 * Created by wubo on 2017/9/25.
 */

public interface NewsDetailView extends BaseView{
    void onLoadNewsDetail(NewsDetail newsDetail);
}
