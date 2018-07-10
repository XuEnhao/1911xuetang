package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.model.CustomProjectContentModel;
import com.yijiuyiyiedu.xuetang.module.view.CustomProjectContentView;

/**
 * Created by xuenhao on 2018/4/11.
 * 定制项目的填写提交信息页面
 */

public class CustomProjectContentPresenter {
    CustomProjectContentModel model;
    CustomProjectContentView mView;

    public CustomProjectContentPresenter(CustomProjectContentView mView) {
        this.mView = mView;
        model = new CustomProjectContentModel();
    }

    /**
     * 获取提交的项目信息
     * @param staff_name 真实姓名  全部必传
     * @param staff_tel  联系方式
     * @param staff_position 详细描述
     */
//    public void getSubmitProjectContentData(String staff_name, String staff_tel, String staff_position){
//        mView.showLoading();
//        model.submitProjectContent(staff_name, staff_tel, staff_position, new JsonCallback<ClearHistoryEntity>() {
//            @Override
//            public void onSuccess(Response<ClearHistoryEntity> response) {
//                mView.showData(response.body());
//                mView.hideLoading();
//            }
//        });
//    }
}
