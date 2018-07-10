package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewChildCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CategoryPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCategorySecondAdapter;
import com.yijiuyiyiedu.xuetang.module.view.CategoryView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/4/3.
 * 分类二级列表
 */

public class NewCategorySecondActivity extends BaseActivity implements CategoryView {
    @BindView(R.id.categorySecond_back)
    ImageView back;
    @BindView(R.id.categorySecond_title)
    TextView title;//二级分类名字
    @BindView(R.id.categorySecond_recyclerView)
    RecyclerView recyclerView;
    CategoryPresenter mPresenter;
    @BindView(R.id.categorySecond_screen)
    TextView screen;//筛选按钮
    Context mContext;
    @BindView(R.id.newCategory_linear)
    LinearLayout linear;
    NewCategorySecondAdapter adapter;
    @BindView(R.id.newCategorySecond_main)
    RelativeLayout main;//主布局
    @BindView(R.id.id_flowlayout)
    TagFlowLayout flowlayout;//流式布局
    @BindView(R.id.newCategorySecond_right)
    RelativeLayout right;//右边布局
    @BindView(R.id.categorySecond_drawerLayout)
    DrawerLayout drawerLayout;//侧滑抽屉
    @BindView(R.id.newCategory_toolbar)
    Toolbar toolbar;
    @BindView(R.id.categorySecond_statusBar)
    View categorySecondStatusBar;
    @BindView(R.id.newCategory_refreshLayout)
    TwinklingRefreshLayout refreshLayout;
    private List<CategoryEntity.DataBean> curriculumList;
    private List<NewChildCategoryEntity.DataBean.CategoryListBean> categoryList;
    // 抽屉菜单对象
    private ActionBarDrawerToggle drawerbar;
    private String category_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category_second);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.categorySecond_statusBar);
        mContext = this;
        initView();//初始化布局
        loadData();//请求数据
        initEvent();

    }


    //设置开关监听
    private void initEvent() {
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
     * 请求数据
     */
    private void loadData() {
        category_id = getIntent().getStringExtra("category_id");
        mPresenter.getData("", "", "", "", "", "","",category_id,"",0);

        mPresenter.getChildData("", "", category_id);
    }

    /**
     * 初始化布局
     */
    private void initView() {
        curriculumList = new ArrayList<>();
        mPresenter = new CategoryPresenter(this);
        //设置菜单内容之外其他区域的背景色
//        Color.parseColor("#00000050")
        drawerLayout.setScrimColor(Color.parseColor("#80000000"));
        refreshLayout.setOverScrollBottomShow(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mPresenter.getData("", "6", "", "", "", "","",category_id,"",0);


            }
        });

    }

    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.categorySecond_statusBar);
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
    public void showData(final CategoryEntity data) {
//        if (data.getStatus().equals("1")) {
//            refreshLayout.finishRefreshing();
//            curriculumList.clear();
//            curriculumList.addAll(data.getData().getCurriculumList());
//            title.setText(getIntent().getStringExtra("title"));
//            adapter = new NewCategorySecondAdapter(R.layout.item_new_category_second1, curriculumList);
//            recyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
//            recyclerView.setAdapter(adapter);
//            adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
//                @Override
//                public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
//                    Intent it = new Intent(mContext, CourseDetailsActivity.class);
//                    it.putExtra("courseId", data.getData().getCurriculumList().get(position).getId());
//                    startActivity(it);
//                }
//            });
//        }
    }

    /**
     * 请求右滑
     *
     * @param data
     */
    @Override
    public void showChildData(NewChildCategoryEntity data) {
        if (data.getStatus().equals("1")) {
            categoryList = data.getData().getCategoryList();
            List<String> list = new ArrayList<>();
            list.add("全部");
            for (int i = 0; i < data.getData().getCategoryList().size(); i++) {
                list.add(categoryList.get(i).getCategory_name());
            }
            flowlayout.setAdapter(new TagAdapter<String>(list) {
                @Override
                public View getView(FlowLayout parent, int position, String o) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.item_new_category_content, parent, false);
                    TextView textView = view.findViewById(R.id.item_newCategoryContent);
                    textView.setText(o);
                    return view;
                }
            });
            flowlayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
                @Override
                public boolean onTagClick(View view, int position, FlowLayout parent) {
                    //
                    openRightLayout(view);
                    if (position == 0) {
                        category_id = "";
                        mPresenter.getData("", "6", "", "", "", "","",category_id,"",0);

                    } else {
                        //跳转分类二级页面
                        category_id = categoryList.get(position - 1).getId();
                        mPresenter.getData("", "6", "", "", "", "","",category_id,"",0);

                    }
                    return true;
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

    // 右边菜单开关事件
    public void openRightLayout(View view) {
        if (drawerLayout.isDrawerOpen(right)) {
            drawerLayout.closeDrawer(right);
        } else {
            drawerLayout.openDrawer(right);
        }
    }

    @OnClick({R.id.categorySecond_back, R.id.categorySecond_screen})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.categorySecond_back:
                JumpUtil.overiderAnimsition((Activity) mContext);
                break;
            case R.id.categorySecond_screen://筛选按钮
                //打开或关闭右侧菜单
                openRightLayout(view);
                break;
        }
    }
}
