package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchNoneEntity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 * 经典课程简介
 */

public class SearchNoneAdapter extends BaseQuickAdapter<SearchNoneEntity.DataBean, BaseViewHolder> {
    public SearchNoneAdapter(int layoutResId, @Nullable List<SearchNoneEntity.DataBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchNoneEntity.DataBean item) {
        helper.setText(R.id.item_newCourse_newCourse_title, item.getTitle());//标题
//        helper.setText(R.id.item_newCourse_newCourse_teacher, "讲师:" + item.getLecturer());//讲师
        helper.setText(R.id.item_newCourse_newCourse_studyNum, item.getStudy_number() + "");//学习人数
//        helper.setText(R.id.item_newCourse_newCourse_grade, "评分：" + item.getScore());//评分
        helper.setText(R.id.item_newCourse_newCourse_price, "¥" + item.getPresent_price());//价格
//        if (item.getIs_member_see().equals("1")){
//            helper.setVisible(R.id.item_newCourse_newCourse_vipSee,true);
//        }else{
            helper.setVisible(R.id.item_newCourse_newCourse_vipSee,false);//会员暂时隐藏
//        }
        //加载图片
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_newCourse_newCourse_img));
    }
}
