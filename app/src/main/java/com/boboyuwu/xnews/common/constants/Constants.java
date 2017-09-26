package com.boboyuwu.xnews.common.constants;

import com.boboyuwu.xnews.app.NewsApplication;

import java.io.File;

/**
 * Created by wubo on 2017/8/29.
 */

public class Constants {

    // 房产id
    public static final String HOUSE_ID = "5YyX5Lqs";
    // 头条id
    public static final String HEADLINE_ID = "T1348647909107";

    //OkHttp默认缓存存放路径
    public static final String PATH_DATA = NewsApplication.getApplication().getCacheDir().getAbsolutePath() + File.separator + "OKHttp";

    public static final String PATH_CACHE = PATH_DATA + File.separator +"NetCache";

}
