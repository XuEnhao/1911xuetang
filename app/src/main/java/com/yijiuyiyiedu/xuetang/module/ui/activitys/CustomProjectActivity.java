package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectListEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CustomProjectPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.CustomProjectAdapter;
import com.yijiuyiyiedu.xuetang.module.view.CustomProjectView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/4/8.
 * 项目列表
 */

public class CustomProjectActivity extends BaseActivity implements CustomProjectView {

    @BindView(R.id.customProject_title)
    TextView title;//标题
    @BindView(R.id.customProject_recycler)
    RecyclerView recyclerView;//列表
    CustomProjectAdapter adapter;
    @BindView(R.id.customProject_refreshLayout)
    TwinklingRefreshLayout refreshLayout;//下拉刷新
    @BindView(R.id.customProject_main)
    RelativeLayout main;
    @BindView(R.id.customProject_rightRecycler)
    RecyclerView rightRecycler;
    @BindView(R.id.customProject_right)
    RelativeLayout right;
    @BindView(R.id.customProject_drawerLayout)
    DrawerLayout drawerLayout;//抽屉菜单
    private Context mContext;
    private List<ProjectListEntity.DataBean.ProjectListBean> projectList;//项目列表
    private CustomProjectPresenter mPresenter;
    private List<ProjectCategoryEntity.DataBean.ProjectCategoryListBean> categoryList;//项目分类
    private String project_category_id;
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_project);
        ButterKnife.bind(this);
        mContext = this;
        StatusBarUtil.setStatusBar(this,R.id.customProject_statusBar);
        initView();
        initDrawerLayout();
        loadData();
    }

    /**
     * 初始化抽屉菜单
     */
    private void initDrawerLayout() {
        drawerbar = new ActionBarDrawerToggle(this, drawerLayout, R.mipmap.ic_launcher, R.string.open, R.string.close) {
            //菜单打开
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            }

            // 菜单关闭
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
            }
        };

        drawerLayout.setDrawerListener(drawerbar);
    }

    /**
     * 加载数据
     */
    private void loadData() {
        if (mPresenter==null){
            mPresenter = new CustomProjectPresenter(this);
        }
//        mPresenter.getProjectCategory("");
        project_category_id = getIntent().getStringExtra("project_category_id");
//        mPresenter.getProjectList("", "", getIntent().getStringExtra("project_category_id"));
    }

    /**
     * 初始化布局
     */
    private void initView() {
        projectList = new ArrayList<>();
        categoryList = new ArrayList<>();
        adapter = new CustomProjectAdapter(R.layout.item_custom_project, projectList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setHeaderView(new SinaRefreshView(this));
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                loadData();
            }
        });

    }

    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.customProject_statusBar);
//        ViewGroup.LayoutParams layoutParams = viewById.getLayoutParams();
//        layoutParams.height = statusBarHeight;
//    }



    /**
     * 返回的监听方法
     */
    @OnClick({R.id.customProject_back, R.id.customProject_spinner, R.id.customProject_custom})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.customProject_back://返回按钮
                JumpUtil.overiderAnimsition((Activity) mContext);
                break;
            case R.id.customProject_spinner://筛选
                openRightLayout(view);
                break;
            case R.id.customProject_custom://定制项目按钮
                if (SharedPreferencesUtil.selectUserInfo(mContext)){
                    JumpUtil.jump(mContext, CustomProjectContentActivity.class);
                }else{
                    JumpUtil.jump(mContext,PassLoginActivity.class);//如果没登录就跳转登录页面
                }
                break;
        }

    }

    // 右边菜单开关事件
    public void openRightLayout(View view) {
        if (drawerLayout.isDrawerOpen(right)) {
            drawerLayout.closeDrawer(right);
        } else {
            drawerLayout.openDrawer(right);
        }
    }

//    /**
//     * 初始化下拉窗口
//     */
//    /**
//     * popup窗口里的ListView
//     */
//    private ListView mTypeLv;
//
//    /**
//     * popup窗口
//     */
//    private PopupWindow typeSelectPopup;
//
//    /**
//     * 模拟的假数据
//     */
//    private List<String> testData;
//
//    /**
//     * 数据适配器
//     */
//    private ArrayAdapter<String> testDataAdapter;
//
//    private void initPopUpWindow() {
//        mTypeLv = new ListView(this);
//        TestData();
//        // 设置适配器
//        testDataAdapter = new ArrayAdapter<String>(this, R.layout.popup_text_item, testData);
//        mTypeLv.setAdapter(testDataAdapter);
//
//        // 设置ListView点击事件监听
//        mTypeLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                // 在这里获取item数据
//                String value = testData.get(position);
//                // 把选择的数据展示对应的TextView上
////                mSelectTv.setText(value);
//                UtilToast.showToast(mContext, "点击了第" + position + "个");
//                // 选择完后关闭popup窗口
//                typeSelectPopup.dismiss();
//            }
//        });
//        typeSelectPopup = new PopupWindow(mTypeLv, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
//        // 取得popup窗口的背景图片
////        Drawable drawable = ContextCompat.getDrawable(this, R.drawable.bg_corner);
//        typeSelectPopup.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#ffffff")));
//        typeSelectPopup.setFocusable(true);
//        typeSelectPopup.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//        typeSelectPopup.update();
//        typeSelectPopup.setOutsideTouchable(true);
//        typeSelectPopup.setOnDismissListener(new PopupWindow.OnDismissListener() {
//            @Override
//            public void onDismiss() {
//                // 关闭popup窗口
//                typeSelectPopup.dismiss();
//            }
//        });
//    }

//    private void TestData() {
//        testData = new ArrayList<>();
//        for (int i = 0; i < 3; i++) {
//            String str = new String("数据" + i);
//            testData.add(str);
//        }
//    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    /**
     * 返回获取项目分类数据
     *
     * @param data
     */
    @Override
    public void showData(ProjectCategoryEntity data) {
        if (data.getStatus().equals("1")) {
            refreshLayout.finishRefreshing();
            categoryList.addAll(data.getData().getProjectCategoryList());
            rightRecycler.setLayoutManager(new LinearLayoutManager(this));
            RightAdapter aadapter = new RightAdapter(R.layout.popup_text_item, categoryList);
            for (int i = 0; i < categoryList.size(); i++) {
                if (categoryList.get(i).getId().equals(project_category_id)) {
                    categoryList.get(i).setIsColor("黑");
                    title.setText(categoryList.get(i).getCategory_name());
                } else {
                    categoryList.get(i).setIsColor("灰");
                }
            }
            rightRecycler.setAdapter(aadapter);
            aadapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    openRightLayout(view);
                    project_category_id = categoryList.get(position).getId();
//                    mPresenter.getProjectList("", "", categoryList.get(position).getId());
                    for (int i = 0; i < categoryList.size(); i++) {
                        categoryList.get(i).setIsColor("灰");
                    }
                    categoryList.get(position).setIsColor("黑");
                    title.setText(categoryList.get(position).getCategory_name());
                    adapter.notifyDataSetChanged();

                }
            });

        }
    }

    /**
     * 返回获取项目列表数据
     *
     * @param data
     */
    @Override
    public void showProjectListData(ProjectListEntity data) {
        if (data.getStatus().equals("1")) {
            projectList = data.getData().getProjectList();
            adapter.getData().clear();
            adapter.setNewData(projectList);
            adapter.notifyDataSetChanged();
            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                    if (SharedPreferencesUtil.selectUserInfo(mContext)){
                        Intent it = new Intent(mContext, ProjectDetailsActivity.class);
                        it.putExtra("projectId", projectList.get(position).getId());
                        it.putExtra("title", projectList.get(position).getProject_name());
                        startActivity(it);
                    }else{
                        JumpUtil.jump(mContext,PassLoginActivity.class);
                    }

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


    /**
     * 右边适配器
     */
    class RightAdapter extends BaseQuickAdapter<ProjectCategoryEntity.DataBean.ProjectCategoryListBean, BaseViewHolder> {

        public RightAdapter(int layoutResId, @Nullable List<ProjectCategoryEntity.DataBean.ProjectCategoryListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, ProjectCategoryEntity.DataBean.ProjectCategoryListBean item) {
            helper.setText(R.id.popup_text, item.getCategory_name());
            TextView view = helper.getView(R.id.popup_text);
            if (item.getIsColor().equals("黑")) {
                view.setTextColor(Color.parseColor("#333333"));
            } else {
                view.setTextColor(Color.parseColor("#666666"));
            }
        }
    }
}
