package com.boboyuwu.xnews.mvp.model.helper;

import com.boboyuwu.xnews.beans.ChannelNewsBean;

import java.util.List;

/**
 * Created by wubo on 2017/6/15.
 *  操作数据库Helper接口,具体实现是GreendaoHelper
 *  规范所有数据库操作
 */

public interface DBHelper {

    //插入和查询当前所有频道
    void setChannelList(List<ChannelNewsBean> list);
    void setChannel(ChannelNewsBean channel);
    void clearAllChannel();
    List<ChannelNewsBean> getChannel();

}
