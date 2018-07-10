package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.NewSwipeBackActivity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.NewCategoryPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.NewCategoryAdapter;
import com.yijiuyiyiedu.xuetang.module.view.NewCategoryView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/4/3.
 * 新版分类
 */

public class NewCategoryActivity extends BaseActivity implements NewCategoryView {
    @BindView(R.id.newCategory_back)
    ImageView back;
    @BindView(R.id.newCategory_recyclerView)
    RecyclerView recyclerView;//列表
    NewCategoryAdapter adapter;
    List<NewCategoryEntity.DataBean.CategoryListBean> newCategorylist;
    private NewCategoryPresenter mPresenter;
    private Context mContext;
    private int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_category);
        //  add setContentView(...) invocation
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.newCategory_statusBar);
        mContext = this;
        newCategorylist = new ArrayList<>();
        mPresenter = new NewCategoryPresenter(this);
        position = getIntent().getIntExtra("position", 0);
//        mPresenter.getNewCategoryList("", "");
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
    }


    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.newCategory_statusBar);
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
    public void showData(NewCategoryEntity data) {
        if (data.getStatus()==0) {

            recyclerView.setLayoutManager(new LinearLayoutManager(this));
            if (position == 3) {
                newCategorylist.addAll(data.getData().getCategoryList());
            } else {
                List<NewCategoryEntity.DataBean.CategoryListBean> list = new ArrayList();

                newCategorylist.addAll(data.getData().getCategoryList());
                for (int i = 0; i < newCategorylist.size(); i++) {
                    if (position == i) {
                        list.add(newCategorylist.get(i));
                    }
                }
                newCategorylist.clear();
                newCategorylist.addAll(list);
            }
            adapter = new NewCategoryAdapter(R.layout.item_new_category_title, newCategorylist);
            recyclerView.setAdapter(adapter);
        }
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
