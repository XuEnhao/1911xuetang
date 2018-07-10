package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.PositionEntity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 职业列表
 */

public class PositionAdapter extends BaseQuickAdapter<PositionEntity.DataBean, BaseViewHolder> {
    public PositionAdapter(int layoutResId, @Nullable List<PositionEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PositionEntity.DataBean item) {
        if (item.isCheck()){
            helper.getView(R.id.item_position_select).setVisibility(View.VISIBLE);
        }else{
            helper.getView(R.id.item_position_select).setVisibility(View.GONE);
        }
        helper.setText(R.id.item_position_name,item.getPosition_name());

    }
}
