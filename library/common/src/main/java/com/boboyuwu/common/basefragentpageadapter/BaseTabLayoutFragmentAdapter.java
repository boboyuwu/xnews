package com.boboyuwu.common.basefragentpageadapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import java.util.List;

/**
 * Created by wubo on 2017/7/10.
 * 一个快捷的带title的FragmentPagerAdapter
 */

public abstract class BaseTabLayoutFragmentAdapter extends BaseFragmentPagerAdapter{

    private List<Fragment> mList;

    public BaseTabLayoutFragmentAdapter(FragmentManager fm, List<Fragment> list) {
        super(fm, list);
        mList = list;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return getTitle(position);
    }

    public  abstract CharSequence getTitle(int position);
}
