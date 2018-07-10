package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.CheckedTextView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.ClassEvaluateEntity;
import com.yijiuyiyiedu.xuetang.module.entity.EvaluateTextEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;
import com.yijiuyiyiedu.xuetang.module.ui.custom.RatingBar;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/15.
 * 评价
 */

public class EvaluateTextAdapter extends BaseQuickAdapter<EvaluateTextEntity, BaseViewHolder> {

    public EvaluateTextAdapter(int layoutResId, @Nullable List<EvaluateTextEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final EvaluateTextEntity item) {

        CheckedTextView textView = helper.getView(R.id.item_evaluate_text);
        textView.setText(item.getName());
        if (item.isCheck()){
            textView.setChecked(true);
        }else{
            textView.setChecked(false);
        }
    }
}