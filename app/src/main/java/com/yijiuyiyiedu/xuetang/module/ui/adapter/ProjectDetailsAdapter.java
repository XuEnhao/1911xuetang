package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.makeramen.roundedimageview.RoundedTransformationBuilder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.MainActivity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 项目详情 内容适配器
 */

public class ProjectDetailsAdapter extends BaseQuickAdapter<ProjectDetailsEntity.DataBean.ProjectCurriculumListBean, BaseViewHolder> {
    public ProjectDetailsAdapter(int layoutResId, @Nullable List<ProjectDetailsEntity.DataBean.ProjectCurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectDetailsEntity.DataBean.ProjectCurriculumListBean item) {
        helper.setText(R.id.item_newCategorySecond_title, item.getTitle());
//        helper.setText(R.id.item_newCategorySecond_price,"¥"+item.getPresent_price());
        //讲师
        helper.setText(R.id.item_newCategorySecond_studyNum, "讲师:" + item.getLecturer());
        //加载图片
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_newCategorySecond_img));

    }
}
