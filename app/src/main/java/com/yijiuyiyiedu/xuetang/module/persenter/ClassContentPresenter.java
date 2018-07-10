package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClassContentEntity;
import com.yijiuyiyiedu.xuetang.module.model.ClassContentModel;
import com.yijiuyiyiedu.xuetang.module.view.ClassContentView;

/**
 * Created by xuenhao on 2018/6/9.
 */

public class ClassContentPresenter {
    ClassContentModel model;
    ClassContentView mView;

    public ClassContentPresenter(ClassContentView mView) {
        this.mView = mView;
        model = new ClassContentModel();
    }

    public void getCatalogData(String curriculumId,String token){
        model.getCurriculumCatalogList(curriculumId,token, new JsonCallback<ClassContentEntity>() {
            @Override
            public void onSuccess(Response<ClassContentEntity> response) {
                mView.showCatalogData(response.body());
            }
        });
    }
}
