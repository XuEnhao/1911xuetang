package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.MsgManagerEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/22.
 */

public class MsgManagerModel {

    public void getMsgManager(String user_id, String page, String limit, JsonCallback<MsgManagerEntity> callback) {
        OkGo.<MsgManagerEntity>post(Constant.MSG_MANAGER)
                .params("user_id", user_id)
                .params("page", page)
                .params("limit", limit)
                .execute(callback);
    }
}
