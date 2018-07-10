package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.VideoCatelogEntity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/22.
 * 课程详情课程小结
 */

public class SeeVideoAdapter extends BaseQuickAdapter<VideoCatelogEntity.DataBean,BaseViewHolder> {
    public SeeVideoAdapter(int layoutResId, @Nullable List<VideoCatelogEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, VideoCatelogEntity.DataBean item) {
        ImageView see = helper.getView(R.id.see_video_see);//see
        ImageView lock = helper.getView(R.id.see_video_lock);//锁
        if (((CourseDetailsActivity)mContext).play==1){
            see.setVisibility(View.GONE);
            lock.setVisibility(View.GONE);
        }else if (item.getLook_at().equals("2")){//是否可以试看  1 可以 2 不可以",
            see.setVisibility(View.VISIBLE);
            lock.setVisibility(View.GONE);
        }else{
            see.setVisibility(View.GONE);
            lock.setVisibility(View.VISIBLE);
        }

        TextView textView = helper.getView(R.id.see_video_text);
        if (item.getColor().equals("白")){
            textView.setBackgroundResource(R.drawable.shape_see_video_item);
        }else{//正在播放
            textView.setBackgroundResource(R.drawable.shape_see_video_item2);
        }
        helper.setText(R.id.see_video_text,item.getVideo_number());
    }
}
