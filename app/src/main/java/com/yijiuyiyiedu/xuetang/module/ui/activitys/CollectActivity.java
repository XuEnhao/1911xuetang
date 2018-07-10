package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.google.gson.Gson;
import com.lcodecore.tkrefreshlayout.header.GoogleDotView;
import com.lcodecore.tkrefreshlayout.header.SinaRefreshView;
import com.lcodecore.tkrefreshlayout.header.bezierlayout.BezierLayout;
import com.lcodecore.tkrefreshlayout.header.progresslayout.ProgressLayout;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CollectPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.DragCollectAdapter;
import com.yijiuyiyiedu.xuetang.module.view.CollectView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.NetWorkUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;
import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/3/21.
 * 我的收藏
 */

public class CollectActivity extends BaseActivity implements CollectView {
    @BindView(R.id.collect_back)
    ImageView collectBack;//返回
    @BindView(R.id.collect_recycler)
    RecyclerView recyclerView;//recycler
    DragCollectAdapter adapter;//侧滑删除adapter
    static CollectPresenter mPresenter;//分类presenter
    //    @BindView(R.id.collect_nothing)
//    TextView nothing;
    @BindView(R.id.collect_refreshLayout)
    TwinklingRefreshLayout refreshLayout;//下拉刷新
    private List<CollectEntity.DataBean> list = new ArrayList();
    private Context mContext;
    private Gson gson;

    public static void getData() {
        mPresenter.getCollectData();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        ButterKnife.bind(this);
        StatusBarUtil.setStatusBar(this,R.id.collect_statusBar);
        mContext = this;
        //侧滑删除的adapter
        adapter = new DragCollectAdapter(R.layout.item_drag_collect, list);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);
        recyclerView.setAdapter(adapter);
        //设置空布局
        View view = View.inflate(mContext, R.layout.activity_empty, null);
        TextView textView = view.findViewById(R.id.nothing_buy);
        textView.setText("您还没有收藏课程呢");
        adapter.setEmptyView(view);
        View footView = View.inflate(mContext, R.layout.fragment_category_foot, null);
        adapter.addFooterView(footView);
        mPresenter = new CollectPresenter(this);
        refreshLayout.setOverScrollBottomShow(false);
//        refreshLayout.setHeaderView(new ProgressLayout(mContext));//swipeRefreshLayout
//        refreshLayout.setFloatRefresh(true);//像swipeRefreshLayout一样的
//        refreshLayout.setHeaderView(new GoogleDotView(mContext));//google五个点刷新布局
//        refreshLayout.setHeaderView(new BezierLayout(mContext));//弹弹弹
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                loadData();

            }
        });
        loadData();
        collectBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
        if (!NetWorkUtil.judgeNetWork(mContext)){
            String userCollect = SharedPreferencesUtil.getStringValue(this, "userCollect");
            gson = new Gson();
            CollectEntity collectEntity = gson.fromJson(userCollect, CollectEntity.class);
            initData(collectEntity);
        }
    }


    private void loadData() {
        mPresenter.getCollectData();
    }

//    //导航栏
//    private void setStatusBar() {
//        // 设置透明导航栏
//        StatusBarUtil.setTranslucentForImageView(this, 50, null);
//        int statusBarHeight = StatusBarUtil.getStatusBarHeight(this);
//        View viewById = findViewById(R.id.collect_statusBar);
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
    public void showData(ClearHistoryEntity data) {

    }

    /**
     * 显示收藏列表
     *
     * @param data
     */
    @Override
    public void showCollectData(final CollectEntity data) {
        if (data.getStatus()==0) {
            refreshLayout.finishRefreshing();
            gson = new Gson();
            String s = gson.toJson(data);
            SharedPreferencesUtil.put(mContext,"userCollect",s);
            initData(data);
        }
    }

    private void initData(CollectEntity data) {
        list.clear();
//        list.addAll(data.getData());
        adapter.notifyDataSetChanged();
        adapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent it = new Intent(mContext, CourseDetailsActivity.class);
                it.putExtra("courseId", position);
                startActivity(it);
            }
        });
    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
