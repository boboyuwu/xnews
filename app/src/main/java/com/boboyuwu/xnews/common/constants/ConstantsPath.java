package com.boboyuwu.xnews.common.constants;

import com.boboyuwu.xnews.app.NewsApplication;

import java.io.File;

/**
 * Created by wubo on 2017/8/29.
 */

public class ConstantsPath {

    //OkHttp默认缓存存放路径
    public static final String PATH_DATA = NewsApplication.getApplication().getCacheDir().getAbsolutePath() + File.separator + "OKHttp";

    public static final String PATH_CACHE = PATH_DATA + File.separator +"NetCache";

}
