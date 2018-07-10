package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.MyCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.TabEntity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.MainActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl.ContentNormalPagerAdapter;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/6/2.
 * 我的课程页面
 */

public class MyCourseFragment extends BaseFragment {
    @BindView(R.id.fragment_myCourse_tabLayout)
    CommonTabLayout tabLayout;
    @BindView(R.id.fragment_myCourse_viewpager)
    ViewPager viewPager;
    protected Unbinder unbinder;
    private List<Fragment> fragments = new ArrayList<>();
    private String[] tabTitle = {"我的课程", "我的收藏"};//, "我的下载"我的下载暂时隐藏
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private boolean isLoad = false;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mycourse;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
    }


    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        loadData();
    }

    private void loadData() {
        if (!isLoad) {
            isLoad = true;
            for (int i = 0; i < tabTitle.length; i++) {
                mTabEntities.add(new TabEntity(tabTitle[i], 0, 0));
            }
            initTabLayout();//初始化tablayout
            fragments.add(new MyCourseMyCourseFragment());//我的课程
//            fragments.add(new MyCourseMyDownFragment());//我的下载
            fragments.add(new MyCourseMyCollectFragment());//我的收藏
            viewPager.setOffscreenPageLimit(3);
            ContentNormalPagerAdapter pagerAdapter = new ContentNormalPagerAdapter(getChildFragmentManager(), fragments, tabTitle);
            viewPager.setAdapter(pagerAdapter);

        }
    }

    private void initTabLayout() {
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(0);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
