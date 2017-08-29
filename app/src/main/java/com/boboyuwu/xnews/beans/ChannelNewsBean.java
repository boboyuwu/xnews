package com.boboyuwu.xnews.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Created by wubo on 2017/8/29.
 * 存储所有新闻频道name id
 */

@Entity
public class ChannelNewsBean implements Serializable{

    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private long Id;
    private String channelName;
    private String channelId;


    public String getChannelId() {
        return this.channelId;
    }
    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }
    public String getChannelName() {
        return this.channelName;
    }
    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
    public long getId() {
        return this.Id;
    }
    public void setId(long Id) {
        this.Id = Id;
    }
    @Generated(hash = 1088852374)
    public ChannelNewsBean(long Id, String channelName, String channelId) {
        this.Id = Id;
        this.channelName = channelName;
        this.channelId = channelId;
    }
    @Generated(hash = 796839396)
    public ChannelNewsBean() {
    }


}
