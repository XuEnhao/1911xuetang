package com.yijiuyiyiedu.xuetang.module.ui.adapter.channerl;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.MsgManagerEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/22.
 * 消息管理的适配器
 */

public class MsgManagerAdapter extends BaseQuickAdapter<MsgManagerEntity.DataBean.MsgListBean, BaseViewHolder> {
    public MsgManagerAdapter(int layoutResId, @Nullable List<MsgManagerEntity.DataBean.MsgListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MsgManagerEntity.DataBean.MsgListBean item) {
        TextView sentTime = helper.getView(R.id.item_msg_manager_time);
        sentTime.setText(item.getSend_time());//设置时间
        TextView content = helper.getView(R.id.item_msg_manager_content);
        content.setText(item.getMessage());//设置消息内容
    }
}
