package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.BuyRecordEntity;
import com.yijiuyiyiedu.xuetang.module.model.BuyRecordModel;
import com.yijiuyiyiedu.xuetang.module.view.BuyRecordView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/23.
 */

public class BuyRecordPresenter {

    BuyRecordModel model;
    BuyRecordView mView;

    public BuyRecordPresenter(BuyRecordView mView) {
        this.mView = mView;
        model = new BuyRecordModel();
    }

    public void getBuyRecordData(String userId, String type,String page, String limit) {
        mView.showLoading();
        model.getBuyRecord(userId, type,page, limit, new JsonCallback<BuyRecordEntity>() {
            @Override
            public void onSuccess(Response<BuyRecordEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }
}
