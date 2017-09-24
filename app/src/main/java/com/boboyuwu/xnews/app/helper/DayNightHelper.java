package com.boboyuwu.xnews.app.helper;

import android.support.v7.app.AppCompatDelegate;

import com.boboyuwu.common.util.SPUtils;
import com.boboyuwu.xnews.common.constants.Keys;

/**
 * Created by wubo on 2017/9/23.
 */

public class DayNightHelper {


    public static final int LIGHT_MODE = 1;
    public static final int Night_MODE = 2;
    private SPUtils mSpUtils;


    public DayNightHelper() {
        mSpUtils = SPUtils.getInstance(Keys.IS_NIGHT_MODE);
    }

    public boolean getMode() {
       return mSpUtils.getBoolean(Keys.IS_NIGHT_MODE,false);
    }

    public void setDayState(int state) {
        switch (state){
            case LIGHT_MODE:
                mSpUtils.put(Keys.IS_NIGHT_MODE,false,true);
                break;
            case Night_MODE:
                mSpUtils.put(Keys.IS_NIGHT_MODE,true,true);
                break;
        }
    }


    public void setDayMode() {
        AppCompatDelegate.setDefaultNightMode(getMode() ?
                AppCompatDelegate.MODE_NIGHT_YES : AppCompatDelegate.MODE_NIGHT_NO);
    }
}
