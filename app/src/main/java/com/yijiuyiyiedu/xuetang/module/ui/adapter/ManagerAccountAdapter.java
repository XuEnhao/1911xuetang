package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.ManagerAccountEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/21.
 * 企业管理页面的适配器
 */

public class ManagerAccountAdapter extends BaseQuickAdapter<ManagerAccountEntity.DataBean.ChildUserBean, BaseViewHolder> {
    public ManagerAccountAdapter(int layoutResId, @Nullable List<ManagerAccountEntity.DataBean.ChildUserBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, ManagerAccountEntity.DataBean.ChildUserBean item) {
        TextView name = helper.getView(R.id.item_manager_son_account_name);
        name.setText(item.getReal_name());
        TextView id = helper.getView(R.id.item_manager_son_account_number);
        id.setText(item.getId());
        TextView phone = helper.getView(R.id.item_manager_son_account_phone);
        phone.setText(item.getUser_name());

    }

}
