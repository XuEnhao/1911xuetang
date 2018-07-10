package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;


/**
 * Created by xuenhao on 2018/4/3.
 */

public class NewCategoryModel {

    /**
     * 获取一二级分类列表
     *
     * @param callback
     */
    public void getCategoryList(JsonCallback<NewCategoryEntity> callback) {
        OkGo.<NewCategoryEntity>get(Constant.CATEGORY_LIST)
                .execute(callback);

    }


}
