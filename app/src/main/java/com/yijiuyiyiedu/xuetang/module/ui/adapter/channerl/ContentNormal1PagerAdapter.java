package com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.view.ViewGroup;

import com.yijiuyiyiedu.xuetang.module.ui.fragments.ClassContentsFragment;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.ClassEvaluateFragment;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/14.
 */

public class ContentNormal1PagerAdapter extends FragmentStatePagerAdapter {

    private List<Fragment> fragments;
    private String[] titles;

    public ContentNormal1PagerAdapter(FragmentManager fm, List<Fragment> fragments, String[] strings) {
        super(fm);
        this.fragments = fragments;
        this.titles = strings;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments == null ? 0 : fragments.size();
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        super.destroyItem(container, position, object);
    }


    @Override
    public CharSequence getPageTitle(int position) {
        return titles[position];
    }


    @Override
    public int getItemPosition(Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}