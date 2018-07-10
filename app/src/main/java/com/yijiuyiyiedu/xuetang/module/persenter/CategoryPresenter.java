package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewChildCategoryEntity;
import com.yijiuyiyiedu.xuetang.module.model.CategoryModel;
import com.yijiuyiyiedu.xuetang.module.view.CategoryView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class CategoryPresenter {
    CategoryView mView;
    CategoryModel model;

    public CategoryPresenter(CategoryView mView) {
        this.mView = mView;
        model = new CategoryModel();
    }

    public void getData(String page, String limit, String status_serial, String is_recommend, String search_word, String sort_by, String teacher_id, String category_ida, String category_idb,int isHide) {
        mView.showLoading();
        model.getCategoryList(page, limit, status_serial, is_recommend, search_word,sort_by,teacher_id,category_ida,category_idb,isHide, new JsonCallback<CategoryEntity>() {
            @Override
            public void onSuccess(Response<CategoryEntity> response) {
                mView.hideLoading();
                mView.showData(response.body());
            }
        });
    }

    public void getChildData(String page, String limit, String category_id) {
        mView.showLoading();
        model.getChildCategoryList(page, limit, category_id, new JsonCallback<NewChildCategoryEntity>() {
            @Override
            public void onSuccess(Response<NewChildCategoryEntity> response) {
                mView.showChildData(response.body());
                mView.hideLoading();
            }

        });
    }
}
