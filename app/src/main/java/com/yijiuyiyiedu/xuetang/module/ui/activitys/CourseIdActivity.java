package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.lcodecore.tkrefreshlayout.RefreshListenerAdapter;
import com.lcodecore.tkrefreshlayout.TwinklingRefreshLayout;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseIdBus;
import com.yijiuyiyiedu.xuetang.module.entity.CourseIdEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CourseIdPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.CourseIdAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.LoadView;
import com.yijiuyiyiedu.xuetang.module.view.CourseIdView;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.WindowUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by xuenhao on 2018/6/13.
 * 课程id
 */

public class CourseIdActivity extends BaseActivity implements CourseIdView {
    @BindView(R.id.courseId_back)
    ImageView back;
    @BindView(R.id.courseId_add)
    ImageView add;
    @BindView(R.id.courseId_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.courseId_refreshLayout)
    TwinklingRefreshLayout refreshLayout;//下拉刷新
    private Context mContext;
    protected CourseIdPresenter mPrsenter;
    private CourseIdAdapter mAdapter;
    private ArrayList<CourseIdEntity.DataBean.BindListBean> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_id);
        ButterKnife.bind(this);
        mContext = this;
        EventBus.getDefault().register(this);
        initView();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getCourseIdBus(CourseIdBus item) {
        if (mPrsenter != null) {
            mPrsenter.getCourseListData();
        } else {
//            UtilToast.showToast(mContext, "presenter为空");
            LogUtil.LogE("tag", getClass().getSimpleName() + ":presenter为空");
        }
    }

    private void initView() {
        list = new ArrayList<>();
        mAdapter = new CourseIdAdapter(R.layout.item_course_id, list);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(mAdapter);
        View emptyView = View.inflate(mContext, R.layout.activity_course_id_empty, null);
        ImageView emptyImg = emptyView.findViewById(R.id.empty_img);
        emptyImg.setImageResource(R.mipmap.empty_id);
        mAdapter.setEmptyView(emptyView);

        refreshLayout.setHeaderView(new LoadView(mContext));
        refreshLayout.setEnableRefresh(true);
        refreshLayout.setEnableLoadmore(false);
        refreshLayout.setOnRefreshListener(new RefreshListenerAdapter() {
            @Override
            public void onRefresh(TwinklingRefreshLayout refreshLayout) {
                super.onRefresh(refreshLayout);
                mPrsenter.getCourseListData();
            }
        });

        mPrsenter = new CourseIdPresenter(this);
        mPrsenter.getCourseListData();
        //返回
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.overiderAnimsition((Activity) mContext);
            }
        });
        //添加
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JumpUtil.jump(mContext, AddCourseIdActivity.class);
            }
        });
    }

    @Override
    protected void onDestroy() {
        WindowUtils.hintKeyboard(this);
        super.onDestroy();
    }

    @Override
    public void showCourseList(CourseIdEntity data) {
        refreshLayout.finishRefreshing();
        if (data.getStatus() == 0) {
            if (data.getData().getBindList().size() != 0) {
                mAdapter.setNewData(data.getData().getBindList());
                mAdapter.setFooterView(View.inflate(mContext, R.layout.footer_course_id, null));
            } else {
                mAdapter.setNewData(data.getData().getBindList());
                mAdapter.removeFooterView(View.inflate(mContext, R.layout.footer_course_id, null));
            }
        } else if (data.getStatus() == 100008) {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("您的账号已在另一台设备登录，您被迫下线");
            builder.setTitle("被迫下线");
            builder.setPositiveButton("去登录", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferencesUtil.put(mContext, "token", "");//清除用户数据
                    UriConstant.clear();
                    JumpUtil.jump(mContext, PassLoginActivity.class);
                    dialog.dismiss();
                }
            });
            //不关闭写法
            builder.setCancelable(false);
            AlertDialog alertDialog = builder.create();
            builder.show();
        } else {
            ToastUtils.showShort(data.getMsg());
        }
    }
}
