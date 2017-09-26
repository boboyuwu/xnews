package com.boboyuwu.common.basefragentpageadapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by wubo on 2017/9/26.
 */

public abstract class BaseViewPagerAdapter <T> extends PagerAdapter{

    private Context mContext;
    private List<T> mList;
    private int mLayoutId;
    private static final int NO_LAYOUT=0;

    public BaseViewPagerAdapter(Context context,List<T> list, int layoutId){
        mContext = context;
        mList = list;
        mLayoutId = layoutId;
    }

    public BaseViewPagerAdapter(Context context,List<T> list){
        this(context,list,NO_LAYOUT);
    }
    @Override
    public int getCount() {
        return mList.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }


    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View inflateView=null;
        if(mLayoutId==NO_LAYOUT){
            inflateView=getItemView(position,mList.get(position));
            if(inflateView==null){
                throw new RuntimeException("请重写getItemView(int position,T t)方法返回view");
            }
            container.addView(inflateView);
        }else{
            inflateView = LayoutInflater.from(mContext).inflate(mLayoutId, container, false);
        }
        convert(inflateView,position,mList.get(position));
        return container;
    }



    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    protected View getItemView(int position,T t){
        return null;
    }
    protected abstract View convert(View inflateView,int position,T t);
}
