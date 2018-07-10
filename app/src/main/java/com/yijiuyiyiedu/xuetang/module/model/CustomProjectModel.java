package com.yijiuyiyiedu.xuetang.module.model;

import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectListEntity;

/**
 * Created by xuenhao on 2018/4/9.
 * //项目列表
 */

public class CustomProjectModel {

//    /**
//     * 获取项目分类列表
//     *
//     * @param limit
//     * @param callback
//     */
//    public void getProjectCategoryList(String limit, JsonCallback<ProjectCategoryEntity> callback) {
//        OkGo.<ProjectCategoryEntity>get(Constant.PROJECT_CATEGORY)
//                .params("limit", limit)
//                .execute(callback);
//    }
//
//    /**
//     * 获取项目列表
//     */
//    public void getProjectList(String page, String limit, String project_category_id, JsonCallback<ProjectListEntity> callback) {
//        OkGo.<ProjectListEntity>get(Constant.PROJECT_LIST)
//                .tag(this)
//                .params("page", page)
//                .params("limit", limit)
//                .params("project_category_id", project_category_id)
//                .execute(callback);
//    }
}
