package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 名师大咖
 */

public class NewCourseTeacherAdapter extends BaseQuickAdapter<CourseEntity.DataBean.TeacherListBean, BaseViewHolder> {
    public NewCourseTeacherAdapter(int layoutResId, @Nullable List<CourseEntity.DataBean.TeacherListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseEntity.DataBean.TeacherListBean item) {
        helper.setText(R.id.teacher_name, item.getTeacher_name());//老师名字
        helper.setText(R.id.teacher_content, "讲师:" + item.getContent());//讲师
//        //加载图片
        GlideUtils.loadImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.teacher_avatar));
    }
}
