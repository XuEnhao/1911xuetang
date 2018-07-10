package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.BoughtProjectEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.persenter.BoughtProjectPresenter;
import com.yijiuyiyiedu.xuetang.module.persenter.StudyPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.BoughtProjectDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.ProjectDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.BoughtCourseAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.BoughtProjectAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.CustomProjectAdapter;
import com.yijiuyiyiedu.xuetang.module.view.BoughtProjectView;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/4/20.
 * 已购项目
 */

public class BoughtProjectFragment extends BaseFragment implements BoughtProjectView{
    @BindView(R.id.boughtProject_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.boughtProject_refreshLayout)
    TwinklingRefreshLayout refreshLayout;//刷新布局
    Unbinder unbinder;
    private View studyEmpty;//空页面
    private TextView nothingBuy;//没有购买课程
    private BoughtProjectPresenter mPresenter;
    private BoughtProjectEntity data;
    private List<BoughtProjectEntity.DataBean.UserProjectBean> projectList;
    private BoughtProjectAdapter adapter;
    @Override
    protected int getLayout() {
        return R.layout.fragment_bought_project;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        studyEmpty = rootView.findViewById(R.id.boughtProject_empty);
        nothingBuy = studyEmpty.findViewById(R.id.nothing_buy);
        projectList= new ArrayList<>();
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                if (mPresenter!=null){
//                    mPresenter.getUserProjectData(UriConstant.userId);
                }
            }
        });
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (SharedPreferencesUtil.selectUserInfo(mContext)) {//如果已登录
            studyEmpty.setVisibility(View.GONE);
            mPresenter = new BoughtProjectPresenter(this);
            UriConstant.userId = SharedPreferencesUtil.getStringValue(mContext, "userId");//将数据库里id取出来
//            mPresenter.getUserProjectData(UriConstant.userId);
        } else {
            nothingBuy.setText("没有登录");
            studyEmpty.setVisibility(View.VISIBLE);
            refreshLayout.setVisibility(View.GONE);

        }
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void StudyBusBus(StudyBus event) {
        if (mPresenter != null) {
            if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "userId"))) {
                studyEmpty.setVisibility(View.GONE);
//                mPresenter.getUserProjectData(UriConstant.userId);
            } else {
                studyEmpty.setVisibility(View.VISIBLE);
                refreshLayout.setVisibility(View.GONE);
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    nothingBuy.setText("现在还没有项目，快去购买吧");
                } else {
                    nothingBuy.setText("没有登录");
                }
            }
        } else {
//            UtilToast.showToast(mContext, "presenter为空");
        }
    }
    //
    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);
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
    public void showData(final BoughtProjectEntity data) {
        if (data.getStatus().equals("1")) {
            refreshLayout.finishRefreshing();
            projectList.clear();
            projectList.addAll(data.getData().getUserProject());
            adapter = new BoughtProjectAdapter(R.layout.item_custom_project, projectList);
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
            recyclerView.setAdapter(adapter);

            if (projectList.size()==0){
                studyEmpty.setVisibility(View.VISIBLE);
                refreshLayout.setVisibility(View.GONE);
                nothingBuy.setText("还没有购买项目,快去购买吧");
            }else{
                studyEmpty.setVisibility(View.GONE);
                refreshLayout.setVisibility(View.VISIBLE);
            }
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent it = new Intent(mContext, BoughtProjectDetailsActivity.class);
                    it.putExtra("projectId", data.getData().getUserProject().get(position).getProject_id());
                    it.putExtra("title", data.getData().getUserProject().get(position).getProject_name());
                    startActivity(it);
                }
            });
        }

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
