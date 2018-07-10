package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/26.
 */

public class CollectModel {

    /**
     * 删除收藏的接口
     *
     * @param user_id       用户id  Y
     * @param curriculum_id 课程id Y
     * @param callback
     */

    public void delCollect(String user_id, String curriculum_id, JsonCallback<ClearHistoryEntity> callback) {

        OkGo.<ClearHistoryEntity>post(Constant.DEL_COLLACT)
                .params("user_id", user_id)
                .params("curriculum_id", curriculum_id)
                .execute(callback);
    }

    /**
     * 删除收藏的接口
     *
     * @param curriculum_id 课程id Y
     * @param callback
     */

    public void delCollect(String curriculum_id, JsonCallback<ClearHistoryEntity> callback) {

        OkGo.<ClearHistoryEntity>post(Constant.DEL_COLLACT)
                .params("curriculum_id", curriculum_id)
                .execute(callback);
    }

    /**
     * 获取收藏列表
     *
     */
    public void getCollectList( JsonCallback<CollectEntity> callback) {
        OkGo.<CollectEntity>post(Constant.COLLECT_LIST)
//                .params("user_id", user_id)
                .execute(callback);
    }
    /**
     * 获取收藏列表
     *
     * @param
     */
    public void getCollectList(int page,int limit, JsonCallback<CollectEntity> callback) {
        OkGo.<CollectEntity>post(Constant.COLLECT_LIST)
                .params("page", page)
                .params("limit", limit)
                .execute(callback);
    }
}
