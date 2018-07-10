package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 新版分类二级适配器
 */

public class NewCategorySecondAdapter extends BaseQuickAdapter<CategoryEntity.DataBean, BaseViewHolder> {
    public NewCategorySecondAdapter(int layoutResId, @Nullable List<CategoryEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryEntity.DataBean item) {
//        helper.setText(R.id.item_newCategorySecond_title, item.getTitle());
////        helper.setText(R.id.item_newCategorySecond_price,"¥"+item.getPresent_price());
//        //讲师
//
//        helper.setText(R.id.item_newCategorySecond_studyNum,   item.getStudy_number()+"人学习");
//        helper.setText(R.id.item_newCategorySecond_score, "评分:" + item.getScore());
//        helper.setText(R.id.item_newCategorySecond_lecture, "讲师:" + item.getLecturer());
//        helper.setText(R.id.item_newCategorySecond_price,item.getPresent_price());
//        //加载图片
////        GlideUtils.loadImage(mContext,item.getPicture(), (ImageView) helper.getView(R.id.item_newCategorySecond_img));
//        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_newCategorySecond_img));

    }
}
