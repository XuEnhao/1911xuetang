package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;
import com.yijiuyiyiedu.xuetang.module.model.MineModel;
import com.yijiuyiyiedu.xuetang.module.view.MineView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class MinePresenter {

    MineModel mineModel;
    MineView mView;

    public MinePresenter(MineView view) {
        mView = view;
        mineModel = new MineModel();
    }

    public void getData(String userId) {
        mView.showLoading();
        mineModel.getUserInfo(userId, new JsonCallback<MineEntity>() {
            @Override
            public void onSuccess(Response<MineEntity> response) {
                mView.showData(response.body());
                mView.showLoading();
            }
        });
    }
}
