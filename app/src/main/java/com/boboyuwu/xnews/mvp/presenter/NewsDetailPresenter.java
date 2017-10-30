package com.boboyuwu.xnews.mvp.presenter;

import com.boboyuwu.xnews.beans.NewsDetailBean;
import com.boboyuwu.xnews.common.utils.RxSubscriber;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.common.utils.RxUtil;
import com.boboyuwu.xnews.mvp.model.NewsDetailModel;
import com.boboyuwu.xnews.mvp.view.NewsDetailView;

import java.util.Map;

import javax.inject.Inject;

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
                .compose(RxUtil.<Map<String, NewsDetailBean>>schedulerFlowableOnIoThread())
                .subscribeWith(new RxSubscriber<Map<String, NewsDetailBean>>(mBaseView, RxSubscriberState.builder()
                        .setLoadMode(RxSubscriberState.LOAD).build()) {
                    @Override
                    public void onNext(Map<String, NewsDetailBean> detail) {
                        mBaseView.onLoadNewsDetail(detail.get(postId));
                    }
                }));
    }


}
