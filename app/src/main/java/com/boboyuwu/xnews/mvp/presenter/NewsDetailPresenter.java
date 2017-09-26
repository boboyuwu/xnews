package com.boboyuwu.xnews.mvp.presenter;

import com.boboyuwu.xnews.beans.NewsDetail;
import com.boboyuwu.xnews.common.utils.RxSubscriber;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.common.utils.RxUtil;
import com.boboyuwu.xnews.mvp.model.NewsDetailModel;
import com.boboyuwu.xnews.mvp.view.NewsDetailView;

import java.util.Map;

import javax.inject.Inject;

import okhttp3.ResponseBody;

/**
 * Created by wubo on 2017/9/25.
 * 获取详情页Presenter
 */

public class NewsDetailPresenter extends BasePresenter<NewsDetailView> {

    NewsDetailModel mNewsDetailModel;

    @Inject
    public NewsDetailPresenter() {
        mNewsDetailModel = new NewsDetailModel();
    }

    /**
     * 获取新闻详情界面
     */
    public void getNewsDetail(final String postId) {
        addDispose(mNewsDetailModel.getNewsDetail(postId)
                .compose(RxUtil.<Map<String, NewsDetail>>schedulerFlowableOnIoThread())
                .subscribeWith(new RxSubscriber<Map<String, NewsDetail>>(mBaseView, RxSubscriberState.builder()
                        .setLoadMode(RxSubscriberState.LOAD).build()) {
                    @Override
                    public void onNext(Map<String, NewsDetail> detail) {
                        mBaseView.onLoadNewsDetail(detail.get(postId));
                    }
                }));

    }


    public void getNewsBodyHtmlPhoto(String photoPath) {
        addDispose(mNewsDetailModel.getNewsBodyHtmlPhoto(photoPath).compose(RxUtil.<ResponseBody>schedulerFlowableOnIoThread())
                .subscribeWith(new RxSubscriber<ResponseBody>(mBaseView, RxSubscriberState.builder()
                        .setLoadMode(RxSubscriberState.LOAD).build()) {
                    @Override
                    public void onNext(ResponseBody responseBody) {
                       mBaseView.onLoadNewsBodyHtmlPhoto(responseBody);
                    }
                })
        );
    }

}
