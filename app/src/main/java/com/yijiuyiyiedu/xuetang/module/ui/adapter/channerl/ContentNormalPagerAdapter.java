package com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.yijiuyiyiedu.xuetang.module.ui.fragments.ClassContentsFragment;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.ClassEvaluateFragment;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/14.
 */

public class ContentNormalPagerAdapter extends FragmentPagerAdapter {

    private List<Fragment> fragments;
    private String[] titles;

    public ContentNormalPagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] strings) {
        super(fm);
        this.fragments = fragments;
        this.titles = strings;
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }
}