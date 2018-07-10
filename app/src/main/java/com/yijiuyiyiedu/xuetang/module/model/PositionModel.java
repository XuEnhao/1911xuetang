package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.PositionEntity;

/**
 * Created by xuenhao on 2018/6/8.
 */

public class PositionModel {

    public void getPositionList(JsonCallback<PositionEntity> callback){
        OkGo.<PositionEntity>get(Constant.POSITION_LIST)
                .execute(callback);
    }
}
