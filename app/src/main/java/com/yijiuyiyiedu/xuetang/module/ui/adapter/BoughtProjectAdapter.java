package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.BoughtProjectEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectListEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/8.
 * 项目列表适配器
 */

public class BoughtProjectAdapter extends BaseQuickAdapter<BoughtProjectEntity.DataBean.UserProjectBean, BaseViewHolder> {

    public BoughtProjectAdapter(int layoutResId, @Nullable List<BoughtProjectEntity.DataBean.UserProjectBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, BoughtProjectEntity.DataBean.UserProjectBean item) {
//        helper.getView(R.id.item_custom_project_title);
//        ImageView imageView = helper.getView(R.id.item_custom_project_img);
        //项目名字
        helper.setText(R.id.item_custom_project_title, item.getProject_name());
        //项目图片
        GlideUtils.loadRoundImage(mContext, item.getProject_cover(), (ImageView) helper.getView(R.id.item_custom_project_img));

    }
}
