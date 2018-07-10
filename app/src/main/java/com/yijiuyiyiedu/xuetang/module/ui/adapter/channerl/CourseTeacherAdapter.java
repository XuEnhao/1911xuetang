package com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/20.
 * 名师推荐
 */

public class CourseTeacherAdapter extends BaseQuickAdapter<CourseEntity.DataBean.RecommendCurriculumListBean, BaseViewHolder> {
    public CourseTeacherAdapter(int layoutResId, @Nullable List<CourseEntity.DataBean.RecommendCurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseEntity.DataBean.RecommendCurriculumListBean item) {
        helper.setText(R.id.item_course_newCurriculumName, item.getTitle());//课程名字
        helper.setText(R.id.item_course_newCurriculumNum, "学习人数：" + item.getStudy_number() + "人");//学习人数
        helper.setText(R.id.item_course_newCurriculumPrice, item.getPresent_price());//价钱
//        GlideUtils.loadImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_course_newCurriculumImg));
//        Glide.with(mContext).load(Constant.BASE_URL+item.getPicture()).transform(new GlideRoundTransform(mContext,10)).into((ImageView) helper.getView(R.id.item_course_newCurriculumImg));
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_course_newCurriculumImg));
    }
}
