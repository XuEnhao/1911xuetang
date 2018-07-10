package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.EditMyselfInfoBus;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCourseFriendAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.TestAdapter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/6/2.
 * 我的课程界面 我的下载
 */

public class MyCourseMyDownFragment extends BaseFragment {


    TestAdapter mAdapter;
    boolean isLoad = false;
    @BindView(R.id.fragment_myCourse_myCourse_downRecyclerView)
    RecyclerView downRecyclerView;
    @BindView(R.id.fragment_myCourse_myCourse_recommendRecyclerView)
    RecyclerView recommendRecyclerView;

    @Override
    protected int getLayout() {
        return R.layout.fragment_mycourse_mydown;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad) {
            recommendRecyclerView.setNestedScrollingEnabled(false);//滑动粘连
            downRecyclerView.setNestedScrollingEnabled(false);
            List<String> list = new ArrayList<>();
            mAdapter = new TestAdapter(R.layout.item_my_course_download, list);
            downRecyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            downRecyclerView.setAdapter(mAdapter);
            // 一行代码搞定（默认为渐显效果）
            mAdapter.openLoadAnimation();
            View view = View.inflate(mContext, R.layout.activity_my_course_empty, null);
            TextView viewById = view.findViewById(R.id.empty_myCourse_text);
            ImageView emptyImg = view.findViewById(R.id.empty_myCourse_img);
            emptyImg.setImageResource(R.mipmap.empty_down);
            view.findViewById(R.id.empty_myCourse_login).setVisibility(View.GONE);
            viewById.setText("对不起，该页面暂无数据");
            mAdapter.setEmptyView(view);
            recommendRecyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
            recommendRecyclerView.setAdapter(new TestAdapter(R.layout.item_new_course_new_course, list));
            isLoad = true;
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
