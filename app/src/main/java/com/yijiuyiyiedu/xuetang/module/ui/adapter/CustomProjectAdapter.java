package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectListEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.Collection;
import java.util.List;

/**
 * Created by xuenhao on 2018/4/8.
 * 项目列表适配器
 */

public class CustomProjectAdapter extends BaseQuickAdapter<ProjectListEntity.DataBean.ProjectListBean, BaseViewHolder> {

    public CustomProjectAdapter(int layoutResId, @Nullable List<ProjectListEntity.DataBean.ProjectListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ProjectListEntity.DataBean.ProjectListBean item) {
//        helper.getView(R.id.item_custom_project_title);
//        ImageView imageView = helper.getView(R.id.item_custom_project_img);
        //项目名字
        helper.setText(R.id.item_custom_project_title, item.getProject_name());
        //项目图片
        GlideUtils.loadRoundImage(mContext, item.getProject_cover(), (ImageView) helper.getView(R.id.item_custom_project_img));

    }
}
