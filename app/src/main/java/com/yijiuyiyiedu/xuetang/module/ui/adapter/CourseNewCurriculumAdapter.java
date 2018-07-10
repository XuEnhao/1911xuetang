package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/19.
 * 分类适配器
 */

public class CourseNewCurriculumAdapter extends BaseQuickAdapter<CourseEntity.DataBean.NewsCurriculumListBean, BaseViewHolder> {

    public CourseNewCurriculumAdapter(int layoutResId, @Nullable List<CourseEntity.DataBean.NewsCurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseEntity.DataBean.NewsCurriculumListBean item) {
        helper.setText(R.id.item_course_newCurriculumName, item.getTitle());//课程名字
        helper.setText(R.id.item_course_newCurriculumNum, "学习人数：" + item.getStudy_number() + "人");//课程学习人数
        helper.setText(R.id.item_course_newCurriculumPrice, item.getPresent_price());//课程费用
//        GlideUtils.loadImage(mContext, item.getPicture(),
//                (ImageView) helper.getView(R.id.item_course_newCurriculumImg));//后面的主图
//        GlideUtils.loadRoundImage(mContext, item.getPicture(),
//                (RoundedImageView) helper.getView(R.id.item_course_newCurriculumImg));//后面的主图
//        Glide.with(mContext).load(Constant.BASE_URL+item.getPicture()).transform(new GlideRoundTransform(mContext,10)).into((ImageView) helper.getView(R.id.item_course_newCurriculumImg));

        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_course_newCurriculumImg));

        LogUtil.LogD("tag", "convert: " + Constant.BASE_URL + item.getPicture());
    }


}
