package com.boboyuwu.xnews.mvp.model.helper;

import android.database.sqlite.SQLiteDatabase;

import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.ChannelNewsBean;
import com.boboyuwu.xnews.greendao.gen.ChannelNewsBeanDao;
import com.boboyuwu.xnews.greendao.gen.DaoMaster;
import com.boboyuwu.xnews.greendao.gen.DaoSession;

import java.util.List;

/**
 * Created by wubo on 2017/8/29.
 */

public class GreenDaoHelper implements DBHelper{

    private DaoSession mDaoSession;

    public GreenDaoHelper(){
        // 通过 DaoMaster 的内部类 DevOpenHelper，你可以得到一个便利的 SQLiteOpenHelper 对象。
        // 可能你已经注意到了，你并不需要去编写「CREATE TABLE」这样的 SQL 语句，因为 greenDAO 已经帮你做了。
        // 注意：默认的 DaoMaster.DevOpenHelper 会在数据库升级时，删除所有的表，意味着这将导致数据的丢失。
        // 所以，在正式的项目中，你还应该做一层封装，来实现数据库的安全升级。
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(NewsApplication.getApplication(), "notes-db", null);
        SQLiteDatabase sd = helper.getWritableDatabase();
        // 注意：该数据库连接属于 DaoMaster，所以多个 Session 指的是相同的数据库连接。
        DaoMaster daoMaster = new DaoMaster(sd);
        mDaoSession = daoMaster.newSession();
    }


    /**
     * 存放当前最新的添加频道List
     * */
    @Override
    public void setChannelList(List<ChannelNewsBean> list) {
        clearAllChannel();
        ChannelNewsBeanDao channelNewsBeanDao = mDaoSession.getChannelNewsBeanDao();
        for (ChannelNewsBean channelNewsBean : list) {
            channelNewsBeanDao.insert(channelNewsBean);
        }
    }

    @Override
    public void setChannel(ChannelNewsBean channel) {
        ChannelNewsBeanDao channelNewsBeanDao = mDaoSession.getChannelNewsBeanDao();
        channelNewsBeanDao.insertOrReplace(channel);
    }


    @Override
    public List<ChannelNewsBean> getChannelList() {
        ChannelNewsBeanDao channelNewsBeanDao = mDaoSession.getChannelNewsBeanDao();
        return channelNewsBeanDao.queryBuilder().build().list();
    }

    @Override
    public void clearAllChannel(){
        ChannelNewsBeanDao mChannelNewsBeanDao = mDaoSession.getChannelNewsBeanDao();
        mChannelNewsBeanDao.deleteAll();
    }



}
