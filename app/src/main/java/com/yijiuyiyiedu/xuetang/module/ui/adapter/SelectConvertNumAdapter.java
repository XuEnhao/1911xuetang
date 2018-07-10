package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.SelectConvertNumEntity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/21.
 * 查看兑换码适配器
 */

public class SelectConvertNumAdapter extends BaseQuickAdapter<SelectConvertNumEntity.DataBean.OrderRandomListBean,BaseViewHolder> {
    public SelectConvertNumAdapter(int layoutResId, @Nullable List<SelectConvertNumEntity.DataBean.OrderRandomListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SelectConvertNumEntity.DataBean.OrderRandomListBean item) {
//        helper.setIsRecyclable(false);//关闭复用
        int i = getParentPosition(item)+1;

        int i1 = helper.getPosition() + 1;
        if (getParentPosition(item)<9){
            helper.setText(R.id.item_selectNum_number,"0"+i1+">");
        }else{
            helper.setText(R.id.item_selectNum_number,i1+">");
        }
        String code = item.getCode();
        String str1 = code.substring(0, 4);
        String str2 = code.substring(4, 8);
        String str3 = code.substring(8, 12);
        String str4 = code.substring(12, 16);
        helper.setText(R.id.item_selectNum_code,str1+" "+str2+" "+str3+" "+str4);

    }
}
