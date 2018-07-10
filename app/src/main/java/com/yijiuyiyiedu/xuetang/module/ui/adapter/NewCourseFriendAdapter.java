package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 合作伙伴
 */

public class NewCourseFriendAdapter extends BaseQuickAdapter<CourseEntity.DataBean.CollaborationEnterpriseListBean, BaseViewHolder> {
    public NewCourseFriendAdapter(int layoutResId, @Nullable List<CourseEntity.DataBean.CollaborationEnterpriseListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseEntity.DataBean.CollaborationEnterpriseListBean item) {
        helper.setText(R.id.home_friend_name, item.getEnterprise_name()+"");//标题
//        //加载图片
        GlideUtils.loadImage(mContext, item.getLogo(), (ImageView) helper.getView(R.id.home_friend_avatar));
    }
}
