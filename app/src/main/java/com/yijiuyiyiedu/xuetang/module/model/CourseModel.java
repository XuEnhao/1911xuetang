package com.yijiuyiyiedu.xuetang.module.model;

import com.yijiuyiyiedu.xuetang.api.constant.Constant;
import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.lzy.okgo.OkGo;
import com.yijiuyiyiedu.xuetang.module.entity.NewsEntity;

/**
 * Created by xuenhao on 2018/3/19.
 */

public class CourseModel {

    public CourseModel() {
    }

    /**
     * 获取首页数据
     *
     * @param callback
     */
    public void getHomeData(JsonCallback<CourseEntity> callback) {
        OkGo.<CourseEntity>get(Constant.HOME)
                .tag(this)
                .execute(callback);
    }

    /**
     * 获取新闻列表
     * @param page
     * @param limit
     * @param callback
     */
    public void getNews(String page, String limit, JsonCallback<NewsEntity> callback){
        OkGo.<NewsEntity>get(Constant.NEWS_LIST)
                .tag(this)
                .params("page",page)
                .params("limit",limit)
                .execute(callback);
    }
}
