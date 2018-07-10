package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ProjectDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.model.ProjectDetailsModel;
import com.yijiuyiyiedu.xuetang.module.view.ProjectDetailsView;

/**
 * Created by xuenhao on 2018/4/9.
 * 项目详情
 */

public class ProjectDetailsPresenter {

    ProjectDetailsModel model;
    ProjectDetailsView mView;

    public ProjectDetailsPresenter(ProjectDetailsView mView) {
        this.mView = mView;
        model = new ProjectDetailsModel();
    }

//    /**
//     * 获取项目详情
//     *
//     * @param id     项目id
//     * @param userId 用户id
//     */
//    public void getProjectDetailsData(String id, String userId) {
//        mView.showLoading();
//        model.getProjectDetails(id, userId, new JsonCallback<ProjectDetailsEntity>() {
//            @Override
//            public void onSuccess(Response<ProjectDetailsEntity> response) {
//                mView.showData(response.body());
//                mView.hideLoading();
//            }
//        });
//    }
//
//    /**
//     * 获取用户项目详情
//     * @param id
//     * @param userId
//     */
//    public void getUserProjectData(String id, String userId){
//        model.getBoughtProjectDetails(id, userId, new JsonCallback<ProjectDetailsEntity>() {
//            @Override
//            public void onSuccess(Response<ProjectDetailsEntity> response) {
//                mView.showBoughtProject(response.body());
//            }
//        });
//    }
}
