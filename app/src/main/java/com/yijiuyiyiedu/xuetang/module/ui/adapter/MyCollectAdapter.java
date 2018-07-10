package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CollectEntity;
import com.yijiuyiyiedu.xuetang.module.entity.DelCollectCourseBus;
import com.yijiuyiyiedu.xuetang.module.entity.MyCurriculumEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.DownloadProgressView;
import com.yijiuyiyiedu.xuetang.module.ui.fragments.MyCourseMyCollectFragment;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 我的收藏列表
 */

public class MyCollectAdapter extends BaseQuickAdapter<CollectEntity.DataBean.UserCollectionBean, BaseViewHolder> {
    public MyCollectAdapter(int layoutResId, @Nullable List<CollectEntity.DataBean.UserCollectionBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final CollectEntity.DataBean.UserCollectionBean item) {
        helper.setText(R.id.fragment_myCollect_title, item.getTitle()+"");//标题
//        if (item.getPercent()!=100){
//            TextView textView = helper.getView(R.id.fragment_myCourse_plan);
//            textView.setTextColor(Color.parseColor("#787993"));
//            helper.setText(R.id.fragment_myCourse_plan, "已学习"+item.getPercent()+"%");//讲师
//            ObjectAnimator animator1 = ObjectAnimator.ofFloat(progressView, "degree", 0, item.getPercent());
//            animator1.setDuration(1000);
//            animator1.setStartDelay(500);
//            animator1.start();
//        }else{
//            TextView textView = helper.getView(R.id.fragment_myCourse_plan);
//            textView.setTextColor(Color.parseColor("#59BE7C"));
//            helper.setText(R.id.fragment_myCourse_plan, "已学完");//
//        }
        helper.getView(R.id.fragment_myCollect_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new DelCollectCourseBus(item.getId()));
            }
        });

//        //加载图片
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.fragment_myCollect_img));
    }
}
