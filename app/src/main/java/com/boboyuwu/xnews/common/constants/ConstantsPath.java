package com.boboyuwu.xnews.common.constants;

import com.boboyuwu.xnews.app.NewsApplication;

import java.io.File;

/**
 * Created by boboyuwu on 2017/8/29.
 */

public class ConstantsPath {
    public static final String PATH_DATA = NewsApplication.getApplication().getCacheDir().getAbsolutePath() + File.separator + "OKHttp";


    public static final String PATH_CACHE = PATH_DATA + File.separator +"NetCache";

}
