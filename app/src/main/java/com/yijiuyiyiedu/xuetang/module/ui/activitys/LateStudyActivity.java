package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.test.mock.MockContext;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.LateStudyBus;
import com.yijiuyiyiedu.xuetang.module.entity.MyCurriculumEntity;
import com.yijiuyiyiedu.xuetang.module.entity.MyCurriculumEntity.DataBean.CurriculumListBean;
import com.yijiuyiyiedu.xuetang.module.persenter.MyCurriculumPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.LateStudyAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.TestAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.MyCurriculumView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.SingleLoginUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/6/13.
 * 最近学习界面
 */

public class LateStudyActivity extends BaseActivity implements MyCurriculumView {
    @BindView(R.id.lateStudy_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.lateStudy_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private Context mContext;
    protected MyCurriculumPresenter mPresenter;
    private int page = 1;
    private LateStudyAdapter mAdapter;
    private  List<CurriculumListBean> list = new ArrayList<>();
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_late_study);
        ButterKnife.bind(this);
        mContext = this;
        mPresenter = new MyCurriculumPresenter(this);
        mPresenter.getMyCurriculumData(3, page, 6);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setHeaderView(new LoadView(mContext));
        refreshLayout.setOverScrollBottomShow(false);//越界回弹不显示loadmore布局
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mPresenter.getMyCurriculumData(3, page, 6);
            }

            @Override
            public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                super.onLoadMore(refreshLayout);
                mPresenter.getMyCurriculumData(3, ++page, 6);

            }
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        mAdapter = new LateStudyAdapter(R.layout.item_late_study,list);
        View emptyView = View.inflate(mContext, R.layout.activity_late_study_empty, null);
        textView = emptyView.findViewById(R.id.lateStudy_empty);//去学习跳转到我的课程
        mAdapter.setEmptyView(emptyView);
        recyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                CurriculumListBean item = (CurriculumListBean) adapter.getItem(position);
                Intent it = new Intent(mContext, CourseDetailsActivity.class);
                it.putExtra("courseId",item.getId());
                mContext.startActivity(it);
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().postSticky(new LateStudyBus(""));
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
    }

    @OnClick(R.id.lateStudy_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }

    @Override
    public void showMyCurriculumData(MyCurriculumEntity data) {
        refreshLayout.finishRefreshing();
        refreshLayout.finishLoadmore();
        if (data.getStatus().equals("0")) {
            mAdapter.setNewData(data.getData().getCurriculumList());
        }else if (data.getStatus().equals("100008")){
            SingleLoginUtils.showDialog(mContext);
        }else {
            list.clear();
            mAdapter.setNewData(list);
//            ToastUtils.showShort(data.getMsg());
        }
    }
}
