package com.boboyuwu.common.util;

import android.widget.Toast;

import com.boboyuwu.common.app.BaseApplication;


/**
 * Created by wubo on 2017/6/14.
 */

public class ToastUtil {

    public static void showToast(String text){
        Toast.makeText(BaseApplication.getApplication(),text, Toast.LENGTH_SHORT).show();
    }
}
