package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.ProjectDetailsPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.ProjectDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCategorySecondAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.ProjectDetailsAdapter;
import com.yijiuyiyiedu.xuetang.module.view.ProjectDetailsView;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/4/9.
 * 项目内容
 */

public class ProjectContentFragment extends BaseFragment implements ProjectDetailsView {
    @BindView(R.id.projectContent_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.projectContent_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    Unbinder unbinder;
    private ProjectDetailsPresenter mPresenter;
    private ProjectDetailsAdapter adapter;
    private List<ProjectDetailsEntity.DataBean.ProjectCurriculumListBean> list;
    public static ProjectContentFragment getInstance(String projecId){
        ProjectContentFragment fragment = new ProjectContentFragment();
        Bundle bundle = new Bundle();
        bundle.putString("projectId",projecId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_project_content;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        list = new ArrayList<>();
        mPresenter = new ProjectDetailsPresenter(this);
//        mPresenter.getProjectDetailsData(getArguments().getString("projectId"), UriConstant.userId);
        refreshLayout.setOverScrollBottomShow(false);//越界不回弹 底部加载更多布局
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
//                mPresenter.getProjectDetailsData(getArguments().getString("projectId"), UriConstant.userId);
            }
        });
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
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(final ProjectDetailsEntity data) {
        if (data.getStatus().equals("1")) {
            refreshLayout.finishRefreshing();
            list.clear();
            list.addAll(data.getData().getProjectCurriculumList());
            adapter = new ProjectDetailsAdapter(R.layout.item_new_category_second, list);
            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
            recyclerView.setAdapter(adapter);
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId", data.getData().getProjectCurriculumList().get(position).getId());
                    startActivity(it);
                }
            });
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }
    }

    @Override
    public void showBoughtProject(ProjectDetailsEntity data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
