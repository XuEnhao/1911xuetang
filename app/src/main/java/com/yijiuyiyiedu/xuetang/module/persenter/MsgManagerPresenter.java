package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.MsgManagerEntity;
import com.yijiuyiyiedu.xuetang.module.model.MsgManagerModel;
import com.yijiuyiyiedu.xuetang.module.view.MsgManagerView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/22.
 */

public class MsgManagerPresenter {
    MsgManagerModel model;
    MsgManagerView mView;

    public MsgManagerPresenter(MsgManagerView mView) {
        this.mView = mView;
        model = new MsgManagerModel();
    }

    public void getData(String userId, String page, String limit) {
        mView.showLoading();
        model.getMsgManager(userId, page, limit, new JsonCallback<MsgManagerEntity>() {
            @Override
            public void onSuccess(Response<MsgManagerEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }
}
