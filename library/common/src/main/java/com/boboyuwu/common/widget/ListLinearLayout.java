package com.boboyuwu.common.widget;

import android.content.Context;
import android.database.DataSetObserver;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wubo on 2017/9/15.
 * 一个提供类似ListView这种可以设置Adapter的LinearLayout
 */

public class ListLinearLayout extends LinearLayout {

    private LinearAdapter mAdapter;

    private ListLinearLayoutDataObserver mListLinearLayoutDataObserver = new ListLinearLayoutDataObserver();

    public interface MultiItemTypeSupport<T> {
        int getLayoutId(int viewType);
        int getItemViewType(int position, T t);
    }

    public ListLinearLayout(Context context) {
        this(context, null);
    }

    public ListLinearLayout(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListLinearLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void setAdapter(LinearAdapter adapter) {
        mAdapter = adapter;

        setAdapterInternal();
    }

    private void setAdapterInternal() {
        if (mAdapter != null) {
            mAdapter.unregisterDataSetObserver(mListLinearLayoutDataObserver);
            mAdapter.registerDataSetObserver(mListLinearLayoutDataObserver);
        }

        if(getChildCount()>0){
            removeAllViews();
        }

        for (int i = 0; i < mAdapter.getCount(); i++) {
            View view = mAdapter.getView(i, this);
            addView(view);
        }
    }

    private class ListLinearLayoutDataObserver extends DataSetObserver {
        @Override
        public void onChanged() {
            super.onChanged();
        }
        public void onItemRangeChanged(int positionStart, int itemCount) {

        }
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {

        }
        public void onItemRangeInserted(int positionStart, int itemCount) {

        }
        public void onItemRangeRemoved(int positionStart, int itemCount) {

        }
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {

        }
    }

    public static class LinearAdapter<T> extends BaseAdapter {
        private Context mContext;
        private MultiItemTypeSupport mMultiItemTypeSupport;
        private int mResId;
        private List<T> mList;

        public LinearAdapter(Context context, int resId) {
            this(context, resId, new ArrayList<T>());
        }

        public LinearAdapter(Context context, int resId, List<T> list) {
            mContext = context;
            mResId = resId;
            mList = list;
        }

        public LinearAdapter(Context context, MultiItemTypeSupport multiItemTypeSupport) {
            this(context, multiItemTypeSupport, new ArrayList<T>());
        }

        public LinearAdapter(Context context, MultiItemTypeSupport multiItemTypeSupport, List<T> list) {
            mContext = context;
            mMultiItemTypeSupport = multiItemTypeSupport;
            mList = list;
        }

        public View getView(int i, ViewGroup viewGroup) {
            int layoutId;
            if (mMultiItemTypeSupport != null) {
                layoutId = mMultiItemTypeSupport.getLayoutId(mMultiItemTypeSupport.getItemViewType(i, mList.get(i)));
            } else {
                layoutId = mResId;
            }
            View view = LayoutInflater.from(mContext).inflate(layoutId, viewGroup, false);
            return view;
        }

        /**
         * 暂时没用
         */
        @Override
        public int getCount() {
            return mList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {

            return null;
        }
    }
}
