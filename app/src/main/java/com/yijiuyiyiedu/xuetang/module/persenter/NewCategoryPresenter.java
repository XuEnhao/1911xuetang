package com.yijiuyiyiedu.xuetang.module.persenter;

import com.lzy.okgo.model.Response;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.NewCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.model.NewCategoryModel;
import com.yijiuyiyiedu.xuetang.module.view.NewCategoryView;

/**
 * Created by xuenhao on 2018/4/3.
 */

public class NewCategoryPresenter {
    NewCategoryModel model;
    NewCategoryView view;

    public NewCategoryPresenter(NewCategoryView view) {
        this.view = view;
        model = new NewCategoryModel();
    }

    public void getNewCategoryList() {
        view.showLoading();
        model.getCategoryList(new JsonCallback<NewCategoryEntity>() {
            @Override
            public void onSuccess(Response<NewCategoryEntity> response) {
                view.showData(response.body());
                view.hideLoading();
            }
        });
    }
}
