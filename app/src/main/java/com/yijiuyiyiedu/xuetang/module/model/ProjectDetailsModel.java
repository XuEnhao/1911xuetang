package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;

/**
 * Created by xuenhao on 2018/4/9.
 * 项目详情
 */

public class ProjectDetailsModel {

//    /**
//     * 获取项目详情
//     *
//     * @param id       项目id
//     * @param user_id  用户id
//     * @param callback
//     */
//    public void getProjectDetails(String id, String user_id, JsonCallback<ProjectDetailsEntity> callback) {
//        OkGo.<ProjectDetailsEntity>post(Constant.PROJECT_DETAILS)
//                .tag(this)
//                .params("id", id)
//                .params("user_id", user_id)
//                .execute(callback);
//
//    }
//
//    /**
//     * 获取用户已购买项目
//     * @param id
//     * @param user_id
//     * @param callback
//     */
//    public void getBoughtProjectDetails(String id, String user_id, JsonCallback<ProjectDetailsEntity> callback){
//        OkGo.<ProjectDetailsEntity>get(Constant.USER_PROJECT_DETAILS)
//                .tag(this)
//                .params("id",id)
//                .params("user_id",user_id)
//                .execute(callback);
//    }
}
