package com.boboyuwu.common.util;

import android.content.Context;
import android.content.pm.ApplicationInfo;

/**
 * Created by wubo on 2017/9/24.
 */

public class ApkInfoUtil {
    /**
     * 是否debug模；是：true ；
     */
    public static boolean isApkDebugable(Context context) {
        try {
            ApplicationInfo info= context.getApplicationInfo();
            return (info.flags & ApplicationInfo.FLAG_DEBUGGABLE)!=0;
        } catch (Exception e) {

        }
        return false;
    }
}
