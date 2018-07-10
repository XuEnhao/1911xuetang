package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchRecommendEntity;
import com.yijiuyiyiedu.xuetang.utils.ConvertUtil;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class NewSearchCurriculumAdapter extends BaseQuickAdapter<SearchRecommendEntity, BaseViewHolder> {
    public NewSearchCurriculumAdapter(int layoutResId, @Nullable List<SearchRecommendEntity> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, SearchRecommendEntity item) {
        helper.setText(R.id.item_category_courseTitle, item.getTitle());//设置标题
        helper.setText(R.id.item_category_courseVolume, "讲师:"+item.getLecturer());//设置讲师
        helper.setText(R.id.item_category_coursePrice, item.getPresent_price());//设置价格
        helper.setText(R.id.item_category_courseGrade, "评分:"+item.getScore());//设置评分
        helper.setText(R.id.item_category_courseNum,item.getStudy_number()+"人学习");//学习人数
        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_category_courseImg));//设置图片
//        helper.setText(R.id.item_category_courseTitle, item.getTitle());//设置标题
//        helper.setText(R.id.item_category_courseVolume, "销量:" + item.getStudy_number() + "份");//设置销量
//        helper.setText(R.id.item_category_coursePrice, item.getPresent_price());//设置价格
//        helper.setText(R.id.item_category_courseGrade, item.getScore());//设置评分
//        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_category_courseImg));//设置图片
//        float v = ConvertUtil.convertToFloat(item.getScore(), 0);
//        if (v > 1 && v <= 2) {//如果评论大于1小于2 就显示
//            ImageView image3 = (ImageView) helper.getView(R.id.item_cate_evaStar3);
//            image3.setImageResource(R.mipmap.normal_star);
//            ImageView image4 = (ImageView) helper.getView(R.id.item_cate_evaStar4);
//            image4.setImageResource(R.mipmap.normal_star);
//            ImageView image5 = helper.getView(R.id.item_cate_evaStar5);
//            image5.setImageResource(R.mipmap.normal_star);
//        } else if (v > 2 && v <= 3) {//如果评论大于2小于3 就显示
//            ImageView image4 = (ImageView) helper.getView(R.id.item_cate_evaStar4);
//            image4.setImageResource(R.mipmap.normal_star);
//            ImageView image5 = (ImageView) helper.getView(R.id.item_cate_evaStar5);
//            image5.setImageResource(R.mipmap.normal_star);
//        } else if (v > 3 && v <= 4) {//如果评论大于3小于4 就显示一个未评价
//            ImageView image5 = helper.getView(R.id.item_cate_evaStar5);
//            image5.setImageResource(R.mipmap.normal_star);
//        } else if (v > 4 && v <= 5) {//如果评论大于4小于5 就显示
//
//        } else if (v > 0 && v <= 1) {//如果评论大于0小于1 就显示
//            ImageView image2 = (ImageView) helper.getView(R.id.item_cate_evaStar2);
//            image2.setImageResource(R.mipmap.normal_star);
//            ImageView image3 = (ImageView) helper.getView(R.id.item_cate_evaStar3);
//            image3.setImageResource(R.mipmap.normal_star);
//            ImageView image4 = (ImageView) helper.getView(R.id.item_cate_evaStar4);
//            image4.setImageResource(R.mipmap.normal_star);
//            ImageView image5 = (ImageView) helper.getView(R.id.item_cate_evaStar5);
//            image5.setImageResource(R.mipmap.normal_star);
//        } else if (v == 0) {
//            ImageView image1 = (ImageView) helper.getView(R.id.item_cate_evaStar1);
//            image1.setImageResource(R.mipmap.normal_star);
//            ImageView image2 = (ImageView) helper.getView(R.id.item_cate_evaStar2);
//            image2.setImageResource(R.mipmap.normal_star);
//            ImageView image3 = (ImageView) helper.getView(R.id.item_cate_evaStar3);
//            image3.setImageResource(R.mipmap.normal_star);
//            ImageView image4 = (ImageView) helper.getView(R.id.item_cate_evaStar4);
//            image4.setImageResource(R.mipmap.normal_star);
//            ImageView image5 = (ImageView) helper.getView(R.id.item_cate_evaStar5);
//            image5.setImageResource(R.mipmap.normal_star);
//        }
    }

}
