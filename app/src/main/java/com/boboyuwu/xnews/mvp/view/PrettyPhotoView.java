package com.boboyuwu.xnews.mvp.view;

import com.boboyuwu.xnews.beans.PrettyPhotos.ResultsBean;

import java.util.List;

/**
 * Created by wubo on 2017/9/28.
 */

public interface PrettyPhotoView extends BaseView{

    void onLoadPrettyPhotoList(List<ResultsBean> results);

    void onLoadMorePrettyPhotoList(List<ResultsBean> results);
}
