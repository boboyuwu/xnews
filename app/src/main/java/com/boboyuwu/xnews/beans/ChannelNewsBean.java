package com.boboyuwu.xnews.beans;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
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


    /**
     * 是title还是channel
     * */
    @Transient
    public static final int TYPE_TITLE=1;
    @Transient
    public static final int TYPE_CHANNEL=2;
    /**
     * 是我的频道还是更多频道
     * */
    @Transient
    public static final int CHANNEL_TYPE_MINE=11;
    @Transient
    public static final int CHANNEL_TYPE_MORE=22;

  /*  @Id
    private long Id;*/
    private String channelName;
    private String channelId;
    private String channelType;
    private boolean isFixChannel;
    private int type;
    private int channelManagerType;
    public int getChannelManagerType() {
        return this.channelManagerType;
    }
    public void setChannelManagerType(int channelManagerType) {
        this.channelManagerType = channelManagerType;
    }
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
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
    @Generated(hash = 1639382036)
    public ChannelNewsBean(String channelName, String channelId,
            String channelType, boolean isFixChannel, int type,
            int channelManagerType) {
        this.channelName = channelName;
        this.channelId = channelId;
        this.channelType = channelType;
        this.isFixChannel = isFixChannel;
        this.type = type;
        this.channelManagerType = channelManagerType;
    }
    @Generated(hash = 796839396)
    public ChannelNewsBean() {
    }


   


}
