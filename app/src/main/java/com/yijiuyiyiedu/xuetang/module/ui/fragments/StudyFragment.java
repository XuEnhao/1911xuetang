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
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseFragment;
import com.yijiuyiyiedu.xuetang.module.entity.StudyBus;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.StudyPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.StudyCompanyAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.StudyPersonalAdapter;
import com.yijiuyiyiedu.xuetang.module.view.StudyView;
import com.yijiuyiyiedu.xuetang.utils.ProgressDialogUtils;
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
 * Created by xuenhao on 2018/3/13.
 * 学习页面
 */

public class StudyFragment extends BaseFragment implements StudyView {
    @BindView(R.id.study_companyBuyRecycler)
    RecyclerView companyRecycler;//企业recyclerview
    @BindView(R.id.study_personalBuyRecycler)
    RecyclerView personalRecycler;//个人recyclerview
    Unbinder unbinder;
    @BindView(R.id.studyFragment_userName)
    TextView userName;//用户名字
    @BindView(R.id.studyFragment_companyName)
    TextView companyName;//公司名字
    @BindView(R.id.study_companyBuy)
    LinearLayout companyBuyLinear;//企业购买的linear
    @BindView(R.id.study_personalBuy)
    LinearLayout personalBuyLinear;//个人购买的linear
    @BindView(R.id.study_emptyLinear)
    LinearLayout studyEmptyLinear;
    @BindView(R.id.nothing_buy)
    TextView nothingBuy;//没有购买
    private StudyPersonalAdapter personalAdapter;
    private StudyCompanyAdapter companyAdapter;
    StudyPresenter mPresenter;
//    private List<StudyEntity.DataBean.EnterpriseCurriculumBean> companyList;
    private List<StudyEntity.DataBean.UserCurriculumBean> personalList;

    @Override
    protected int getLayout() {
        return R.layout.fragment_study;
    }


    /**
     * 初始化数据
     *
     * @param inflater
     */
    @Override
    protected void initView(LayoutInflater inflater) {
        super.initView(inflater);
        mPresenter = new StudyPresenter(this);
//        companyList = new ArrayList<>();
        personalList = new ArrayList<>();
        if (SharedPreferencesUtil.selectUserInfo(mContext)) {//如果已登录
            UriConstant.userId = SharedPreferencesUtil.getStringValue(mContext, "userId");//将数据库里id取出来
            mPresenter.getData(UriConstant.userId, "", "");
        } else {
            studyEmptyLinear.setVisibility(View.VISIBLE);//显示空布局
            nothingBuy.setText("没有登录");
            companyBuyLinear.setVisibility(View.GONE);//把企业购买隐藏
            personalBuyLinear.setVisibility(View.GONE);//把个人购买隐藏
        }
        //设置企业购买的适配器和manager
        GridLayoutManager companyManager = new GridLayoutManager(mContext, 3);
        companyRecycler.setLayoutManager(companyManager);
//        companyAdapter = new StudyCompanyAdapter(R.layout.item_study_company, companyList);
        companyRecycler.setAdapter(companyAdapter);
        //设置个人购买的适配器和manager
        GridLayoutManager personalManager = new GridLayoutManager(mContext, 3);
        personalRecycler.setLayoutManager(personalManager);
        personalAdapter = new StudyPersonalAdapter(R.layout.item_study_company, personalList);
        personalRecycler.setAdapter(personalAdapter);

        //设置监听
        companyAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                UtilToast.showToast(mContext, companyList.get(position).getTitle());
                Intent it = new Intent(mContext, CourseDetailsActivity.class);
//                it.putExtra("courseId", companyList.get(position).getCurriculum_id());
                startActivity(it);
            }
        });
        personalAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                UtilToast.showToast(mContext, personalList.get(position).getTitle());
                Intent it = new Intent(mContext, CourseDetailsActivity.class);
                it.putExtra("courseId", personalList.get(position).getCurriculum_id());
                startActivity(it);
            }
        });
    }

    @Override
    protected void initEvent() {
        super.initEvent();
        EventBus.getDefault().register(this);
    }

    /**
     * 懒加载
     */
    @Override
    protected void lazyFetchData() {
        super.lazyFetchData();

    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void StudyBusBus(StudyBus event) {
//        userCompany.setText("未登录");
//        userName.setText("未登录");
//        GlideUtils.loadImage(mContext, "/public/image/touxiang.png", userAvatar);
//        mPresenter.getData(UriConstant.userId);//请求用户的信息
//        studyEmptyLinear.setVisibility(View.VISIBLE);//显示空布局
//        companyBuyLinear.setVisibility(View.GONE);
//        personalBuyLinear.setVisibility(View.GONE);
        if (mPresenter != null) {
            if (!TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext, "userId"))) {
                mPresenter.getData(UriConstant.userId, "", "");
            } else {
                studyEmptyLinear.setVisibility(View.VISIBLE);
                companyBuyLinear.setVisibility(View.GONE);
                personalBuyLinear.setVisibility(View.GONE);
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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }


    @Override
    public void showLoading() {
//        ProgressDialogUtils.getInstance(mContext).showDialog();
    }

    @Override
    public void hideLoading() {
//        if (mContext!=null){
//            ProgressDialogUtils.getInstance(mContext).dismissDialog();
//        }

    }

    @Override
    public void showData(final StudyEntity data) {
        if (data.getStatus().equals("1")) {
//        "user_type": "2 用户类型" 1个人用户 2企业用户
//        "enterprise_id": "0 企业的用户id", 0个人用户 大于0就是企业用户
            StudyEntity.DataBean.UserInfoBean userInfo = data.getData().getUserInfo();
            //公司
//            companyList.clear();
//            companyList.addAll(data.getData().getEnterpriseCurriculum());
            //个人
            personalList.clear();
            personalList.addAll(data.getData().getUserCurriculum());
            companyAdapter.notifyDataSetChanged();
            personalAdapter.notifyDataSetChanged();
            studyEmptyLinear.setVisibility(View.VISIBLE);
            companyBuyLinear.setVisibility(View.GONE);
            personalBuyLinear.setVisibility(View.GONE);
            if (SharedPreferencesUtil.selectUserInfo(mContext)) {
                nothingBuy.setText("现在还没有课程，快去购买吧");
            } else {
                nothingBuy.setText("没有登录");
            }
//            if (userInfo.getUser_type().equals("1") && !userInfo.getEnterprise_id().equals("0")) {//企业子账户
//                if (personalList.size() > 0) {//如果有个人购买的课程那么个人购买就显示布局
//                    personalBuyLinear.setVisibility(View.VISIBLE);
//                    personalRecycler.setVisibility(View.VISIBLE);
//                } else {
//                    personalBuyLinear.setVisibility(View.GONE);
//                }
//                if (companyList.size() > 0) {
//                    companyBuyLinear.setVisibility(View.VISIBLE);
//                    companyRecycler.setVisibility(View.VISIBLE);
//                } else {
//                    companyBuyLinear.setVisibility(View.GONE);
//                }
//            } else if (userInfo.getUser_type().equals("1") && userInfo.getEnterprise_id().equals("0")) {//个人用户
//                if (data.getData().getUserCurriculum().size() > 0) {
//                    personalBuyLinear.setVisibility(View.VISIBLE);
//                    personalRecycler.setVisibility(View.VISIBLE);
//                    companyBuyLinear.setVisibility(View.GONE);
//                } else {
//                    personalBuyLinear.setVisibility(View.GONE);
//                    companyBuyLinear.setVisibility(View.GONE);
//                }
//            } else if (userInfo.getUser_type().equals("2")) {
//                if (data.getData().getEnterpriseCurriculum().size() > 0) {//企业用户
//                    companyBuyLinear.setVisibility(View.VISIBLE);
//                    companyAdapter.setUserType(2);
//                    companyRecycler.setVisibility(View.VISIBLE);
//                } else {
//                    companyBuyLinear.setVisibility(View.GONE);
//                    companyAdapter.setUserType(2);
//                }
//            }
            //是否没有购买
            //一个都没有购买
//            if (data.getData().getEnterpriseCurriculum().size() == 0 && data.getData().getUserCurriculum().size() == 0) {
//                studyEmptyLinear.setVisibility(View.VISIBLE);
//                companyBuyLinear.setVisibility(View.GONE);
//                personalBuyLinear.setVisibility(View.GONE);
//            } else if (data.getData().getEnterpriseCurriculum().size() == 0) {
//                //企业的没有购买
//                studyEmptyLinear.setVisibility(View.GONE);
//                companyBuyLinear.setVisibility(View.GONE);
////                personalBuyLinear.setVisibility(View.VISIBLE);
//            } else
             if (data.getData().getUserCurriculum().size() == 0) {
                //个人的没有购买
                studyEmptyLinear.setVisibility(View.VISIBLE);
//                companyBuyLinear.setVisibility(View.VISIBLE);
                personalBuyLinear.setVisibility(View.GONE);
            }
//            //设置用户名字
//            userName.setText("" + userInfo.getNick_name());
//            //设置公司名字
//            companyName.setText("");


        } else {
            UtilToast.showToast(mContext, data.getMsg() + "");
        }

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
