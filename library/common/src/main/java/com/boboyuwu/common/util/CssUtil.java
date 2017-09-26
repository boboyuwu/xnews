package com.boboyuwu.common.util;

/**
 * Created by wubo on 2017/9/26.
 */

public class CssUtil {

    public static String getWebViewDayCssStyle() {
        return "<style type='text/css'>"
                // +"@font-face{ font-family: myface; src:url('file:///android_asset/fonts/fzzdx.ttf');}"
                + "img{border:0; margin:0;max-width:100%;width:100%;text-align:center;vertical-align:middle;overflow:hidden;}"
                + "html{width:100%;}"
                + "body{padding-top:8px;padding-left:15px;padding-right:15px;margin:0px;font-family:myface;line-height:180%;}"
                + "p{width:100%; word-wrap:break-word;}"  // '微软雅黑'
                + "tap{width: 200px; border:#00468C solid 5px;}"
                + "a:link {text-decoration:none;}"
                + "a:hover {text-decoration:none;}"
                + "a:active {text-decoration:none;}"
                + "a:visited {text-decoration:none;}</style>";
    }

}
