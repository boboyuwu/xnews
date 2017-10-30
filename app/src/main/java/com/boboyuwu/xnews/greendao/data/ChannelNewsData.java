package com.boboyuwu.xnews.greendao.data;

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
public class ChannelNewsData implements Serializable {

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

/*    @Id(autoincrement = true)*/
    /**
     *  这样直接设置id主键目前会造成相同数据排序插入无效,读取后还是原来的顺序,怀疑是GreenDao或者SQLite问题待排查
     * */
    @Id
    private Long id;
    private String channelName;
    private String channelId;
    //这个是请求参数中的频道类型  (headline、house、list)
    private String channelType;
    //是不是固定定死的五个频道
    private boolean isFixChannel;
    //这个type 是频道还是title
    private int type;
    //所属type是我的频道还是更多频道
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
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Generated(hash = 657031006)
    public ChannelNewsData(Long id, String channelName, String channelId,
            String channelType, boolean isFixChannel, int type,
            int channelManagerType) {
        this.id = id;
        this.channelName = channelName;
        this.channelId = channelId;
        this.channelType = channelType;
        this.isFixChannel = isFixChannel;
        this.type = type;
        this.channelManagerType = channelManagerType;
    }
    @Generated(hash = 630126987)
    public ChannelNewsData() {
    }
  

}
