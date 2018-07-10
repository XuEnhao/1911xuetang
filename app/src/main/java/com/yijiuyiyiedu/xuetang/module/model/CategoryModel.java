package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CategoryEntity;
import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.module.entity.NewChildCategoryEntity;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class CategoryModel {

    /**
     *
     * @param page page
     * @param limit
     * @param status_serial
     * @param is_recommend
     * @param search_word
     * @param sort_by
     * @param teacher_id 老师id
     * @param category_ida 父类id
     * @param category_idb 二级id
     * @param isHide       是否隐藏
     * @param callback
     */
    public void getCategoryList(String page, String limit, String status_serial, String is_recommend, String search_word, String sort_by, String teacher_id, String category_ida, String category_idb,int isHide,JsonCallback<CategoryEntity> callback) {
        OkGo.<CategoryEntity>get(Constant.CURRICULUM_LIST)
                .params("page", page)
                .params("limit", limit)
                .params("status_serial", status_serial)
                .params("is_recommend", is_recommend)
                .params("search_word", search_word)
                .params("sort_by", sort_by)
                .params("teacher_id", teacher_id)
                .params("category_id_a", category_ida)
                .params("category_id_b", category_idb)
                .params("is_hide",isHide )
                .execute(callback);
    }

    /**
     * 获取下级分类列表
     *
     * @param page
     * @param limit
     * @param category_id
     * @param callback
     */
    public void getChildCategoryList(String page, String limit, String category_id, JsonCallback<NewChildCategoryEntity> callback) {
        OkGo.<NewChildCategoryEntity>get(Constant.CHILD_CATEGORY_LIST)
                .params("page", page)
                .params("limit", limit)
                .params("category_id", category_id)
                .execute(callback);
    }
}
