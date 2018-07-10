package com.yijiuyiyiedu.xuetang.module.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.StudyPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.BoughtCourseAdapter;
import com.yijiuyiyiedu.xuetang.module.view.StudyView;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by xuenhao on 2018/4/20.
 * 已购课程
 */

public class BoughtCourseFragment extends BaseFragment implements StudyView {
    @BindView(R.id.boughtCourse_recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.boughtCourse_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private GridLayoutManager newCourseManager;
    private BoughtCourseAdapter adapter;
    private StudyPresenter mPresenter;
    private List<StudyEntity.DataBean.UserCurriculumBean> newsCurriculumList;
    private TextView nothingBuy;
    private View studyEmpty;

    @Override
    protected int getLayout() {
        return R.layout.fragment_bought_course;
    }

    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        studyEmpty = rootView.findViewById(R.id.boughtCourse_empty);
        nothingBuy = studyEmpty.findViewById(R.id.nothing_buy);
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                if (mPresenter!=null){
                    mPresenter.getData(UriConstant.userId,"","");
                }
            }
        });
    }

    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();
        if (SharedPreferencesUtil.selectUserInfo(mContext)) {//如果已登录
            newsCurriculumList = new ArrayList<>();
            studyEmpty.setVisibility(View.GONE);
            mPresenter = new StudyPresenter(this);
            UriConstant.userId = SharedPreferencesUtil.getStringValue(mContext, "userId");//将数据库里id取出来
            mPresenter.getData(UriConstant.userId, "", "");
        } else {
            nothingBuy.setText("没有登录");
            studyEmpty.setVisibility(View.VISIBLE);
            refreshLayout.setVisibility(View.GONE);

        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void StudyBusBus(StudyBus event) {
        if (mPresenter != null) {
            if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "userId"))) {
                studyEmpty.setVisibility(View.GONE);
                mPresenter.getData(UriConstant.userId, "", "");
            } else {
                studyEmpty.setVisibility(View.VISIBLE);
                refreshLayout.setVisibility(View.GONE);
                if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                    nothingBuy.setText("现在还没有课程，快去购买吧");
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
    public void showData(StudyEntity data) {
        if (data.getStatus().equals("1")) {
            refreshLayout.finishRefreshing();
            newsCurriculumList.clear();
            newsCurriculumList.addAll(data.getData().getUserCurriculum());
            newCourseManager = new GridLayoutManager(mContext, 2);
            recyclerView.setLayoutManager(newCourseManager);
            adapter = new BoughtCourseAdapter(R.layout.item_new_course_new_course, newsCurriculumList);
            recyclerView.setAdapter(adapter);

            if (newsCurriculumList.size()==0){
                studyEmpty.setVisibility(View.VISIBLE);
                refreshLayout.setVisibility(View.GONE);
                nothingBuy.setText("还没有购买课程,快去购买吧");
            }else{
                studyEmpty.setVisibility(View.GONE);
                refreshLayout.setVisibility(View.VISIBLE);
            }
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
                    it.putExtra("courseId", newsCurriculumList.get(position).getId());
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
