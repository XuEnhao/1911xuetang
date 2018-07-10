package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.module.model.SearchCurriculumModel;
import com.yijiuyiyiedu.xuetang.module.view.SearchCurriculumView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/23.
 */

public class SearchCurriculumPresenter {

    SearchCurriculumModel model;
    SearchCurriculumView mView;

    public SearchCurriculumPresenter(SearchCurriculumView mView) {
        this.mView = mView;
        model = new SearchCurriculumModel();
    }

    /**
     * 关键词搜索课程
     *
     * @param page
     * @param limit
     * @param searchWord
     * @param sort
     * @param userId
     */
    public void getSearchCurriculumData(String page, String limit, String searchWord, String sort, String userId) {
        mView.showLoading();
        model.getSearchCurriCulumList(page, limit, searchWord, sort, userId, new JsonCallback<SearchCurriCulumEntity>() {
            @Override
            public void onSuccess(Response<SearchCurriCulumEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }

    public void getRemodListData(String page, String limit, String category_id, String status_serial, String is_recommend, String title) {
        mView.showLoading();
        model.getRemoList(page, limit, category_id, status_serial, is_recommend, title, new JsonCallback<CategoryEntity>() {
            @Override
            public void onSuccess(Response<CategoryEntity> response) {
                mView.showRemodData(response.body());
                mView.hideLoading();
            }
        });
    }
}
