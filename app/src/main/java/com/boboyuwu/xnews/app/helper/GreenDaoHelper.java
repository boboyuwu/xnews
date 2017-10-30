package com.boboyuwu.xnews.app.helper;

import android.database.sqlite.SQLiteDatabase;

import com.boboyuwu.xnews.app.NewsApplication;
import com.boboyuwu.xnews.beans.HeadLineNews;
import com.boboyuwu.xnews.beans.HeadLineNews.HeadLineNewsBean;
import com.boboyuwu.xnews.greendao.data.ChannelNewsData;
import com.boboyuwu.xnews.greendao.data.HeadLineNewsData;
import com.boboyuwu.xnews.greendao.gen.ChannelNewsDataDao;
import com.boboyuwu.xnews.greendao.gen.DaoMaster;
import com.boboyuwu.xnews.greendao.gen.DaoSession;
import com.boboyuwu.xnews.greendao.gen.HeadLineNewsDataDao;
import com.boboyuwu.xnews.greendao.gen.HeadLineNewsDataDao.Properties;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import java.util.ArrayList;
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
    public void setChannelList(List<ChannelNewsData> list) {
        clearAllChannel();
        ChannelNewsDataDao channelNewsDataDao = mDaoSession.getChannelNewsDataDao();
        for (ChannelNewsData channelNewsData : list) {
            channelNewsDataDao.insert(channelNewsData);
        }
    }

    @Override
    public void setChannel(ChannelNewsData channel) {
        ChannelNewsDataDao channelNewsDataDao = mDaoSession.getChannelNewsDataDao();
        channelNewsDataDao.insertOrReplace(channel);
    }


    @Override
    public List<ChannelNewsData> getChannelList() {
        ChannelNewsDataDao channelNewsDataDao = mDaoSession.getChannelNewsDataDao();
        return channelNewsDataDao.queryBuilder().build().list();
    }



    @Override
    public void clearAllChannel(){
        ChannelNewsDataDao mChannelNewsDataDao= mDaoSession.getChannelNewsDataDao();
        mChannelNewsDataDao.deleteAll();
    }


    /**
     *  存放首页新闻频道列表缓存数据
     */

    @Override
    public void setHeadLineNewsBeanList(String channelId,List<HeadLineNewsBean> list,int index) {
        //clearAllNews();
        HeadLineNews headLineNews =null;

        String str = "";

        HeadLineNewsDataDao mHeadLineNewsDataDao= mDaoSession.getHeadLineNewsDataDao();

        HeadLineNewsData headLineNewsData = mHeadLineNewsDataDao.queryBuilder().where(Properties.ChannelId.eq(channelId)).unique();



        List<HeadLineNewsBean> newList = new ArrayList<>();
        if(null!=headLineNewsData){
            String dataJson = headLineNewsData.getDataJson();
            headLineNews= new Gson().fromJson(dataJson, HeadLineNews.class);
            List<HeadLineNewsBean> headLineNewsList = headLineNews.getHeadLineNewsList();
            headLineNewsList = headLineNewsList.subList(0, index);
            newList.addAll(headLineNewsList);
            newList.addAll(list);
            headLineNews.setHeadLineNewsList(newList);
            str=new Gson().toJson(headLineNews);
            headLineNewsData.setDataJson(str);
            headLineNewsData.setChannelId(channelId);
            mHeadLineNewsDataDao.update(headLineNewsData);
        }else{
            headLineNewsData=new HeadLineNewsData();
            headLineNews=new HeadLineNews();
            headLineNews.setHeadLineNewsList(list);
            str=new Gson().toJson(headLineNews);
            headLineNewsData.setDataJson(str);
            headLineNewsData.setChannelId(channelId);
            mHeadLineNewsDataDao.insert(headLineNewsData);
        }

    }

    @Override
    public List<HeadLineNewsBean> getHeadLineNewsBeanList(String channelId) {
        String dataJson="";
        HeadLineNewsDataDao mHeadLineNewsDataDao= mDaoSession.getHeadLineNewsDataDao();
        HeadLineNewsData data = mHeadLineNewsDataDao.queryBuilder().where(Properties.ChannelId.eq(channelId)).unique();

        if(data!=null){
            dataJson = data.getDataJson();

        }

        HeadLineNews headLineNews = new Gson().fromJson(dataJson, HeadLineNews.class);
        Logger.e("getHeadLineNewsBeanList:"+dataJson);
        return headLineNews==null?new ArrayList<HeadLineNewsBean>():headLineNews.getHeadLineNewsList();

    }


    @Override
    public void clearAllNews(){
        HeadLineNewsDataDao mHeadLineNewsDataDao= mDaoSession.getHeadLineNewsDataDao();
        mHeadLineNewsDataDao.deleteAll();
    }



}
