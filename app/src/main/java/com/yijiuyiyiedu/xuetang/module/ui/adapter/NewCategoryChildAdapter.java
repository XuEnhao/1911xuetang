package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 新版二级分类
 */

public class NewCategoryChildAdapter extends BaseQuickAdapter<NewCategoryEntity.DataBean.CategoryListBean.ChildListBean, BaseViewHolder> {


    public NewCategoryChildAdapter(int layoutResId, @Nullable List<NewCategoryEntity.DataBean.CategoryListBean.ChildListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, NewCategoryEntity.DataBean.CategoryListBean.ChildListBean item) {
        helper.setText(R.id.item_newCategoryContent, "#" + item.getCategory_name());

    }
}
