package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewCourseEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 最新课程简介
 */

public class NewCurriculumAdapter extends BaseQuickAdapter<NewCourseEntity.DataBean.CurriculumListBean, BaseViewHolder> {
    public NewCurriculumAdapter(int layoutResId, @Nullable List<NewCourseEntity.DataBean.CurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewCourseEntity.DataBean.CurriculumListBean item) {
        helper.setText(R.id.item_newCurriculum_title, item.getTitle()+"");//标题
//        helper.setText(R.id.item_newCourse_newCourse_teacher, "讲师:" + item.getLecturer());//讲师
        helper.setText(R.id.item_newCurriculum_number, item.getStudy_number());//学习人数
//        helper.setText(R.id.item_newCourse_newCourse_grade, "评分：" + item.getScore());//评分
        helper.setText(R.id.item_newCurriculum_price, "¥" + item.getPresent_price());//价格
        //加载图片
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_newCurriculum_img));
    }
}
