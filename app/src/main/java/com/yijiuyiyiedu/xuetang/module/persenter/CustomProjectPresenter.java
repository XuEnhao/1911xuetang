package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectListEntity;
import com.yijiuyiyiedu.xuetang.module.model.CustomProjectModel;
import com.yijiuyiyiedu.xuetang.module.view.CustomProjectView;

/**
 * Created by xuenhao on 2018/4/9.
 */

public class CustomProjectPresenter {

    CustomProjectModel model;
    CustomProjectView mView;

    public CustomProjectPresenter(CustomProjectView mView) {
        this.mView = mView;
        model = new CustomProjectModel();
    }

//    /**
//     * 获取项目分类列表
//     *
//     * @param limit
//     */
//    public void getProjectCategory(String limit) {
//        mView.showLoading();
//        model.getProjectCategoryList(limit, new JsonCallback<ProjectCategoryEntity>() {
//            @Override
//            public void onSuccess(Response<ProjectCategoryEntity> response) {
//                mView.showData(response.body());
//                mView.hideLoading();
//            }
//        });
//    }
//
//    /**
//     * 获取项目列表
//     *
//     * @param page
//     * @param limit
//     * @param project_category_id
//     */
//    public void getProjectList(String page, String limit, String project_category_id) {
//        mView.showLoading();
//        model.getProjectList(page, limit, project_category_id, new JsonCallback<ProjectListEntity>() {
//            @Override
//            public void onSuccess(Response<ProjectListEntity> response) {
//                mView.showProjectListData(response.body());
//                mView.hideLoading();
//            }
//        });
//    }
}
