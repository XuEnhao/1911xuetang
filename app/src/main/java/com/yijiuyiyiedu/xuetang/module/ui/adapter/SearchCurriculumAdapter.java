package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.graphics.Color;
import android.support.annotation.Nullable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.utils.ConvertUtil;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class SearchCurriculumAdapter extends BaseQuickAdapter<SearchCurriCulumEntity.DataBean.SearchCurriculumListBean, BaseViewHolder> {
    public SearchCurriculumAdapter(int layoutResId, @Nullable List<SearchCurriCulumEntity.DataBean.SearchCurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchCurriCulumEntity.DataBean.SearchCurriculumListBean item) {
        helper.setText(R.id.item_newCourse_newCourse_title, item.getTitle()+"");//标题
//        helper.setText(R.id.item_newCourse_newCourse_teacher, "讲师:" + item.getLecturer());//讲师
        helper.setText(R.id.item_newCourse_newCourse_studyNum, item.getStudy_number() + "人学习");//学习人数
//        helper.setText(R.id.item_newCourse_newCourse_grade, "评分：" + item.getScore());//评分
        helper.setText(R.id.item_newCourse_newCourse_price, "¥" + item.getPresent_price());//价格
        helper.setVisible(R.id.item_newCourse_newCourse_vipSee,false);
        //加载图片
        GlideUtils.loadRoundImage(mContext, R.mipmap.item_newcourse_course, (ImageView) helper.getView(R.id.item_newCourse_newCourse_img));
    }

}
