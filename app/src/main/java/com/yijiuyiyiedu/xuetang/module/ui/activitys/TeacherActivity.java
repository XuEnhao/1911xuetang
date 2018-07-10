package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.TeacherEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.TeacherPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCourseTeacherAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.TeacherListAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.TeacherView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/6/11.
 * 名师大咖秀列表
 */

public class TeacherActivity extends BaseActivity implements TeacherView{
    @BindView(R.id.activity_teacher_back)
    ImageView back;
    @BindView(R.id.activity_teacher_search)
    ImageView search;
    @BindView(R.id.activity_teacher_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.activity_teacher_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private TeacherPresenter mPresenter;
    private int page = 1;
    private Context mContext;
    private TeacherListAdapter mAdapter;
    private ArrayList<TeacherEntity.DataBean.TeacherListBean> list;
    private boolean isLoadMore = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        //名师大咖
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mAdapter = new TeacherListAdapter(R.layout.item_mian_teacher, list);
        // 一行代码搞定（默认为渐显效果）
        mAdapter.openLoadAnimation();
        recyclerView.setAdapter(mAdapter);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setOverScrollTopShow(false);
        refreshLayout.setHeaderView(new LoadView(mContext));
        View view = View.inflate(mContext, R.layout.activity_course_id_empty, null);
        TextView viewById = view.findViewById(R.id.empty_companyId);
        ImageView emptyImg = view.findViewById(R.id.empty_img);
        viewById.setText("对不起，该页面暂无数据");
        mAdapter.setEmptyView(view);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                page = 1;
                mPresenter.getTeacherData(page,6);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                isLoadMore = true;
                mPresenter.getTeacherData(page++,6);
            }
        });
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TeacherEntity.DataBean.TeacherListBean item = (TeacherEntity.DataBean.TeacherListBean) adapter.getItem(position);
                Intent it = new Intent(mContext, TeacherDetailsActivity.class);
                it.putExtra("teacherId", item.getId());
                startActivity(it);
            }
        });
        loadData();
    }

    private void loadData() {
        mPresenter = new TeacherPresenter(this);
        mPresenter.getTeacherData(page,6);
    }

    @Override
    public void showTeacherData(TeacherEntity data) {
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
        if (data.getStatus()==0){
            if (isLoadMore){
                mAdapter.addData(data.getData().getTeacherList());
                isLoadMore = false;
            }else{
                mAdapter.setNewData(data.getData().getTeacherList());
            }
            mAdapter.notifyDataSetChanged();
        }
    }
    @OnClick(R.id.activity_teacher_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }
}
