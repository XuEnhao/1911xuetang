package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.BuyCourseEntity;
import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.module.entity.OrderEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;

/**
 * Created by xuenhao on 2018/3/27.
 * 购买信息
 */

public class BuyCourseModel {

    /**
     * 购买信息显示页面
     *
     * @param curriculum_id 必传
     * @param userId        必传
     * @param callback
     */
    public void getBuyCourse(String curriculum_id, String userId, JsonCallback<BuyCourseEntity> callback) {
        OkGo.<BuyCourseEntity>post(Constant.BUY_COURSE)
                .tag(this)
                .params("curriculum_id", curriculum_id)
                .params("user_id", userId)
                .execute(callback);

    }

//    /**
//     * 获取项目详情
//     * @param id 项目id  必传
//     * @param user_id 用户id  必传
//     * @param callback
//     */
//    public void getBuyProject(String id, String user_id, JsonCallback<ProjectDetailsEntity> callback){
//        OkGo.<ProjectDetailsEntity>post(Constant.PROJECT_DETAILS)
//                .tag(this)
//                .params("id",id)
//                .params("user_id",user_id)
//                .execute(callback);
//    }

    /**
     * 生成订单
     *
     * @param kcid      课程id  都必传
     * @param price     商品价格
     * @param title     商品名称
     * @param user_type 用户类型
     * @param user_id   用户id
     * @param callback
     */
    public void getOrderId(String kcid, String price, String title, String user_type, String user_id, String pay_type, JsonCallback<OrderEntity> callback) {
        OkGo.<OrderEntity>post(Constant.PRODUCE_ORDER)
                .tag(this)
                .params("kcid", kcid)
                .params("price", price)
                .params("pay_number", title)
                .params("user_type", user_type)
                .params("user_id", user_id)
                .params("pay_type", pay_type)
                .execute(callback);
    }


}
