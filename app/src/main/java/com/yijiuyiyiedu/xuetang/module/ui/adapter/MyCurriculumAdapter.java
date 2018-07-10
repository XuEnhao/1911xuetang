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
import com.yijiuyiyiedu.xuetang.module.entity.MyCurriculumEntity;
import com.yijiuyiyiedu.xuetang.module.ui.custom.DownloadProgressView;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.JumpUtil;
import com.yijiuyiyiedu.xuetang.utils.LogUtil;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 我的课程列表
 */

public class MyCurriculumAdapter extends BaseQuickAdapter<MyCurriculumEntity.DataBean.CurriculumListBean, BaseViewHolder> {
    public MyCurriculumAdapter(int layoutResId, @Nullable List<MyCurriculumEntity.DataBean.CurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MyCurriculumEntity.DataBean.CurriculumListBean item) {
        helper.setText(R.id.fragment_myCourse_title, item.getTitle()+"");//标题
        DownloadProgressView progressView = helper.getView(R.id.fragment_myDownload_progress);
        progressView.setStrokeColor(Color.parseColor("#6317A5"));
        if (item.getPercent()<100&&item.getPercent()>0){
            if (progressView.getVisibility()==View.GONE){
                progressView.setVisibility(View.VISIBLE);
            }
            TextView textView = helper.getView(R.id.fragment_myCourse_plan);
            textView.setTextColor(Color.parseColor("#787993"));
            helper.setText(R.id.fragment_myCourse_plan, "已学习"+item.getPercent()+"%");//讲师
            ObjectAnimator animator1 = ObjectAnimator.ofInt(progressView, "degree", 0, item.getPercent());
            animator1.setDuration(1000);
            animator1.start();
//            progressView.setDegree(item.getPercent());
        }else if (item.getPercent()==0){
            TextView textView = helper.getView(R.id.fragment_myCourse_plan);
            textView.setTextColor(Color.parseColor("#787993"));
            helper.setText(R.id.fragment_myCourse_plan, "未学习");//讲师
            if (progressView.getVisibility()==View.VISIBLE){
                progressView.setVisibility(View.GONE);
            }
        }else{
            TextView textView = helper.getView(R.id.fragment_myCourse_plan);
            textView.setTextColor(Color.parseColor("#59BE7C"));
            helper.setText(R.id.fragment_myCourse_plan, "已学完");//
            if (progressView.getVisibility()==View.VISIBLE){
                progressView.setVisibility(View.GONE);
            }
        }
        LogUtil.LogD("degree",item.getPercent()+"");

//        progressView.setDegree();

//        //加载图片
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.fragment_myCourse_img));
    }
}
