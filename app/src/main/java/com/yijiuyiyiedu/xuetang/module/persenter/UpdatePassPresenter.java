package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.model.UpdatePassModel;
import com.yijiuyiyiedu.xuetang.module.view.UpdatePassView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/26.
 * 修改密码接口
 */

public class UpdatePassPresenter {
    UpdatePassModel model;
    UpdatePassView mView;

    public UpdatePassPresenter(UpdatePassView mView) {
        this.mView = mView;
        model = new UpdatePassModel();
    }

    /**
     * 修改密码
     *
     * @param user_id  都是必传
     * @param oldps
     * @param newpass
     * @param newpasso
     */
    public void updatePassword(String user_id, String oldps, String newpass, String newpasso) {
        mView.showLoading();
        model.updatePass(user_id, oldps, newpass, newpasso, new JsonCallback<ClearHistoryEntity>() {
            @Override
            public void onSuccess(Response<ClearHistoryEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }
}
