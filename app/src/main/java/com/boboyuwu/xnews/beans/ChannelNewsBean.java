package com.boboyuwu.xnews.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Transient;

import java.io.Serializable;

/**
 * Created by wubo on 2017/8/29.
 * 封装新闻频道,视频频道
 */

@Entity
public class ChannelNewsBean implements Serializable {

    @Transient
    public static final String HEADLINE="headline";
    @Transient
    public static final String HOUSE="house";
    @Transient
    public static final String OTHER="list";
    @Transient
    private static final long serialVersionUID = 1L;
    @Id
    private long Id;
    private String channelName;
    private String channelId;
    private String channelType;
    private boolean isFixChannel;
    public boolean getIsFixChannel() {
        return this.isFixChannel;
    }
    public void setIsFixChannel(boolean isFixChannel) {
        this.isFixChannel = isFixChannel;
    }
    public String getChannelType() {
        return this.channelType;
    }
    public void setChannelType(String channelType) {
        this.channelType = channelType;
    }
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
    @Generated(hash = 1380931854)
    public ChannelNewsBean(long Id, String channelName, String channelId,
            String channelType, boolean isFixChannel) {
        this.Id = Id;
        this.channelName = channelName;
        this.channelId = channelId;
        this.channelType = channelType;
        this.isFixChannel = isFixChannel;
    }
    @Generated(hash = 796839396)
    public ChannelNewsBean() {
    }


}
