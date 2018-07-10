package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.NewCategorySecondActivity;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by xuenhao on 2018/4/3.
 * 新版分类
 */

public class NewCategoryAdapter extends BaseQuickAdapter<NewCategoryEntity.DataBean.CategoryListBean, BaseViewHolder> {


    public NewCategoryAdapter(int layoutResId, @Nullable List<NewCategoryEntity.DataBean.CategoryListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final NewCategoryEntity.DataBean.CategoryListBean item) {
        helper.setText(R.id.item_newCategoryTitle, item.getCategory_name());
        final List<String> tags = new ArrayList<>();
        for (int i = 0; i < item.getChildList().size(); i++) {
            tags.add(item.getChildList().get(i).getCategory_name());
        }
        final TagFlowLayout mFlowLayout = helper.getView(R.id.id_flowlayout);

        mFlowLayout.setAdapter(new TagAdapter<String>(tags) {
            @Override
            public View getView(FlowLayout parent, int position, String s) {
                View view = LayoutInflater.from(mContext).inflate(R.layout.item_new_category_content, mFlowLayout, false);
                TextView textView = view.findViewById(R.id.item_newCategoryContent);
                textView.setText(s);
                return view;
            }
        });

        mFlowLayout.setOnTagClickListener(new TagFlowLayout.OnTagClickListener() {
            @Override
            public boolean onTagClick(View view, int position, FlowLayout parent) {
                //跳转分类二级页面
                Intent it = new Intent(mContext, NewCategorySecondActivity.class);
                it.putExtra("category_id", item.getChildList().get(position).getId());
                it.putExtra("title",tags.get(position));
                mContext.startActivity(it);
                return true;
            }
        });

    }
}
