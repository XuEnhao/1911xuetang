package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/13.
 */

public class StudyPersonalAdapter extends BaseQuickAdapter<StudyEntity.DataBean.UserCurriculumBean, BaseViewHolder> {

    //    R.id.item_study_classImg  课程图
//    R.id.item_study_className  课程名字
//    R.id.item_study_classSchedule 课程进度 看到第几节
//    R.id.item_study_linear linear
    public StudyPersonalAdapter(int layoutResId, @Nullable List<StudyEntity.DataBean.UserCurriculumBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, StudyEntity.DataBean.UserCurriculumBean item) {
        helper.setText(R.id.item_study_className, item.getTitle());//课程名字
        helper.setText(R.id.item_study_classSchedule, item.getSee_title() + "1");//课程进度
        ImageView imageView = helper.getView(R.id.item_study_classImg);
        GlideUtils.loadImage(mContext, item.getPicture(), imageView,
                UtilDpOrPx.dip2px(mContext, 113), UtilDpOrPx.dip2px(mContext, 113));
    }

}
