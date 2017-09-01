package com.boboyuwu.xnews.mvp.view;


import com.boboyuwu.xnews.beans.HeadLineNews.T1348647909107Bean;

import java.util.List;

/**
 * Created by wubo on 2017/8/28.
 */

public interface HomePageView extends BaseView{

    void onLoadNewsList(List<T1348647909107Bean> list);
}
