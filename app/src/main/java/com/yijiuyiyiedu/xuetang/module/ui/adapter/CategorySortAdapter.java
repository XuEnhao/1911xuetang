package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CategorySortEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 分类一级列表adapter
 */

public class CategorySortAdapter extends BaseQuickAdapter<CategorySortEntity, BaseViewHolder> {
    public CategorySortAdapter(int layoutResId, @Nullable List<CategorySortEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategorySortEntity item) {
        TextView textView = helper.getView(R.id.category_sort_text);
        textView.setText(item.getTitle());
        if (item.isCheck()){
            textView.setBackgroundResource(R.drawable.shape_record_give);
            textView.setTextColor(Color.parseColor("#6317A5"));
        }else{
            textView.setBackgroundResource(0);
            textView.setTextColor(Color.parseColor("#25265E"));
        }

    }
}
