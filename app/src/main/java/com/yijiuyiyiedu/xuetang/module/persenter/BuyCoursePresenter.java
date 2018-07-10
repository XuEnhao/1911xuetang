package com.yijiuyiyiedu.xuetang.module.persenter;

import android.util.Log;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.BuyCourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.OrderEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.model.BuyCourseModel;
import com.yijiuyiyiedu.xuetang.module.view.BuyCourseView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/27.
 * 购买信息显示页面
 */

public class BuyCoursePresenter {
    BuyCourseModel model;
    BuyCourseView mView;

    public BuyCoursePresenter(BuyCourseView mView) {
        this.mView = mView;
        model = new BuyCourseModel();
    }

    /**
     * 获取购买信息显示页面
     *
     * @param curriculum_id
     * @param userId
     */
    public void getBuyCourseData(String curriculum_id, String userId) {
//        mView.showLoading();
        model.getBuyCourse(curriculum_id, userId, new JsonCallback<BuyCourseEntity>() {
            @Override
            public void onSuccess(Response<BuyCourseEntity> response) {
                mView.showData(response.body());
//                mView.hideLoading();
            }
        });
    }

    /**
     * 获取订单id
     *
     * @param kcid      课程id
     * @param price     价格
     * @param title     商品名称
     * @param user_type 用户类型
     * @param user_id   用户id
     */
    public void getOrderData(String kcid, String price, String title, String user_type, String user_id, String pay_type) {
        mView.showLoading();
        model.getOrderId(kcid, price, title, user_type, user_id, pay_type, new JsonCallback<OrderEntity>() {
            @Override
            public void onSuccess(Response<OrderEntity> response) {
                mView.hideLoading();
                mView.showOrderData(response.body());
            }
        });
    }

    /**
     * 获取项目信息
     * @param id
     * @param user_id
     */
//    public void getProjectData(String id,String user_id){
//        model.getBuyProject(id, user_id, new JsonCallback<ProjectDetailsEntity>() {
//            @Override
//            public void onSuccess(Response<ProjectDetailsEntity> response) {
//                mView.showProjectData(response.body());
//            }
//        });
//    }

}
