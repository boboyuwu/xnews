package com.boboyuwu.xnews.mvp.view;

/**
 * Created by wubo on 2017/6/10.
 * 定义view的基类
 * 基本每个activity都会发生的行为
 */

public interface BaseView {


    void onSucess();
    void onSucess(String msg);
    void onError();
    void onError(String msg);
    void showDialog();
    void disMissDialog();

}
