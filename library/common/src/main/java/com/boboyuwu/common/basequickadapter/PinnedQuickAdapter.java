package com.boboyuwu.common.basequickadapter;

import android.content.Context;

import com.pipikou.library.AdapterStick;

import java.util.List;

/**
 * Created by boboyuwu on 2017/8/29.
 * 支持吸顶的Adapter
 * 请实现isPinnedViewType()方法，如果有Header请重写getItemCount方法
 */

public abstract class PinnedQuickAdapter<T> extends QuickAdapter<T> implements AdapterStick<BaseAdapterHelper>{
    public PinnedQuickAdapter(Context context, int layoutResId) {
        super(context, layoutResId);
    }

    public PinnedQuickAdapter(Context context, int layoutResId, List<T> data) {
        super(context, layoutResId, data);
    }

    protected PinnedQuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, multiItemTypeSupport);
    }

    protected PinnedQuickAdapter(Context context, MultiItemTypeSupport<T> multiItemTypeSupport, List<T> data) {
        super(context, multiItemTypeSupport, data);
    }

    @Override
    protected void convert(BaseAdapterHelper helper, T item) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
