package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.PositionEntity;
import com.yijiuyiyiedu.xuetang.module.model.PositionModel;
import com.yijiuyiyiedu.xuetang.module.view.PositionView;

/**
 * Created by xuenhao on 2018/6/8.
 * 职业
 */

public class PositionPresenter {
    PositionModel model;
    PositionView mView;

    public PositionPresenter(PositionView mView) {
        this.mView = mView;
        model = new PositionModel();
    }

    public void getPositionData(){
        model.getPositionList(new JsonCallback<PositionEntity>() {
            @Override
            public void onSuccess(Response<PositionEntity> response) {
                mView.showPositionData(response.body());
            }
        });
    }
}
