package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.VipListEntity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/19.
 * 会员充值页面 会员条目
 */

public class BuyVipAdapter extends BaseQuickAdapter<VipListEntity.DataBean.RechargeListBean,BaseViewHolder> {
    public BuyVipAdapter(int layoutResId, @Nullable List<VipListEntity.DataBean.RechargeListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VipListEntity.DataBean.RechargeListBean item) {
        TextView title = helper.getView(R.id.item_buyVip_title);//标题
        TextView price = helper.getView(R.id.item_buyVip_price);//价格
        LinearLayout linearLayout = helper.getView(R.id.item_buyVip_linear);
        helper.setText(R.id.item_buyVip_title,item.getRecharge_name());
        helper.setText(R.id.item_buyVip_price,"¥"+item.getPrice());
        if (item.getIsCheck().equals("紫")){
            linearLayout.setBackgroundResource(R.drawable.shape_buy_vip_item2);
//            linearLayout.setBackgroundColor(Color.parseColor("#773685"));
            title.setTextColor(Color.parseColor("#ffffff"));
            price.setTextColor(Color.parseColor("#ffffff"));
        }else{
            linearLayout.setBackgroundResource(R.drawable.shape_buy_vip_item);
//            linearLayout.setBackgroundColor(Color.parseColor("#FFFFFF"));
            title.setTextColor(Color.parseColor("#666666"));
            price.setTextColor(Color.parseColor("#666666"));
        }
    }
}
