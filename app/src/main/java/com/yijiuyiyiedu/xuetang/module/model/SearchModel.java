package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.ClearHistoryEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchCurriCulumEntity;
import com.yijiuyiyiedu.xuetang.module.entity.SearchEntity;
import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.module.entity.SearchNoneEntity;

/**
 * Created by xuenhao on 2018/3/23.
 */

public class SearchModel {

    /**
     * 获取历史记录 热门搜索
     *
     * @param token 用户token
     * @param callback
     */
    public void getHotHistory(String token, JsonCallback<SearchEntity> callback) {
        OkGo.<SearchEntity>post(Constant.SEARCH_RECORDLIST)
                .headers("Authorization", token)
                .execute(callback);
    }

    /**
     * 清除历史记录
     *
     * @param token
     * @param callback
     */
    public void clearHistory(String token, JsonCallback<ClearHistoryEntity> callback) {
        OkGo.<ClearHistoryEntity>post(Constant.CLEAR_HISTORY)
                .headers("Authorization", token)
                .execute(callback);
    }

    /**
     * @param page
     * @param limit
     * @param searchWord 关键词
     * @param sortBy     排序
     * @param token     用户token 用于保存历史搜索
     * @param callback
     */
    public void getSearchCurriCulumList(String page, String limit, String searchWord, String sortBy, String token, JsonCallback<SearchCurriCulumEntity> callback) {
        OkGo.<SearchCurriCulumEntity>get(Constant.SEARCH_CURRICULUM)
                .params("page", page)
                .params("limit", limit)
                .params("search_word", searchWord)
                .params("sort_by", sortBy)
                .headers("Authorization", token)
                .execute(callback);
    }

    /**
     * 没有搜索到结果时返回
     * @param callback
     */
    public void getSearchNoneData(JsonCallback<SearchNoneEntity> callback){
        OkGo.<SearchNoneEntity>get(Constant.SEARCH_NONE)
                .execute(callback);
    }
}
