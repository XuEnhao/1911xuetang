package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 已购课程简介
 */

public class BoughtCourseAdapter extends BaseQuickAdapter<StudyEntity.DataBean.UserCurriculumBean, BaseViewHolder> {
    public BoughtCourseAdapter(int layoutResId, @Nullable List<StudyEntity.DataBean.UserCurriculumBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, StudyEntity.DataBean.UserCurriculumBean item) {
        helper.setText(R.id.item_newCourse_newCourse_title, " " + item.getTitle());//标题
//        helper.setText(R.id.item_newCourse_newCourse_teacher, "讲师:" + item.getLecturer());//讲师
        helper.setVisible(R.id.item_newCourse_newCourse_studyNum, false);//学习人数
//        helper.setVisible(R.id.item_newCourse_newCourse_grade, false);//评分
        helper.setVisible(R.id.item_newCourse_newCourse_price, false);//价格
        //加载图片
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_newCourse_newCourse_img));
    }
}
