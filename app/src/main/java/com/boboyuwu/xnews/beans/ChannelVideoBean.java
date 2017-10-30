package com.boboyuwu.xnews.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wubo on 2017/9/29.
 */

public class ChannelVideoBean implements Parcelable {

    private String channelName;
    private String channelId;

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.channelName);
        dest.writeString(this.channelId);
    }

    public ChannelVideoBean() {
    }

    protected ChannelVideoBean(Parcel in) {
        this.channelName = in.readString();
        this.channelId = in.readString();
    }

    public static final Creator<ChannelVideoBean> CREATOR = new Creator<ChannelVideoBean>() {
        @Override
        public ChannelVideoBean createFromParcel(Parcel source) {
            return new ChannelVideoBean(source);
        }

        @Override
        public ChannelVideoBean[] newArray(int size) {
            return new ChannelVideoBean[size];
        }
    };
}
