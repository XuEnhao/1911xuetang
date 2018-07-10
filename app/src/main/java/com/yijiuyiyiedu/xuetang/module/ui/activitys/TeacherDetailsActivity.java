package com.yijiuyiyiedu.xuetang.module.ui.activitys;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseActivity;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.TeacherDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.ui.adapter.TeacherCourseAdapter;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.ToastUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;
import com.yijiuyiyiedu.xuetang.utils.titlebar.StatusBarUtil;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by xuenhao on 2018/6/4.
 * 名师详情
 */

public class TeacherDetailsActivity extends BaseActivity {
    @BindView(R.id.teacher_details_avatar)
    CircleImageView avatar;//头像
    @BindView(R.id.teacher_details_teacherName)
    TextView name;
    @BindView(R.id.teacher_details_teacherTitle)
    TextView teacherTitle;
    @BindView(R.id.teacher_details_courseNumber)
    TextView courseNumber;
    @BindView(R.id.teacher_details_personNumber)
    TextView personNumber;
    @BindView(R.id.teacher_details_content)
    TextView content;
    @BindView(R.id.teacher_details_recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.teacher_details_statusBar)
    View statusBar;
    private Context mContext;
    private ArrayList<TeacherDetailsEntity.DataBean.CurriculumListBean> list;
    private TeacherCourseAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_details);
        ButterKnife.bind(this);
        mContext = this;
        statusBar();
        initView();
    }

    private void statusBar() {
        StatusBarUtil.setTranslucentForImageView((Activity) mContext, 0, null);
        int statusBarHeight = StatusBarUtil.getStatusBarHeight(mContext);
        ViewGroup.LayoutParams layoutParams = statusBar.getLayoutParams();
        layoutParams.height = statusBarHeight;
    }

    /**
     * 初始化布局
     */
    private void initView() {
        recyclerView.setNestedScrollingEnabled(false);//滑动粘连
        recyclerView.setLayoutManager(new GridLayoutManager(mContext,2));
        mAdapter = new TeacherCourseAdapter(R.layout.item_new_course_new_course, list);
        recyclerView.setAdapter(mAdapter);//设置适配器
        mAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                TeacherDetailsEntity.DataBean.CurriculumListBean item = (TeacherDetailsEntity.DataBean.CurriculumListBean) adapter.getItem(position);
                Intent it = new Intent(mContext,CourseDetailsActivity.class);
                it.putExtra("courseId",item.getId());
                startActivity(it);
            }
        });
        loadData();

    }

    /**
     * 请求数据
     */
    private void loadData() {
        OkGo.<TeacherDetailsEntity>get(Constant.TEACHER_DETAILS)
                .tag(this)
                .params("teacher_id", getIntent().getStringExtra("teacherId"))
                .execute(new JsonCallback<TeacherDetailsEntity>() {
                    @Override
                    public void onSuccess(Response<TeacherDetailsEntity> response) {
                        TeacherDetailsEntity body = response.body();
                        if (body.getStatus() == 0) {
                            initData(body.getData());//初始化数据
                        } else {
                            ToastUtils.showShort(body.getMsg());
                        }
                    }
                });
    }

    private void initData(TeacherDetailsEntity.DataBean data) {
        GlideUtils.loadImage(mContext, data.getHead_img(), avatar);//老师头像
        name.setText(data.getTeacher_name());//老师名字
        content.setText(data.getContent());//简介
        courseNumber.setText(data.getCurriculumList().size()+"门课程");
        personNumber.setText(data.getStudy_number()+"学员");
        teacherTitle.setText(data.getGraduate());//称谓
        mAdapter.setNewData(data.getCurriculumList());//设置数据

    }

    @OnClick(R.id.teacher_details_back)
    public void onViewClicked() {
        JumpUtil.overiderAnimsition(this);
    }
}
