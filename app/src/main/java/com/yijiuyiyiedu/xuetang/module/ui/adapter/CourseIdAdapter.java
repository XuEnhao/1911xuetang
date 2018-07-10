package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseIdEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 已绑定课程id列表
 */

public class CourseIdAdapter extends BaseQuickAdapter<CourseIdEntity.DataBean.BindListBean, BaseViewHolder> {
    public CourseIdAdapter(int layoutResId, @Nullable List<CourseIdEntity.DataBean.BindListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseIdEntity.DataBean.BindListBean item) {
        helper.setText(R.id.item_courseId_number,item.getInvitation_code());
    }
}
