package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.BaseResult;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/15.
 */

public class RegisterModel {
    public RegisterModel() {
    }


    /**
     * @param userName     用户名
     * @param password     密码
     * @param phoneSmsCode 验证码
     * @param companyCode         企业id
     * @param callback
     */
    public void userRegister(String userName, String password, String phoneSmsCode, String companyCode, JsonCallback<LoginEntity> callback) {
        OkGo.<LoginEntity>post(Constant.USER_REGISTER)
                .tag(this)
                .params("user_name", userName)
                .params("password", password)
                .params("phoneSmsCode", phoneSmsCode)
                .params("company_code", companyCode)
                .execute(callback);
    }

    /**
     * @param phone    用户手机号
     * @param type     用户类型
     * @param callback 回调
     */
    public void getVerifyCode(String phone, int type, JsonCallback<BaseResult> callback) {
        OkGo.<BaseResult>post(Constant.SEND_SMS)
                .tag(this)
                .params("phone", phone)
                .params("type", type)
                .execute(callback);
    }

    /**
     * 检查手机号是否注册过
     * @param phone
     * @param callback
     */
    public void checkPhone(String phone,JsonCallback<ClearHistoryEntity> callback){
        OkGo.<ClearHistoryEntity>post(Constant.CHECK_PHONE)
                .tag(this)
                .params("phone",phone)
                .execute(callback);
    }
}
