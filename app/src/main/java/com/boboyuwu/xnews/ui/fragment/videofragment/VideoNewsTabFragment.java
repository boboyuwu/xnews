package com.boboyuwu.xnews.ui.fragment.videofragment;

import com.boboyuwu.xnews.mvp.presenter.VideoPresenter;
import com.boboyuwu.xnews.ui.fragment.basefragment.LazyFragment;
import com.example.boboyuwu.zhihunews.R;

/**
 * Created by wubo on 2017/9/29.
 */

public class VideoNewsTabFragment  extends LazyFragment<VideoPresenter>{


    @Override
    protected void initInject() {
        getFragmentComponent().injectFragment(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_video_news_list;
    }

    @Override
    protected void onLazyLoadData() {

    }
}
