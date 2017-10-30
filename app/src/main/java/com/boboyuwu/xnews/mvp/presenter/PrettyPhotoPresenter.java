package com.boboyuwu.xnews.mvp.presenter;

import com.boboyuwu.xnews.beans.PrettyPhotos;
import com.boboyuwu.xnews.common.utils.RxSubscriber;
import com.boboyuwu.xnews.common.utils.RxSubscriberState;
import com.boboyuwu.xnews.common.utils.RxUtil;
import com.boboyuwu.xnews.mvp.model.PrettyPhotoModel;
import com.boboyuwu.xnews.mvp.view.PrettyPhotoView;

import javax.inject.Inject;

/**
 * Created by wubo on 2017/9/28.
 */

public class PrettyPhotoPresenter extends BasePresenter<PrettyPhotoView> {

    private PrettyPhotoModel mPrettyPhotoModel;

    @Inject
    public PrettyPhotoPresenter() {
        mPrettyPhotoModel = new PrettyPhotoModel();
    }

    /**
     *  获取美女列表
     * */
    public void getPrettyPhotoNewsList(final String pageIndex) {
        addDispose(mPrettyPhotoModel.getPrettyPhotoNewsList(pageIndex)
                .compose(RxUtil.<PrettyPhotos>schedulerFlowableOnIoThread())
                .subscribeWith(new RxSubscriber<PrettyPhotos>(mBaseView,
                        RxSubscriberState.builder().setLoadMode(RxSubscriberState.LOAD).build()) {
                    @Override
                    public void onNext(PrettyPhotos prettyPhotos) {
                        mBaseView.onLoadPrettyPhotoList(prettyPhotos.getResults());
                    }
                })
        );
    }

    /**
     *  获取美女更多列表
     * */
    public void getPrettyPhotoMoreNewsList(final String pageIndex) {
        addDispose(mPrettyPhotoModel.getPrettyPhotoNewsList(pageIndex)
                .compose(RxUtil.<PrettyPhotos>schedulerFlowableOnIoThread())
                .subscribeWith(new RxSubscriber<PrettyPhotos>(mBaseView,
                        RxSubscriberState.builder().setLoadMode(RxSubscriberState.MORE_LOAD).build()) {
                    @Override
                    public void onNext(PrettyPhotos prettyPhotos) {
                        mBaseView.onLoadMorePrettyPhotoList(prettyPhotos.getResults());
                    }
                })
        );
    }

}
