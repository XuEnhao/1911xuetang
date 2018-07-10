package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 分类一级列表adapter
 */

public class CategoryAdapter extends BaseQuickAdapter<NewCategoryEntity.DataBean.CategoryListBean, BaseViewHolder> {
    public CategoryAdapter(int layoutResId, @Nullable List<NewCategoryEntity.DataBean.CategoryListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewCategoryEntity.DataBean.CategoryListBean item) {
        TextView title = helper.getView(R.id.item_category_title);//分类名字
        title.setText(item.getCategory_name());//分类名字
        if (item.isCheck()){
            helper.setVisible(R.id.item_category_cursor,true);
            LinearLayout linearLayout = helper.getView(R.id.item_category_liner);
            linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            title.setTextColor(Color.parseColor("#6317A5"));
        }else{
            helper.getView(R.id.item_category_cursor).setVisibility(View.INVISIBLE);
            LinearLayout linearLayout = helper.getView(R.id.item_category_liner);
            linearLayout.setBackgroundColor(Color.parseColor("#F2F3F7"));
            title.setTextColor(Color.parseColor("#25265E"));
        }

    }
}
