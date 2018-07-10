package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.BaseResult;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.model.RegisterModel;
import com.yijiuyiyiedu.xuetang.module.view.RegisterView;
import com.lzy.okgo.model.Response;


/**
 * Created by xuenhao on 2018/3/15.
 */

public class RegisterPresenter {

    RegisterView mView;
    RegisterModel model;

    public RegisterPresenter(RegisterView view) {
        this.mView = view;
        model = new RegisterModel();
    }

    //获取验证码
    public void getCodeData(String phone, int type) {
        mView.showLoading();
        model.getVerifyCode(phone, type, new JsonCallback<BaseResult>() {
            @Override
            public void onSuccess(Response<BaseResult> response) {
                mView.hideLoading();
                mView.showData(response.body());
            }
        });
    }

    //用户注册
    public void RegisterData(String phone, String password, String code, String companyCode) {
        mView.showLoading();
        model.userRegister(phone, password, code, companyCode, new JsonCallback<LoginEntity>() {
            @Override
            public void onSuccess(Response<LoginEntity> response) {
                mView.hideLoading();
                mView.showRegisterData(response.body());
            }
        });
    }

    /**
     * 是否注册过
     * @param phone
     */
    public void getCheckPhoneData(String phone){
        mView.showLoading();
        model.checkPhone(phone, new JsonCallback<ClearHistoryEntity>() {
            @Override
            public void onSuccess(Response<ClearHistoryEntity> response) {
                mView.hideLoading();
                mView.showPhoneData(response.body());
            }
        });
    }


}
