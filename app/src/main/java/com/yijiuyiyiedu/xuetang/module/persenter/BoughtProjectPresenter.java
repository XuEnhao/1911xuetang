package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.BoughtProjectEntity;
import com.yijiuyiyiedu.xuetang.module.model.BoughtProjectModel;
import com.yijiuyiyiedu.xuetang.module.view.BoughtProjectView;

/**
 * Created by xuenhao on 2018/4/20.
 */

public class BoughtProjectPresenter {
    BoughtProjectModel model;
    BoughtProjectView mView;

    public BoughtProjectPresenter(BoughtProjectView mView) {
        this.mView = mView;
        model = new BoughtProjectModel();
    }

    /**
     * 获取已购项目列表
     * @param user_id
     */
//    public void getUserProjectData(String user_id){
//        model.getUserProject(user_id, new JsonCallback<BoughtProjectEntity>() {
//            @Override
//            public void onSuccess(Response<BoughtProjectEntity> response) {
//                mView.showData(response.body());
//            }
//        });
//    }
}
