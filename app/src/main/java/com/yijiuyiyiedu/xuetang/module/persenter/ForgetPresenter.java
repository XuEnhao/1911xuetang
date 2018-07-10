package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.model.ForgetModel;
import com.yijiuyiyiedu.xuetang.module.view.ForgetView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/27.
 */

public class ForgetPresenter {

    ForgetView mView;
    ForgetModel model;

    public ForgetPresenter(ForgetView mView) {
        this.mView = mView;
        model = new ForgetModel();
    }

    /**
     * 忘记密码 请求注册新账号
     *
     * @param user_name
     * @param password
     * @param phoneSmsCode
     */
    public void getForgetData(String user_name, String password, String phoneSmsCode) {
        model.getForgetData(user_name, password, phoneSmsCode, new JsonCallback<ClearHistoryEntity>() {
            @Override
            public void onSuccess(Response<ClearHistoryEntity> response) {
                mView.showForgetData(response.body());
            }
        });
    }
}
