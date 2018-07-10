package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.ClassicCourseEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.ClassicCoursePresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.ClassicCourseAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.ClassicCourseView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/6/11.
 * 经典课程
 */

public class ClassicCourseFragment extends BaseFragment implements ClassicCourseView{
    @BindView(R.id.fragment_classicCourse_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.fragment_classicCourse_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    Unbinder unbinder;
    private boolean isLoad = false;
    private View footer;
    private ClassicCourseAdapter mAdapter;
    private ArrayList<ClassicCourseEntity.DataBean.CurriculumListBean> list ;
    private int page = 1;
    private ClassicCoursePresenter mPresenter;
    private int categoryId;
    private boolean isLoadMore = false;

    public static ClassicCourseFragment getInstance(int id){
        ClassicCourseFragment fragment = new ClassicCourseFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("id",id);
        fragment.setArguments(bundle);
        return fragment;
    }
    @Override
    protected int getLayout() {
        return R.layout.fragment_classic_course;
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad){
            categoryId = getArguments().getInt("id", 1);
            isLoad = true;
            list = new ArrayList<>();
            mPresenter = new ClassicCoursePresenter(this);
            mPresenter.getRecommendData(page,20,0,categoryId);//	排序字段 1 时间倒序 2 学习人数倒序 3 评分倒序
            recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
            mAdapter = new ClassicCourseAdapter(R.layout.item_new_course_new_course,list);
            mAdapter.openLoadAnimation();
            footer = View.inflate(mContext, R.layout.evaluate_footer, null);
            recyclerView.setAdapter(mAdapter);
            refreshLayout.setEnableRefresh(true);
            refreshLayout.setEnableLoadmore(true);
            refreshLayout.setOverScrollBottomShow(false);
            refreshLayout.setOverScrollTopShow(false);
            refreshLayout.setHeaderView(new LoadView(mContext));
            refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                @Override
                public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                    super.onRefresh(refreshLayout);
                    page = 1;
                    refreshLayout.setEnableLoadmore(true);
                    mAdapter.removeFooterView(footer);
                    mPresenter.getRecommendData(page,20,categoryId,1);
                }

                @Override
                public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                    super.onLoadMore(refreshLayout);
                    isLoadMore = true;
                    page++;
                    mPresenter.getRecommendData(page,20,categoryId,1);
                }
            });
            mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    ClassicCourseEntity.DataBean.CurriculumListBean item  = (ClassicCourseEntity.DataBean.CurriculumListBean) adapter.getData().get(position);
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId",item.getId());
                    startActivity(it);
                }
            });

        }

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

    @Override
    public void showRecommendData(ClassicCourseEntity data) {
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
        if (data.getStatus()==0){
            if (data.getData().getCurriculumList().size()<20){
                mAdapter.setFooterView(footer);
                refreshLayout.setEnableLoadmore(false);
            }
            if (isLoadMore){
                mAdapter.addData(data.getData().getCurriculumList());
                isLoadMore = false;
            }else{
                mAdapter.setNewData(data.getData().getCurriculumList());
            }
            mAdapter.notifyDataSetChanged();
        }
    }

}
