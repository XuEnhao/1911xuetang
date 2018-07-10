package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.entity.TabEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.ProjectDetailsPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl.ContentNormalPagerAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.ProjectContentFragment;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.ProjectDetailsFragment;
import com.yijiuyiyiedu.xuetang.module.view.ProjectDetailsView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.TabLayoutUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/4/9.
 * 项目详情
 */

public class ProjectDetailsActivity extends BaseActivity implements ProjectDetailsView {
    @BindView(R.id.projectDetails_back)
    ImageView back;
    @BindView(R.id.projectDetails_title)
    TextView title;
    @BindView(R.id.projectDetails_tabLayout)
    CommonTabLayout tabLayout;
    @BindView(R.id.projectDetails_viewPager)
    ViewPager viewPager;
    @BindView(R.id.projectDetails_price)
    TextView price;
    @BindView(R.id.projectDetails_buyProject)
    TextView buyProject;
    String[] titles = {"项目内容", "项目详情"};
    public static String projectId = "";
    private ProjectDetailsPresenter mPresenter;
    private List<Fragment> fragmentList;
    private Context mContext;
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project_details);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setStatusBar(this,R.id.projectDetails_statusBar);
        projectId = getIntent().getStringExtra("projectId");
        initView();
        loadData();
    }

    private void loadData() {
        mPresenter = new ProjectDetailsPresenter(this);
        if (TextUtils.isEmpty(SharedPreferencesUtil.getStringValue(mContext,"userId"))){
            UriConstant.userId = SharedPreferencesUtil.getStringValue(mContext,"userId");
//            mPresenter.getProjectDetailsData(projectId,UriConstant.userId);
        }else{
//            mPresenter.getProjectDetailsData(projectId,UriConstant.userId);
        }

    }

    /**
     * 初始化布局
     */
    private void initView() {
        for (int i = 0; i < titles.length; i++) {
            mTabEntities.add(new TabEntity(titles[i], 0, 0));
        }
        fragmentList = new ArrayList<>();//fragment布局
        fragmentList.add(ProjectContentFragment.getInstance(projectId));
        fragmentList.add(ProjectDetailsFragment.getInstance(projectId));
        ContentNormalPagerAdapter pagerAdapter = new ContentNormalPagerAdapter(getSupportFragmentManager(), fragmentList, titles);
        viewPager.setAdapter(pagerAdapter);
//        tabLayout.setupWithViewPager(viewPager);
//        TabLayoutUtils.setIndicator(tabLayout, 50, 50);
        //购买项目
        buyProject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (SharedPreferencesUtil.selectUserInfo(mContext)){
                    UriConstant.userId = SharedPreferencesUtil.getStringValue(mContext,"userId");
                    Intent it = new Intent(mContext,BuyProjectActivity.class);
                    it.putExtra("curriculumId",getIntent().getStringExtra("projectId"));
                    startActivity(it);
                }else{
                    UtilToast.showToast(mContext,"请先登录");
                }

            }
        });
        tabLayout.setTabData(mTabEntities);
        tabLayout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                viewPager.setCurrentItem(position);
            }

            @Override
            public void onTabReselect(int position) {
                if (position == 0) {
//                        mTabLayout_2.showMsg(0, mRandom.nextInt(100) + 1);
//                    UnreadMsgUtils.show(mTabLayout_2.getMsgView(0), mRandom.nextInt(100) + 1);
                }
            }
        });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                tabLayout.setCurrentTab(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        viewPager.setCurrentItem(0);
    }

    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.projectDetails_statusBar);
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
    public void showData(final ProjectDetailsEntity data) {
        if (data.getStatus().equals("1")) {
            price.setText("¥" + data.getData().getProjectDetail().getProject_price() + "/人");
            title.setText(getIntent().getStringExtra("title"));

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

    @OnClick(R.id.projectDetails_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }
}
