package com.boboyuwu.xnews.mvp.presenter;

import com.boboyuwu.xnews.common.utils.RxUtil;
import com.boboyuwu.xnews.mvp.model.HomePageNewsModel;
import com.boboyuwu.xnews.mvp.view.HomePageView;

import javax.inject.Inject;

import io.reactivex.functions.Consumer;
import okhttp3.ResponseBody;

/**
 * Created by wubo on 2017/8/28.
 */

public class HomePageNewsPresenter extends BasePresenter <HomePageView>{

    /**由于传送MVP模式中,P作为M、V层桥梁,需要接收V对象,new出M对象，M跟V层是不接触的这样的好处在于
     * M层一般是请求接口,而P层做得到的接口数据处理然后将处理的数据传给V,解耦之后,就算请求接口方式整体改变
     * 后也不需要去Activity也就是V里改动,也不需要在P里改动,只需要更改M层就可以
     */
    HomePageNewsModel mHomePageNewsModel;
    @Inject
    public HomePageNewsPresenter(){
        mHomePageNewsModel = new HomePageNewsModel();
    }

    /**
     * 获取首页新闻列表
     * */
    public void getHomePageNewsList(String channelType, String channelId, String pageIndex){
        addDispose(mHomePageNewsModel.getHomePageNewsList(channelType,channelId,pageIndex)
                .compose(RxUtil.<ResponseBody>schedulerOnIoThread())
                .subscribe(new Consumer<ResponseBody>() {
            @Override
            public void accept(ResponseBody headLineNews) throws Exception {

                
            }
        }));

    }

}
