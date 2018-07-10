package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/17.
 * //首页项目适配器
 */

public class NewCourseProjectAdapter extends BaseQuickAdapter<CourseEntity.DataBean.NewsCurriculumListBean, BaseViewHolder> {
    public NewCourseProjectAdapter(int layoutResId, @Nullable List<CourseEntity.DataBean.NewsCurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseEntity.DataBean.NewsCurriculumListBean item) {
        //加载圆角图片
        GlideUtils.loadRoundImage(mContext, item.getPicture(),
                (ImageView) helper.getView(R.id.item_newCourse_project_img));
        helper.setText(R.id.item_newCourse_project_title, item.getTitle());
    }
}
