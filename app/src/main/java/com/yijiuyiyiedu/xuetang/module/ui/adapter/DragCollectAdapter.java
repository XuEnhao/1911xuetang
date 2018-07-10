package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.mcxtzhang.swipemenulib.SwipeMenuLayout;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectEntity;
import com.yijiuyiyiedu.xuetang.module.persenter.CollectPresenter;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CollectActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.CourseDetailsActivity;
import com.yijiuyiyiedu.xuetang.module.view.CollectView;
import com.yijiuyiyiedu.xuetang.utils.ConvertUtil;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.UtilDpOrPx;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class DragCollectAdapter extends BaseQuickAdapter<CollectEntity.DataBean, BaseViewHolder> implements CollectView {
    CollectPresenter mPresenter;
    private int position;

    public DragCollectAdapter(int layoutResId, @Nullable List<CollectEntity.DataBean> data) {
        super(layoutResId, data);
        mPresenter = new CollectPresenter(this);
    }

    @Override
    protected void convert(final BaseViewHolder helper, final CollectEntity.DataBean item) {
//        helper.setText(R.id.item_category_course1Title, item.getTitle());//设置标题
//        helper.setText(R.id.item_category_course1Volume, "销量:" + item.getStudy_number() + "份");//设置销量
//        helper.setText(R.id.item_category_course1Price, item.getPresent_price());//设置价格
//        helper.setText(R.id.item_category_course1Grade, item.getScore());//设置评分
//        GlideUtils.loadRoundImage(mContext, item.getPicture(), (ImageView) helper.getView(R.id.item_category_course1Img));//设置图片
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
//        Button button = helper.getView(R.id.btnDelete);
//        button.setWidth(UtilDpOrPx.dip2px(mContext, 90));
//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                position = helper.getPosition();
//                mPresenter.delCollectData(UriConstant.userId, item.getId());
//                CollectActivity.getData();
//            }
//        });
//        helper.getView(R.id.item_drag_collect_swipe).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Intent it = new Intent(mContext, CourseDetailsActivity.class);
//                it.putExtra("courseId",item.getCurriculum_id());
//                mContext.startActivity(it);
//            }
//        });
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showData(ClearHistoryEntity data) {
        if (data.getStatus()==0) {
            UtilToast.showToast(mContext, "删除成功");
            this.remove(position);
        } else {
            UtilToast.showToast(mContext, data.getMsg());
        }
    }

    @Override
    public void showCollectData(CollectEntity data) {

    }

    @Override
    public void showFailureMessage(String msg) {

    }

    @Override
    public void showErrorMessage() {

    }
}
