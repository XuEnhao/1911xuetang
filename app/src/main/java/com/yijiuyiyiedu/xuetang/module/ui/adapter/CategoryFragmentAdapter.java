package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.utils.ConvertUtil;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/21.
 * 分类详情列表
 */

public class CategoryFragmentAdapter extends BaseQuickAdapter<CategoryEntity.DataBean.CurriculumListBean, BaseViewHolder> {
    public CategoryFragmentAdapter(int layoutResId, @Nullable List<CategoryEntity.DataBean.CurriculumListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, CategoryEntity.DataBean.CurriculumListBean item) {
        helper.setText(R.id.item_newCourse_newCourse_title, item.getTitle()+"");//标题
//        helper.setText(R.id.item_newCourse_newCourse_teacher, "讲师:" + item.getLecturer());//讲师
        helper.setText(R.id.item_newCourse_newCourse_studyNum, item.getStudy_number() + "");//学习人数
        helper.setVisible(R.id.item_newCourse_newCourse_vipSee, false);//new 标志
        helper.setText(R.id.item_newCourse_newCourse_price, "¥" + item.getPresent_price());//价格
        //加载图片
        GlideUtils.loadRoundImage(mContext,item.getPicture(), (ImageView) helper.getView(R.id.item_newCourse_newCourse_img));
    }

}
