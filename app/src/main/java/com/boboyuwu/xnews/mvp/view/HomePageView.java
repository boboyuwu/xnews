package com.boboyuwu.xnews.mvp.view;

import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;

import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 */

public interface HomePageView extends BaseView{

    void onLoadNewsList(List<HeadLineNewsBean> list);
}
