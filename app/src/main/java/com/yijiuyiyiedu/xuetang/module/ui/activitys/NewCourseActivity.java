package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.NewCourseEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.NewCoursePresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCurriculumAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.NewCourseView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/6/11.
 * 新上好课列表
 */

public class NewCourseActivity extends BaseActivity implements NewCourseView {
    @BindView(R.id.activity_newCourse_back)
    ImageView back;
    @BindView(R.id.activity_newCourse_search)
    ImageView search;//搜索
    @BindView(R.id.activity_newCourse_recyclerView)
    RecyclerView recyclerView;//recyclerview
    @BindView(R.id.activity_newCourse_refreshLayout)
    TwinklingRefreshLayout refreshLayout;//刷新
    NewCoursePresenter mPresenter;
    private int page = 1;
    private NewCurriculumAdapter mAdapter;
    private ArrayList<NewCourseEntity.DataBean.CurriculumListBean> list;
    private Context mContext;
    private boolean isLoadMore = false;
    private View footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
        ButterKnife.bind(this);
        mContext = this;
        initView();
    }

    private void initView() {
        list = new ArrayList<>();
        mPresenter = new NewCoursePresenter(this);
        mPresenter.getNewCourseData(page, 10);
        mAdapter = new NewCurriculumAdapter(R.layout.item_new_curriculum, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
        //设置监听
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewCourseEntity.DataBean.CurriculumListBean item = (NewCourseEntity.DataBean.CurriculumListBean) adapter.getItem(position);
                Intent it = new Intent(mContext,CourseDetailsActivity.class);
                it.putExtra("courseId",item.getId());
                startActivity(it);
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext, SearchActivity.class);
            }
        });
        footer = View.inflate(mContext, R.layout.evaluate_footer, null);
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
                mPresenter.getNewCourseData(page, 10);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                isLoadMore = true;
                mPresenter.getNewCourseData(++page, 10);
            }
        });
    }

    @Override
    public void showNewCourseData(NewCourseEntity data) {
        refreshLayout.finishLoadmore();
        refreshLayout.finishRefreshing();
        if (data.getStatus() == 0) {
            if (data.getData().getCurriculumList().size()<10){
                mAdapter.setFooterView(footer);
                refreshLayout.setEnableLoadmore(false);
            }
            if (isLoadMore) {
                mAdapter.addData(data.getData().getCurriculumList());
                isLoadMore = false;
            } else {
                mAdapter.removeFooterView(footer);
                refreshLayout.setEnableLoadmore(true);
                mAdapter.setNewData(data.getData().getCurriculumList());
            }
            mAdapter.notifyDataSetChanged();
        }
    }

    @OnClick(R.id.activity_newCourse_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }
}
