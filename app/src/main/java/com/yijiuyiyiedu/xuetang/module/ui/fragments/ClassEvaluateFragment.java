package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.AddEvaluateBus;
import com.yijiuyiyiedu.xuetang.module.entity.ClassEvaluateEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.ClassEvaluatePresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.AddEvaluateActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.ClassEvaluateAdapter;
import com.yijiuyiyiedu.xuetang.module.view.ClassEvaluateView;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/3/14.
 * 课程评价
 */

public class ClassEvaluateFragment extends BaseFragment implements ClassEvaluateView {
    @BindView(R.id.classEvaluate_recycler)
    RecyclerView recycler;//reyccler
    Unbinder unbinder;
    ClassEvaluatePresenter mPresenter;
    @BindView(R.id.classEvaluate_evaText)
    ImageView evaText;
    @BindView(R.id.classEvaluate_refreshLayout)
    TwinklingRefreshLayout refreshLayout;//下拉刷新布局
    @BindView(R.id.classEvaluate_eva)
    TextView classEvaluateEva;//评价
    @BindView(R.id.classEvaluate_score)
    TextView headScore;//头布局评分
    private List<ClassEvaluateEntity.DataBean.EvaluateListBean> list;
    private ClassEvaluateAdapter adapter;
    boolean isLoad = false;//是否加载
    private LinearLayoutManager manager;
    private int page = 1;
    private String courseId;
    private boolean isRefresh = false;
    private View footer;

    @Override
    protected int getLayout() {
        return R.layout.fragment_class_evaluate;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void AddEvaluateEvent(AddEvaluateBus bean) {
        if (mPresenter != null) {
            isRefresh = true;
            page = 1;
            mPresenter.getEvaluateData(courseId, String.valueOf(page), "4");
        }
    }


    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (!isLoad) {
            mPresenter = new ClassEvaluatePresenter(this);
            list = new ArrayList<>();
            isLoad = true;
            recycler.setNestedScrollingEnabled(false);//滑动粘连
            manager = new LinearLayoutManager(mContext);
            recycler.setLayoutManager(manager);
            adapter = new ClassEvaluateAdapter(R.layout.item_class_evaluate, list);
            recycler.setAdapter(adapter);
            headScore.setText(((CourseDetailsActivity) mContext).score + "");
            if (((CourseDetailsActivity) mContext).play == 1) {
                evaText.setVisibility(View.VISIBLE);
            } else {
                evaText.setVisibility(View.GONE);
            }
            refreshLayout.setEnableRefresh(false);
            refreshLayout.setOverScrollTopShow(false);
            refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
                @Override
                public void onLoadMore(TwinklingRefreshLayout refreshLayout) {
                    super.onLoadMore(refreshLayout);
                    mPresenter.getEvaluateData(((CourseDetailsActivity) mContext).courseId, String.valueOf(++page), "4");
                }

            });
            courseId = ((CourseDetailsActivity) mContext).getIntent().getStringExtra("courseId");
            mPresenter.getEvaluateData(courseId, String.valueOf(page++), "4");
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


    /**
     * 评价按钮
     */
    @OnClick(R.id.classEvaluate_evaText)
    public void onViewClicked() {
        Intent intent = new Intent(mContext, AddEvaluateActivity.class);
        intent.putExtra("courseId", ((CourseDetailsActivity) mContext).courseId);
        startActivity(intent);
//        JumpUtil.jump(mContext, AddEvaluateActivity.class);
    }


    @Override
    public void showData(ClassEvaluateEntity data) {
        refreshLayout.finishLoadmore();
        if (data.getStatus().equals("0")) {
            if (data.getData().getEvaluateList() != null&&data.getData().getEvaluateList().size() != 0){
                if (isRefresh){
                    isRefresh = false;
                    list.clear();
                    adapter.removeFooterView(footer);
                    refreshLayout.setEnableLoadmore(true);
                    adapter.setNewData(data.getData().getEvaluateList());
                }else{
                    list.addAll(data.getData().getEvaluateList());
                }
            }
            else if (data.getData().getEvaluateList().size() == 0) {
                footer = View.inflate(mContext, R.layout.evaluate_footer, null);
                adapter.setFooterView(footer);
                refreshLayout.setEnableLoadmore(false);
            }
            classEvaluateEva.setText("(" + adapter.getData().size() + "条评价）");
            adapter.notifyDataSetChanged();
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }
    }
}
