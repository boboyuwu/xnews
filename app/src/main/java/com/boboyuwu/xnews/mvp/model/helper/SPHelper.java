package com.boboyuwu.xnews.mvp.model.helper;

import android.content.Context;
import android.content.SharedPreferences;

import com.boboyuwu.common.util.Constants;

import javax.inject.Inject;

/**
 * Created by wubo on 2017/6/15.
 */

public class SPHelper {
        private SharedPreferences mSp;

        @Inject
        public SPHelper(Context context){
            mSp=context.getSharedPreferences(Constants.SP,context.MODE_PRIVATE);
        }


}
