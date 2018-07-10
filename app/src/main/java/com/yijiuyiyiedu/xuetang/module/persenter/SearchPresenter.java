package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchNoneEntity;
import com.yijiuyiyiedu.xuetang.module.model.SearchModel;
import com.yijiuyiyiedu.xuetang.module.view.SearchView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/23.
 */

public class SearchPresenter {
    SearchModel model;
    SearchView mView;

    public SearchPresenter(SearchView mView) {
        this.mView = mView;
        model = new SearchModel();
    }

    /**
     * 获取热门搜索历史记录
     *
     * @param userId
     */
    public void getHotHistory(String userId) {
        model.getHotHistory(userId, new JsonCallback<SearchEntity>() {
            @Override
            public void onSuccess(Response<SearchEntity> response) {
                mView.showData(response.body());
            }
        });
    }

    /**
     * 清除历史记录
     *
     * @param userId
     */
    public void clearHistory(String userId) {
        model.clearHistory(userId, new JsonCallback<ClearHistoryEntity>() {
            @Override
            public void onSuccess(Response<ClearHistoryEntity> response) {
                mView.showClearData(response.body());
            }
        });
    }

    /**
     * 获取搜索列表
     * @param page
     * @param limit
     * @param searchWord
     * @param sortBy
     * @param userId
     */
    public void getCurriculumData(String page, String limit, String searchWord, String sortBy, String userId){
        mView.showLoading();
        model.getSearchCurriCulumList(page, limit, searchWord, sortBy, userId, new JsonCallback<SearchCurriCulumEntity>() {
            @Override
            public void onSuccess(Response<SearchCurriCulumEntity> response) {
                mView.showCurriculumData(response.body());
                mView.hideLoading();
            }
        });
    }

    /**
     * 搜索无结果是展示的猜你喜欢
     */
    public void getSearchNoneData(){
        model.getSearchNoneData(new JsonCallback<SearchNoneEntity>() {
            @Override
            public void onSuccess(Response<SearchNoneEntity> response) {
                mView.showNoneData(response.body());
            }
        });
    }
}
