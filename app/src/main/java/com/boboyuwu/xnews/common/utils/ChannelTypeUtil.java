package com.boboyuwu.xnews.common.utils;

import android.text.TextUtils;

import com.boboyuwu.xnews.beans.ChannelNewsBean;

/**
 * Created by wubo on 2017/9/20.
 * 用于区分Channel Type 类型的Util
 */

public class ChannelTypeUtil {

     public static String getChannelType(String channelId){
         String channelType="";
         if (TextUtils.equals(channelId, "T1348647909107")) {
             channelType=ChannelNewsBean.HEADLINE;
         } else if (TextUtils.equals(channelId, "5YyX5Lqs")) {
             channelType=ChannelNewsBean.HOUSE;
         } else {
             channelType=ChannelNewsBean.OTHER;
         }
         return channelType;
     }
}
