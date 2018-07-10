package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.BuyRecordEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/23.
 * 购买记录
 */

public class BuyRecordModel {


    /**
     * @param userId   用户id 必传
     * @param page
     * @param limit
     * @param callback
     * @param type  订单类型(1,课程2，项目，3会员)
     */
    public void getBuyRecord(String userId,String type, String page, String limit, JsonCallback<BuyRecordEntity> callback) {
        OkGo.<BuyRecordEntity>post(Constant.ORDERLIST)
                .params("user_id", userId)
                .params("page", page)
                .params("type", type)
                .params("limit", limit)
                .execute(callback);
    }
}
