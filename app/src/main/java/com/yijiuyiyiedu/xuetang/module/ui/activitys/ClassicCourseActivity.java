package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.ImageView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.TabEntity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl.ContentNormalPagerAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.ClassicCourseFragment;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/6/11.
 * 经典课程列表
 */

public class ClassicCourseActivity extends BaseActivity {
    @BindView(R.id.activity_classic_back)
    ImageView back;
    @BindView(R.id.activity_classic_search)
    ImageView search;
    @BindView(R.id.activity_classic_tableLayout)
    CommonTabLayout tabLayout;
    @BindView(R.id.activity_classic_viewpager)
    ViewPager viewpager;
    String[] tabTitle = {"全部", "最新", "最热"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Fragment> fragments = new ArrayList<>();
    private Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_classic_course);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        for (int i = 0; i < tabTitle.length; i++) {
            mTabEntities.add(new TabEntity(tabTitle[i], 0, 0));
        }
        initTabLayout();//初始化tablayout
        fragments.add(ClassicCourseFragment.getInstance(1));
        fragments.add(ClassicCourseFragment.getInstance(2));
        fragments.add(ClassicCourseFragment.getInstance(3));
        ContentNormalPagerAdapter pagerAdapter = new ContentNormalPagerAdapter(getSupportFragmentManager(), fragments, tabTitle);
        viewpager.setAdapter(pagerAdapter);
    }

    private void initTabLayout() {
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
//                    switchFragment(position);
                viewpager.setCurrentItem(position);

            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
                }
            }
        });
        viewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
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

        viewpager.setCurrentItem(0);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext,SearchActivity.class);
            }
        });

    }

    @OnClick(R.id.activity_classic_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }
}
