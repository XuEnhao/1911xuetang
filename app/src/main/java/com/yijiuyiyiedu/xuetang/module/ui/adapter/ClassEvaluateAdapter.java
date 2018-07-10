package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.entity.ClassEvaluateEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.CircleImageView;
import com.yijiuyiyiedu.xuetang.module.ui.custom.RatingBar;
import com.yijiuyiyiedu.xuetang.utils.ConvertUtil;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/15.
 * 课程目录标题下的子条目
 */

public class ClassEvaluateAdapter extends BaseQuickAdapter<ClassEvaluateEntity.DataBean.EvaluateListBean, BaseViewHolder> {

    public ClassEvaluateAdapter(int layoutResId, @Nullable List<ClassEvaluateEntity.DataBean.EvaluateListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final ClassEvaluateEntity.DataBean.EvaluateListBean item) {
        TextView userName = helper.getView(R.id.item_eva_userName);
        userName.setText(item.getNick_name());//昵称
        GlideUtils.loadImage(mContext, item.getHead_img(), (CircleImageView) helper.getView(R.id.item_eva_userAvatar));//用户头像
        LogUtil.LogD("img", "convert: "+item.getHead_img());
        TextView time = helper.getView(R.id.item_eva_evaTime);
        time.setText(item.getCreate_time());//评论时间
        TextView content = helper.getView(R.id.item_eva_evaContent);
        content.setText(item.getEvaluate_content());//评论内容
        int i = Integer.parseInt(item.getScore());
        RatingBar ratingBar = helper.getView(R.id.item_eva_ratingBar);
        ratingBar.setClickable(false);
        ratingBar.setStar(i);
    }
}