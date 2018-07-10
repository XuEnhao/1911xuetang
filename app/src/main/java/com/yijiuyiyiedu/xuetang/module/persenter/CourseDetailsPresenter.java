package com.yijiuyiyiedu.xuetang.module.persenter;

import android.view.View;

import com.lzy.okgo.callback.StringCallback;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ALNAEntity;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectCourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.model.CourseDetailsModel;
import com.yijiuyiyiedu.xuetang.module.view.CourseDetailsView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/22.
 */

public class CourseDetailsPresenter {
    CourseDetailsModel model;
    CourseDetailsView mView;

    public CourseDetailsPresenter(CourseDetailsView mView) {
        this.mView = mView;
        model = new CourseDetailsModel();
    }

    /**
     * @param courseId 课程id
     * @param userId   用户id 可选
     */
    public void getData(String courseId, String userId) {
        mView.showLoading();
        model.getCourseDetails(courseId, userId, new JsonCallback<CourseDetailsEntity>() {
            @Override
            public void onSuccess(Response<CourseDetailsEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }

    /**
     * @param userId   用户id 必传
     * @param courseId 课程id 必传
     */
    public void getCollectCourse(String userId, String courseId) {
        mView.showLoading();
        model.collectCourse(userId, courseId, new JsonCallback<CollectCourseEntity>() {
            @Override
            public void onSuccess(Response<CollectCourseEntity> response) {
                mView.showCollectData(response.body());
                mView.hideLoading();
            }
        });
    }

    /**
     * 获取播放视频信息
     *
     * @param curriculum_id
     * @param catalog_id
     */
    public void getVideoData(String curriculum_id, String catalog_id) {
        mView.showLoading();
        model.getPlayModel(curriculum_id, catalog_id, new StringCallback() {
            @Override
            public void onSuccess(Response<String> response) {
                mView.showVideoData(response.body());
                mView.hideLoading();
            }
        });
    }


    /**
     * 获取播放url
     * @param curriculum_id
     * @param user_id
     * @param catalog_id
     */
    public void getALNAData(String curriculum_id,String user_id,String catalog_id){
        mView.showLoading();
        model.getALNAData(curriculum_id, user_id, catalog_id, new JsonCallback<ALNAEntity>() {
            @Override
            public void onSuccess(Response<ALNAEntity> response) {
                mView.showALNAData(response.body());
                mView.hideLoading();
            }
        });
    }

    /**
     * 保存播放进度
     * @param curriculum_id
     * @param curriculum_catalog_id
     * @param get_current_time
     */
    public void getSaveScheduleData(String curriculum_id,String curriculum_catalog_id,int get_current_time){
        mView.showLoading();
        model.getSaveSchedule(curriculum_id, curriculum_catalog_id, get_current_time, new JsonCallback<ClearHistoryEntity>() {
            @Override
            public void onSuccess(Response<ClearHistoryEntity> response) {
                mView.hideLoading();
                mView.showSaveSchedule(response.body());
            }
        });
    }
}
