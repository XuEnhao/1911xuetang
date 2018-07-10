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
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewsEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CoursePresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCourseMsgMoreAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.CourseView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/4/21.
 * 资讯列表
 */

public class SchoolMsgMoreActivty extends BaseActivity implements CourseView {
    @BindView(R.id.msg_more_recycler)
    RecyclerView recycler;
    @BindView(R.id.msg_more_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    @BindView(R.id.more_search)
    ImageView search;
    private CoursePresenter mPresenter;
    private NewCourseMsgMoreAdapter msgAdapter;
    private Context mContext;
    private List<NewsEntity.DataBean.NewsListBean> list;
    private int page = 1;
    private boolean isLoadMore=false;
    private View footer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_school_msg_more);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this, R.id.addEvaluate_statusBar);
        mContext = this;
        loadData();

    }

    private void loadData() {
        list = new ArrayList<>();
        mPresenter = new CoursePresenter(this);
        mPresenter.getNewsData(String.valueOf(page), "10");//获取新闻资讯
        msgAdapter = new NewCourseMsgMoreAdapter(R.layout.item_new_course_msg, list);
        recycler.setLayoutManager(new LinearLayoutManager(mContext));
        footer = View.inflate(mContext, R.layout.evaluate_footer, null);
        recycler.setAdapter(msgAdapter);
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(true);
        refreshLayout.setHeaderView(new LoadView(mContext));
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setOverScrollTopShow(false);//越界回弹不显示loadmore布局
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                page = 1;
                refreshLayout.setEnableLoadmore(true);
//                msgAdapter.removeFooterView(footer);
                mPresenter.getNewsData(String.valueOf(page), "10");//获取数据
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                isLoadMore = true;
                mPresenter.getNewsData(String.valueOf(++page), "10");//获取数据
            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext,SearchActivity.class);
            }
        });
        //学堂资讯
        msgAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                NewsEntity.DataBean.NewsListBean item = (NewsEntity.DataBean.NewsListBean) adapter.getItem(position);
                Intent it = new Intent(mContext, SchoolMsgActivity.class);
                it.putExtra("id", item.getId());//新闻id
                startActivity(it);
            }
        });
    }


//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.addEvaluate_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(CourseEntity data) {

    }

    @Override
    public void showNewsData(final NewsEntity data) {
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
        if (data.getStatus().equals("0")) {
            //学堂资讯
//            R.layout.item_new_course_msg//学堂资讯
            if (data.getData().getNewsList().size()<10){
                msgAdapter.setFooterView(footer);
                refreshLayout.setEnableLoadmore(false);
            }
            if (isLoadMore){
                msgAdapter.addData(data.getData().getNewsList());
                isLoadMore = false;
            }else{
                msgAdapter.setNewData(data.getData().getNewsList());
            }

        }

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }

    @OnClick(R.id.addEvaluate_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }
}
