package com.boboyuwu.xnews.greendao.data;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

import java.io.Serializable;

/**
 * Created by wubo on 2017/9/29.
 */


@Entity
public class HeadLineNewsData implements Serializable{


    @Id
    Long id;
    String channelId;
    String dataJson;


    public String getDataJson() {
        return this.dataJson;
    }

    public void setDataJson(String dataJson) {
        this.dataJson = dataJson;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChannelId() {
        return this.channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Generated(hash = 1201354003)
    public HeadLineNewsData(Long id, String channelId, String dataJson) {
        this.id = id;
        this.channelId = channelId;
        this.dataJson = dataJson;
    }

    @Generated(hash = 569419294)
    public HeadLineNewsData() {
    }
}
