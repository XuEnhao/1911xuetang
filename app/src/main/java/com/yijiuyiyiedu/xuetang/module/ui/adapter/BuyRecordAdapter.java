package com.yijiuyiyiedu.xuetang.module.ui.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.util.MultiTypeDelegate;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.R;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.api.constant.UriConstant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.BuyRecordBus;
import com.yijiuyiyiedu.xuetang.module.entity.BuyRecordEntity;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.PayActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SelectConvertNumActivity;
import com.yijiuyiyiedu.xuetang.module.ui.activitys.SelectUseConvertNumActivity;
import com.yijiuyiyiedu.xuetang.utils.GlideUtils;
import com.yijiuyiyiedu.xuetang.utils.TimeUtil;
import com.yijiuyiyiedu.xuetang.utils.UtilToast;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import static com.yijiuyiyiedu.xuetang.utils.TimeUtil.dateFormatYMDHMS;

/**
 * Created by xuenhao on 2018/3/23.
 * 购买记录适配器
 */

public class BuyRecordAdapter extends BaseQuickAdapter<BuyRecordEntity.DataBean.OrderListBean, BaseViewHolder> {
    final int COURSE = 1;
    final int PROJECT = 2;
    final int VIP = 3;
    public BuyRecordAdapter(@Nullable final List<BuyRecordEntity.DataBean.OrderListBean> data) {
        super(data);
        setMultiTypeDelegate(new MultiTypeDelegate<BuyRecordEntity.DataBean.OrderListBean>() {
            @Override
            protected int getItemType(BuyRecordEntity.DataBean.OrderListBean orderListBean) {
                int listType = 0;
                String pay_type = orderListBean.getPay_type();
                if (pay_type.equals("1")){//订单类型  1 购买课程 2 购买项目  3 会员充值
                    listType = 1;
                }else if (pay_type.equals("2")){
                    listType = 2;
                }else{
                    listType = 3;
                }
                //做处理
                switch (listType){
                    case 1://课程
                        return COURSE;
                    case 2://会员
                        return PROJECT;
                    case 3:
                        return VIP;
                }
                return 0;
            }
        });

        getMultiTypeDelegate().registerItemType(COURSE, R.layout.item_buy_record)//课程
                .registerItemType(PROJECT,R.layout.item_buy_record)//项目
                .registerItemType(VIP, R.layout.item_buy_record_vip);//会员
    }
    @Override
    protected void convert(BaseViewHolder helper, final BuyRecordEntity.DataBean.OrderListBean item) {

        switch (helper.getItemViewType()) {
            case COURSE://课程
                //                pay_status 1待支付 2已支付 3取消 4退款
                String pay_status = item.getPay_status();
                TextView payStatus = helper.getView(R.id.item_buy_record_orderType);//是否支付
                TextView price = helper.getView(R.id.item_buy_record_price);//价格
                final TextView seeNumber = helper.getView(R.id.item_buy_record_SeeNumber);//查看课程
                final TextView giveNumber = helper.getView(R.id.item_buy_record_giveNumber);//赠送课程
                if (pay_status.equals("1")){
                    payStatus.setTextColor(Color.parseColor("#F4555D"));
                    price.setTextColor(Color.parseColor("#F4555D"));
                    helper.setText(R.id.item_buy_record_orderType,"待支付");
                    helper.setText(R.id.item_buy_record_orderTime," ");//待支付没有时间
                    giveNumber.setText("去支付");
                    seeNumber.setText("取消订单");
                } else if (pay_status.equals("2")) {
                    payStatus.setTextColor(Color.parseColor("#222222"));
                    price.setTextColor(Color.parseColor("#CCCCCC"));
                    helper.setText(R.id.item_buy_record_orderType,"已支付");
                    helper.setText(R.id.item_buy_record_orderTime,item.getPay_time()+" ");
                    giveNumber.setText("查看兑换码");
                    seeNumber.setText("查看兑换情况");
                }else if (pay_status.equals("3")){
                    payStatus.setTextColor(Color.parseColor("#222222"));
                    helper.setText(R.id.item_buy_record_orderType,"已取消");
                    giveNumber.setVisibility(View.GONE);
                    seeNumber.setVisibility(View.GONE);
                    price.setTextColor(Color.parseColor("#CCCCCC"));
                    helper.setText(R.id.item_buy_record_orderTime," ");
                }else{
                    helper.setText(R.id.item_buy_record_orderType,"已退款");
                }
                helper.setText(R.id.item_buy_record_orderNum,"订单编号:"+item.getOrder_sn());//订单编号
                helper.setText(R.id.item_buy_record_num,"数量:"+item.getPay_number());//数量
                helper.setText(R.id.item_buy_record_price,"¥"+item.getOrder_amount());//支付价钱
                //加载图片
                GlideUtils.loadRoundImage(mContext,item.getPicture(), (ImageView) helper.getView(R.id.item_buy_record_orderImg));
                helper.setText(R.id.item_buy_record_title,item.getTitle());//课程标题
//              //讲师
                helper.setText(R.id.item_buy_record_courseNum,"讲师:"+item.getLecturer());
                //赠送兑换码
                giveNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (giveNumber.getText().toString().equals("去支付")){//跳转支付页面
                            Intent it = new Intent(mContext, PayActivity.class);
                            it.putExtra("orderId",Integer.parseInt(item.getId()));
                            it.putExtra("courseId",Integer.parseInt(item.getId()));
//                            1 课程 2 项目 3充值
                            it.putExtra("type",1);
                            mContext.startActivity(it);
                        }else{//否则就是查看兑换码
                            Intent it = new Intent(mContext, SelectConvertNumActivity.class);
                            it.putExtra("orderId",item.getId());//订单id
                            it.putExtra("status","1");//状态
                            it.putExtra("payNumber",item.getPay_number());
                            mContext.startActivity(it);
                        }
                    }
                });
                //查看兑换码
                seeNumber.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (seeNumber.getText().toString().equals("取消订单")){
                            delOrder(item.getId());
                        }else{//查看兑换码
                            Intent it = new Intent(mContext, SelectUseConvertNumActivity.class);
                            it.putExtra("orderId",item.getId());//订单id
                            it.putExtra("status","2");//状态
                            it.putExtra("payNumber",item.getPay_number());
                            mContext.startActivity(it);
                        }
                    }
                });
                break;
            case PROJECT://项目
                //                pay_status 1待支付 2已支付 3取消 4退款
                String pay_status1 = item.getPay_status();
                TextView payStatus1 = helper.getView(R.id.item_buy_record_orderType);//是否支付
                TextView price1 = helper.getView(R.id.item_buy_record_price);//价格
                final TextView seeNumber1 = helper.getView(R.id.item_buy_record_SeeNumber);//查看课程
                final TextView giveNumber1 = helper.getView(R.id.item_buy_record_giveNumber);//赠送课程
                if (pay_status1.equals("1")){
                    payStatus1.setTextColor(Color.parseColor("#F4555D"));
                    price1.setTextColor(Color.parseColor("#F4555D"));
                    helper.setText(R.id.item_buy_record_orderType,"待支付");
                    giveNumber1.setText("去支付");
                    seeNumber1.setText("取消订单");
                    helper.setText(R.id.item_buy_record_orderTime," ");//待支付没有时间
                } else if (pay_status1.equals("2")) {
                    payStatus1.setTextColor(Color.parseColor("#222222"));
                    price1.setTextColor(Color.parseColor("#CCCCCC"));
                    helper.setText(R.id.item_buy_record_orderType,"已支付");
                    helper.setText(R.id.item_buy_record_orderTime,item.getPay_time()+" ");//支付时间
                    giveNumber1.setText("查看兑换码");
                    seeNumber1.setText("查看兑换情况");
                }else if (pay_status1.equals("3")){
                    payStatus1.setTextColor(Color.parseColor("#222222"));
                    price1.setTextColor(Color.parseColor("#CCCCCC"));
                    helper.setText(R.id.item_buy_record_orderType,"已取消");
                    giveNumber1.setVisibility(View.GONE);
                    seeNumber1.setVisibility(View.GONE);
                    helper.setText(R.id.item_buy_record_orderTime," ");
                }else{
                    helper.setText(R.id.item_buy_record_orderType,"已退款");
                }
                helper.setText(R.id.item_buy_record_orderNum,"订单编号:"+item.getOrder_sn());//订单编号
                helper.setText(R.id.item_buy_record_num,"数量:"+item.getPay_number());//数量
                helper.setText(R.id.item_buy_record_price,"¥"+item.getOrder_amount());//支付价钱
                //加载图片
                GlideUtils.loadRoundImage(mContext,item.getPicture(), (ImageView) helper.getView(R.id.item_buy_record_orderImg));
                helper.setText(R.id.item_buy_record_title,item.getTitle());//课程标题
//              //几门课程 共多少学时
                helper.setText(R.id.item_buy_record_courseNum,"共计"+item.getTotalCurriculum()+"门课程；"+item.getCurriculum_time()+"学时");
                //赠送兑换码
                giveNumber1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (giveNumber1.getText().toString().equals("去支付")){//跳转支付页面
                            Intent it = new Intent(mContext, PayActivity.class);
                            it.putExtra("orderId",Integer.parseInt(item.getId()));
                            it.putExtra("type",2);
                            mContext.startActivity(it);
                        }else{//否则就是赠送兑换码
                            Intent it = new Intent(mContext, SelectConvertNumActivity.class);
                            it.putExtra("orderId",item.getId());//订单id
                            it.putExtra("status","1");//状态
                            it.putExtra("payNumber",item.getPay_number());
                            mContext.startActivity(it);
                        }
                    }
                });
                //查看兑换码
                seeNumber1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (seeNumber1.getText().toString().equals("取消订单")){
                            delOrder(item.getId());
                        }else{//查看兑换码
                            Intent it = new Intent(mContext, SelectUseConvertNumActivity.class);
                            it.putExtra("orderId",item.getId());//订单id
                            it.putExtra("status","2");//状态
                            it.putExtra("payNumber",item.getPay_number());
                            mContext.startActivity(it);
                        }
                    }
                });
                break;
            case VIP://会员
                helper.setText(R.id.item_buy_record_vip_orderNum,"订单编号:"+item.getOrder_sn());//订单编号
                helper.setText(R.id.item_buy_record_vip_title,item.getTitle());//标题
                GlideUtils.loadRoundImage(mContext,R.mipmap.record_vip, (ImageView) helper.getView(R.id.item_buy_record_vip_orderImg));
//                item_buy_record_vip_orderType
                //                pay_status 1待支付 2已支付 3取消 4退款
                String pay_status2 = item.getPay_status();
                TextView payStatus2 = helper.getView(R.id.item_buy_record_vip_orderType);//是否支付
                TextView price2 = helper.getView(R.id.item_buy_record_vip_price);//价格
                final TextView seeNumber2 = helper.getView(R.id.item_buy_record_vip_seeCourse);//查看课程
                final TextView delNumber2 = helper.getView(R.id.item_buy_record_vip_delOrder);//删除课程
                if (pay_status2.equals("1")){
                    payStatus2.setTextColor(Color.parseColor("#F4555D"));
                    price2.setTextColor(Color.parseColor("#F4555D"));
                    helper.setText(R.id.item_buy_record_vip_orderType,"待支付");
                    seeNumber2.setText("去支付");
                    delNumber2.setText("取消订单");
                    helper.setText(R.id.item_buy_record_vip_orderTime," ");//待支付没有时间
                } else if (pay_status2.equals("2")) {
                    payStatus2.setTextColor(Color.parseColor("#222222"));
                    price2.setTextColor(Color.parseColor("#CCCCCC"));
                    helper.setText(R.id.item_buy_record_vip_orderType,"已支付");
                    seeNumber2.setVisibility(View.GONE);
                    delNumber2.setVisibility(View.GONE);
                    helper.setText(R.id.item_buy_record_vip_orderTime,item.getPay_time()+" ");//支付时间
                }else if (pay_status2.equals("3")){
                    payStatus2.setTextColor(Color.parseColor("#222222"));
                    price2.setTextColor(Color.parseColor("#CCCCCC"));
                    helper.setText(R.id.item_buy_record_vip_orderType,"已取消");
                    seeNumber2.setVisibility(View.GONE);
                    delNumber2.setVisibility(View.GONE);
                    helper.setText(R.id.item_buy_record_vip_orderTime," ");
                }else{
                    helper.setText(R.id.item_buy_record_vip_orderType,"已退款");
                    seeNumber2.setVisibility(View.GONE);
                    delNumber2.setVisibility(View.GONE);
                    helper.setText(R.id.item_buy_record_vip_orderTime," ");
                }
                price2.setText("¥"+item.getOrder_amount());
                helper.setText(R.id.item_buy_record_vip_num," ");//数量
                //去支付
                seeNumber2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (seeNumber2.getText().toString().equals("去支付")){//跳转支付页面
                            Intent it = new Intent(mContext, PayActivity.class);
                            it.putExtra("orderId",Integer.parseInt(item.getId()));
//                            1 课程 2 项目 3充值
                            it.putExtra("type",3);
                            mContext.startActivity(it);
                        }else{//否则就是赠送兑换码

                        }
                    }
                });
                //查看兑换码
                delNumber2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (delNumber2.getText().toString().equals("取消订单")){
                            delOrder(item.getId());
                        }else{//查看兑换码

                        }
                    }
                });
                break;
            default:
                break;
        }
    }

    //删除订单
    private void delOrder(String order_id){
        OkGo.<ClearHistoryEntity>post(Constant.BASE_URL+"/Api/Order/changeOrder")
                .tag(this)
                .params("order_id",order_id)
                .params("user_id",UriConstant.userId)
                .execute(new JsonCallback<ClearHistoryEntity>() {
                    @Override
                    public void onSuccess(Response<ClearHistoryEntity> response) {
                        if (response.body().getStatus()==0){
                            UtilToast.showToast(mContext,"取消成功");
                            EventBus.getDefault().postSticky(new BuyRecordBus("1"));
                        }else{
                            UtilToast.showToast(mContext,response.body().getMsg());
                        }
                    }
                });
    }
}
