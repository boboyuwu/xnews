package com.boboyuwu.common.basequickadapter;

/**
 * Created by wubo on 16/6/1.
 */
public interface MultiItemTypeSupport<T> {

    int getLayoutId(int viewType);

    int getItemViewType(int position, T t);

}
