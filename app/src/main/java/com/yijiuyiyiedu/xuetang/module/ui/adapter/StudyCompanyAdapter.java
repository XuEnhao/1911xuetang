package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.StudyEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/13.
 */
// company
public class StudyCompanyAdapter extends BaseQuickAdapter<StudyEntity.DataBean, BaseViewHolder> {

    //    R.id.item_study_classImg  课程图
//    R.id.item_study_className  课程名字
//    R.id.item_study_classSchedule 课程进度 看到第几节
//    R.id.item_study_linear linear
    private int userType;

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public StudyCompanyAdapter(int layoutResId, @Nullable List<StudyEntity.DataBean> data) {
        super(layoutResId, data);
    }


    @Override
    protected void convert(BaseViewHolder helper, StudyEntity.DataBean item) {
//        if (userType == 2) {
//            helper.setText(R.id.item_study_className, item.getTitle());//课程名字
//            helper.getView(R.id.item_study_classSchedule).setVisibility(View.GONE);
//            helper.getView(R.id.item_study_numLinear).setVisibility(View.VISIBLE);
//            TextView bought = (TextView) helper.getView(R.id.item_study_bought);
//            bought.setText("已购:" + item.getEnterpriseCurriculumTotal() + "人");
//            TextView newAdd = (TextView) helper.getView(R.id.item_study_newAdd);
//            bought.setText("新增:" + item.getNewEnterpriseUser() + "人");
//            GlideUtils.loadImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_study_classImg));
//            Button buyBtn = helper.getView(R.id.study_buyBtn);
//            if (item.getNewEnterpriseUser() > 0) {
//                buyBtn.setVisibility(View.VISIBLE);
//            } else {
//                buyBtn.setVisibility(View.VISIBLE);
//                buyBtn.setBackgroundColor(Color.parseColor("#eeeeee"));
//                buyBtn.setTextColor(Color.parseColor("#999999"));
//            }
//        } else {
//            helper.setText(R.id.item_study_className, item.getTitle());//课程名字
//            helper.setText(R.id.item_study_classSchedule, item.getSee_title());//课程进度
//            GlideUtils.loadImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_study_classImg));
//
//        }

    }

}
