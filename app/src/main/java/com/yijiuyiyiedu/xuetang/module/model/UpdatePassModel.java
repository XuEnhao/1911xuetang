package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;

/**
 * Created by xuenhao on 2018/3/26.
 */

public class UpdatePassModel {

    /**
     * 修改密码
     *
     * @param user_id
     * @param oldps
     * @param newpass
     * @param newpasso
     * @param callback
     */
    public void updatePass(String user_id, String oldps, String newpass, String newpasso, JsonCallback<ClearHistoryEntity> callback) {
        OkGo.<ClearHistoryEntity>post(Constant.UPDATE_PASSWORD)
                .params("user_id", user_id)
                .params("oldps", oldps)
                .params("newpass", newpass)
                .params("newpasso", newpasso)
                .execute(callback);
    }
}
