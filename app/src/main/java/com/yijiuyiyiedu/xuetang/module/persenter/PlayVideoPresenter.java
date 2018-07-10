package com.yijiuyiyiedu.xuetang.module.persenter;

import android.util.Log;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CourseDetailsEntity;
import com.yijiuyiyiedu.xuetang.module.model.PlayVideoModel;
import com.yijiuyiyiedu.xuetang.module.view.PlayVideoView;

/**
 * Created by xuenhao on 2018/3/29.
 */

public class PlayVideoPresenter {
    PlayVideoModel model;
    PlayVideoView mView;

    public PlayVideoPresenter(PlayVideoView mView) {
        this.mView = mView;
        model = new PlayVideoModel();
    }

    /**
     * 获取视频信息
     *
     * @param user_id
     * @param curriculum_id
     * @param catalog_id
     */
    public void getPlayData(String user_id, String curriculum_id, String catalog_id) {
        Log.d("tag", "getPlayData: " + user_id + "   curriculum_id:" + curriculum_id + " catalog_id:" + catalog_id);
        mView.showLoading();
        model.getPlayModel(user_id, curriculum_id, catalog_id, new StringCallback() {

            @Override
            public void onSuccess(Response<String> response) {
                mView.showVideoData(response.body());
            }

            @Override
            public String convertResponse(okhttp3.Response response) throws Throwable {

                return super.convertResponse(response);
            }
        });
    }

    /**
     * 获取数据
     *
     * @param id     课程id
     * @param userId 用户id
     */
    public void getContentData(String id, String userId) {
        mView.showLoading();
        model.getCourseDetails(id, userId, new JsonCallback<CourseDetailsEntity>() {
            @Override
            public void onSuccess(Response<CourseDetailsEntity> response) {
                mView.showContentData(response.body());
                mView.hideLoading();
            }
        });
    }
}
