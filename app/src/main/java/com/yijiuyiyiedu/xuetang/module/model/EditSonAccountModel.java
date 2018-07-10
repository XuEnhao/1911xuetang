package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/23.
 */

public class EditSonAccountModel {

//    /**
//     * 添加企业子账号
//     *
//     * @param user_id
//     * @param username
//     * @param password
//     * @param staff_name
//     * @param staff_position
//     * @param callback
//     */
//    public void AddSonAccount(String user_id, String username, String password, String staff_name, String staff_position, String childId, JsonCallback<ClearHistoryEntity> callback) {
//        OkGo.<ClearHistoryEntity>post(Constant.ADD_CHILD_USER)
//                .params("user_id", user_id)
//                .params("username", username)
//                .params("password", password)
//                .params("staff_name", staff_name)
//                .params("staff_position", staff_position)
//                .params("child_id", childId)
//                .execute(callback);
//
//    }
//
//    /**
//     * 删除企业子账号
//     *
//     * @param childId
//     * @param callback
//     */
//    public void DelSonAccount(String childId, JsonCallback<ClearHistoryEntity> callback) {
//        OkGo.<ClearHistoryEntity>post(Constant.DEL_CHILD_USER)
//                .params("user_id", childId)
//                .execute(callback);
//    }
}
