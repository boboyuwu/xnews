package com.boboyuwu.xnews.mvp.presenter;

import com.boboyuwu.xnews.beans.VideoNews;
import com.boboyuwu.xnews.common.utils.RxSubscriber;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.common.utils.RxUtil;
import com.boboyuwu.xnews.mvp.model.VideoModel;
import com.boboyuwu.xnews.mvp.view.VideoView;

import java.util.Map;

import javax.inject.Inject;

/**
 * Created by wubo on 2017/9/29.
 */

public class VideoPresenter extends BasePresenter<VideoView>{

    private VideoModel mVideoModel;

    @Inject
    public VideoPresenter() {
        mVideoModel = new VideoModel();
    }

    /**
     *  首次进来加载视频列表
     * */
    public void getVideoNewsList(final String type, String pageIndex) {
        addDispose(mVideoModel.getVideoNewsList(type,pageIndex)
        .compose(RxUtil.<Map<String,VideoNews>>schedulerFlowableOnIoThread())
        .subscribeWith(new RxSubscriber<Map<String, VideoNews>>(mBaseView, RxSubscriberState.builder()
                .setLoadMode(RxSubscriberState.LOAD).build()) {
            @Override
            public void onNext(Map<String, VideoNews> stringVideoNewsMap) {
                mBaseView.onLoadVideoNewsList(stringVideoNewsMap.get(type).getVideoNewsBeanList());
            }
        }));
    }


    /**
     *  加载更多视频列表
     * */
    public void getVideoNewsMoreList(final String type, String pageIndex) {
        addDispose(mVideoModel.getVideoNewsList(type,pageIndex)
                .compose(RxUtil.<Map<String,VideoNews>>schedulerFlowableOnIoThread())
                .subscribeWith(new RxSubscriber<Map<String, VideoNews>>(mBaseView, RxSubscriberState.builder()
                        .setLoadMode(RxSubscriberState.MORE_LOAD).build()) {
                    @Override
                    public void onNext(Map<String, VideoNews> stringVideoNewsMap) {
                        mBaseView.onLoadVideoNewsMoreList(stringVideoNewsMap.get(type).getVideoNewsBeanList());
                    }
                }));
    }


}
