package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.lzy.okgo.OkGo;

/**
 * Created by xuenhao on 2018/3/23.
 * 搜索页面model
 */

public class SearchCurriculumModel {

    /**
     * @param page
     * @param limit
     * @param searchWord 关键词
     * @param sortBy     排序
     * @param userId     用户id 用于保存历史搜索
     * @param callback
     */
    public void getSearchCurriCulumList(String page, String limit, String searchWord, String sortBy, String userId, JsonCallback<SearchCurriCulumEntity> callback) {
        OkGo.<SearchCurriCulumEntity>get(Constant.SEARCH_CURRICULUM)
                .params("page", page)
                .params("limit", limit)
                .params("search_word", searchWord)
                .params("sort_by", sortBy)
                .params("user_id", userId)
                .execute(callback);
    }

    public void getRemoList(String page, String limit, String category_id, String status_serial, String is_recommend, String title, JsonCallback<CategoryEntity> callback) {
        OkGo.<CategoryEntity>get(Constant.CURRICULUM_LIST)
                .params("page", page)
                .params("limit", limit)
                .params("category_id", category_id)
                .params("status_serial", status_serial)
                .params("is_recommend", is_recommend)
                .params("title", title)
                .execute(callback);
    }
}
