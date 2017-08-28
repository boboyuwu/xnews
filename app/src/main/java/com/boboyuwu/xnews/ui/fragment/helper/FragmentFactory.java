package com.boboyuwu.xnews.ui.fragment.helper;

import android.support.v4.app.Fragment;

/**
 * Created by wubo on 2017/7/10.
 * 根据tab或者id区分
 */

public interface FragmentFactory{

          Fragment createFragment(String tab);
          Fragment createFragment(int tab);

}
