package com.boboyuwu.common.basefragentpageadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.List;

/**
 * Created by wubo on 2017/7/10.
 * 一个快捷的FragmentPagerAdapter
 */

public class BaseFragmentPagerAdapter extends FragmentPagerAdapter{
    private List<Fragment> mList;
    private BaseFragmentPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    public BaseFragmentPagerAdapter(FragmentManager fm, List<Fragment> list) {
        this(fm);
        mList = list;
    }



    @Override
    public Fragment getItem(int position) {
        return mList.get(position);
    }

    @Override
    public int getCount() {
        return mList.size();
    }



}
