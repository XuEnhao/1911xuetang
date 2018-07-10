package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.model.HttpHeaders;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.MineEntity;
import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.utils.SharedPreferencesUtil;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class MineModel {

    /**
     * 我的界面 获取用户信息
     *
     * @param Authorization 用户token
     */
    public void getUserInfo(String Authorization, JsonCallback<MineEntity> callback) {
        OkGo.<MineEntity>post(Constant.GET_USER_INFO)
                .tag(this)
                .headers("Authorization",Authorization)
                .execute(callback);
    }
}
