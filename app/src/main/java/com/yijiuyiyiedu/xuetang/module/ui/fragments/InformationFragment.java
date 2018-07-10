package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.NewsChannelActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SearchActivity;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.flyco.tablayout.SlidingTabLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ${二星} on 2017/4/21 0021.
 */

public class InformationFragment extends BaseFragment {

    @BindView(R.id.add_channel_iv)
    ImageView addChannelIv;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    @BindView(R.id.magicIndicator)
    SlidingTabLayout tabs;
    @BindView(R.id.add_channel_iv_search)
    ImageView addChannelIvSearch;


    private static final String[] CHANNELS = new String[]{"推荐", "新高考改革", "语文", "数学", "政治", "化学", "奥数", "体育", "音乐"};
    private List<String> mList = new ArrayList<>();

//    private ExamplePagerAdapter mExamplePagerAdapter;

    // 指定FramgnetView布局
    @Override
    protected int getLayout() {
        return R.layout.fragment_information;
    }

    // 初始化View , presenter等
    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        // 数据拿到
        for (int i = 0; i < CHANNELS.length; i++) {
            mList.add(CHANNELS[i]);
        }
//        mExamplePagerAdapter = new ExamplePagerAdapter(getChildFragmentManager(),mList);
//        viewPager.setAdapter(mExamplePagerAdapter);
        tabs.setViewPager(viewPager);
    }

    // 懒加载
    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
//        ((InformationPresenter)mPresenter).onRefresh();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);

        return rootView;
    }

    @OnClick({R.id.add_channel_iv, R.id.add_channel_iv_search})
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.add_channel_iv:
                JumpUtil.jump(getContext(), NewsChannelActivity.class);
                break;
            case R.id.add_channel_iv_search:
                JumpUtil.jump(getContext(), SearchActivity.class);
                break;
        }
    }

    @OnClick(R.id.add_channel_iv_search)
    public void onClick() {

    }
}
