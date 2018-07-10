package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/27.
 * 忘记密码model
 */

public class ForgetModel {

    /**
     * 忘记密码
     *
     * @param user_name
     * @param password
     * @param phoneSmsCode
     * @param callback
     */
    public void getForgetData(String user_name, String password, String phoneSmsCode, JsonCallback<ClearHistoryEntity> callback) {

        OkGo.<ClearHistoryEntity>post(Constant.FORGET_PASSWORD)
                .params("user_name", user_name)
                .params("password", password)
                .params("phoneSmsCode", phoneSmsCode)
                .params("type", 2)
                .execute(callback);
    }
}
