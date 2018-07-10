package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ManagerAccountEntity;
import com.yijiuyiyiedu.xuetang.module.model.ManagerAccountModel;
import com.yijiuyiyiedu.xuetang.module.view.ManagerAccountView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/22.
 */

public class ManagerAccountPresenter {
    ManagerAccountModel model;
    ManagerAccountView mView;

    public ManagerAccountPresenter(ManagerAccountView mView) {
        this.mView = mView;
        model = new ManagerAccountModel();
    }

//    public void getData(String userId) {
//        mView.showLoading();
//        model.getManagerAccount(userId, new JsonCallback<ManagerAccountEntity>() {
//            @Override
//            public void onSuccess(Response<ManagerAccountEntity> response) {
//                mView.showData(response.body());
//                mView.hideLoading();
//            }
//        });
//    }
}
