package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.model.EditSonAccountModel;
import com.yijiuyiyiedu.xuetang.module.view.EditSonAccountView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/23.
 */

public class EditSonAccountPresenter {
    EditSonAccountModel model;
    EditSonAccountView mView;

    public EditSonAccountPresenter(EditSonAccountView mView) {
        this.mView = mView;
        model = new EditSonAccountModel();
    }

//    /**
//     * 添加企业子账号
//     *
//     * @param user_id
//     * @param username
//     * @param password
//     * @param staff_name
//     * @param staff_position
//     * @param childId
//     */
//    public void getAddSonAccountData(String user_id, String username, String password, String staff_name, String staff_position, String childId) {
//        model.AddSonAccount(user_id, username, password, staff_name, staff_position, childId, new JsonCallback<ClearHistoryEntity>() {
//            @Override
//            public void onSuccess(Response<ClearHistoryEntity> response) {
//                mView.showData(response.body());
//            }
//        });
//    }

//    /**
//     * 删除企业子账号
//     *
//     * @param childId
//     */
//    public void setDelSonAccountData(String childId) {
//        model.DelSonAccount(childId, new JsonCallback<ClearHistoryEntity>() {
//            @Override
//            public void onSuccess(Response<ClearHistoryEntity> response) {
//                mView.showDelData(response.body());
//            }
//        });
//    }
}
