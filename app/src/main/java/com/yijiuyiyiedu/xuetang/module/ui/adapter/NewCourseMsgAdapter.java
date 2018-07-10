package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewsEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.TimeUtil;

import java.util.List;

import static com.yijiuyiyiedu.xuetang.utils.TimeUtil.dateFormatYMDHMS;

/**
 * Created by xuenhao on 2018/4/3.
 * 学堂资讯
 */

public class NewCourseMsgAdapter extends BaseQuickAdapter<CourseEntity.DataBean.NewsListBean, BaseViewHolder> {
    public NewCourseMsgAdapter(int layoutResId, @Nullable List<CourseEntity.DataBean.NewsListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CourseEntity.DataBean.NewsListBean item) {
        helper.setText(R.id.item_newCourse_msg_title, item.getTitle());//标题
        helper.setText(R.id.item_newCourse_msg_time, TimeUtil.formatData(dateFormatYMDHMS,Long.parseLong(item.getCreate_time())));//时间
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_newCourse_msg_img));
    }
}
