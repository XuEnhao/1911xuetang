package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.MyCurriculumEntity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.DownloadProgressView;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 最近学习
 */

public class LateStudyAdapter extends BaseQuickAdapter<MyCurriculumEntity.DataBean.CurriculumListBean, BaseViewHolder> {
    public LateStudyAdapter(int layoutResId, @Nullable List<MyCurriculumEntity.DataBean.CurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MyCurriculumEntity.DataBean.CurriculumListBean item) {
        helper.setText(R.id.item_lateStudy_back_title, item.getTitle()+"");//标题
        helper.setText(R.id.item_lateStudy_back_plan, "已学习:"+item.getPercent()+"%");//标题
        helper.getView(R.id.item_lateStudy_back_continue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(mContext, CourseDetailsActivity.class);
                it.putExtra("courseId",item.getId());
                mContext.startActivity(it);
            }
        });
//        //加载图片
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_lateStudy_back_img));
    }
}
