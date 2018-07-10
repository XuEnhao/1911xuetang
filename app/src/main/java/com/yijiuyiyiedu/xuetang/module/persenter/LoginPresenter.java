package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.LoginEntity;
import com.yijiuyiyiedu.xuetang.module.model.LoginModel;
import com.yijiuyiyiedu.xuetang.module.view.LoginView;
import com.lzy.okgo.model.Response;


/**
 * Created by xuenhao on 2018/3/15.
 */

public class LoginPresenter {

    LoginView mView;
    LoginModel model;

    public LoginPresenter(LoginView view) {
        this.mView = view;
        model = new LoginModel();
    }

    //登录信息
    public void getData(String userName, String password) {
        mView.showLoading();
        model.userLogin(userName, password, new JsonCallback<LoginEntity>() {
            @Override
            public void onSuccess(Response<LoginEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }

            @Override
            public void onError(Response<LoginEntity> response) {
                super.onError(response);
                mView.showFailureMessage(response.message());
                mView.showErrorMessage();
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
