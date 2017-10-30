package com.boboyuwu.xnews.app.helper;

import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.greendao.data.ChannelNewsData;

import java.util.List;

/**
 * Created by wubo on 2017/6/15.
 *  操作数据库Helper接口,   具体实现是 GreendaoHelper
 *  规范所有数据库操作
 */

public interface DBHelper {

    //插入和查询当前所有频道
    void setChannelList(List<ChannelNewsData> list);
    void setChannel(ChannelNewsData channel);
    void clearAllChannel();
    void clearAllNews();
    List<ChannelNewsData> getChannelList();

    //插入和查询新闻列表
    void setHeadLineNewsBeanList(String channelId,List<HeadLineNewsBean> list,int index);
    List<HeadLineNewsBean> getHeadLineNewsBeanList(String channelId);

}
