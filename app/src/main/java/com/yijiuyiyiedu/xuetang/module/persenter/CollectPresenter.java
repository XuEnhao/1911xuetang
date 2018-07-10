package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.CollectEntity;
import com.yijiuyiyiedu.xuetang.module.model.CollectModel;
import com.yijiuyiyiedu.xuetang.module.view.CollectView;

/**
 * Created by xuenhao on 2018/3/26.
 */

public class CollectPresenter {
    CollectModel model;
    CollectView mView;

    public CollectPresenter(CollectView mView) {
        this.mView = mView;
        model = new CollectModel();
    }

    /**
     * 删除收藏课程
     *
     * @param curriculumId
     */
    public void delCollectData( String curriculumId) {
        mView.showLoading();
        model.delCollect( curriculumId, new JsonCallback<ClearHistoryEntity>() {
            @Override
            public void onSuccess(Response<ClearHistoryEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }
    /**
     * 删除收藏课程
     *
     * @param userId
     * @param curriculumId
     */
    public void delCollectData(String userId, String curriculumId) {
        mView.showLoading();
        model.delCollect(userId, curriculumId, new JsonCallback<ClearHistoryEntity>() {
            @Override
            public void onSuccess(Response<ClearHistoryEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }

    /**
     * 获取收藏列表
     *
     */
    public void getCollectData() {
        mView.showLoading();
        model.getCollectList( new JsonCallback<CollectEntity>() {
            @Override
            public void onSuccess(Response<CollectEntity> response) {
                mView.showCollectData(response.body());
                mView.hideLoading();
            }
        });
    }

    /**
     * 获取收藏列表
     *
     * @param
     */
    public void getCollectData(int page,int limit) {
        mView.showLoading();
        model.getCollectList(page, limit,new JsonCallback<CollectEntity>() {
            @Override
            public void onSuccess(Response<CollectEntity> response) {
                mView.showCollectData(response.body());
                mView.hideLoading();
            }
        });
    }
}
