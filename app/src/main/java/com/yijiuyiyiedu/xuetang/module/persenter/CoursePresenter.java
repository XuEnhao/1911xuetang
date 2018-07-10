package com.yijiuyiyiedu.xuetang.module.persenter;

import com.yijiuyiyiedu.xuetang.module.base.JsonCallback;
import com.yijiuyiyiedu.xuetang.module.entity.CourseEntity;
import com.yijiuyiyiedu.xuetang.module.entity.NewsEntity;
import com.yijiuyiyiedu.xuetang.module.model.CourseModel;
import com.yijiuyiyiedu.xuetang.module.view.CourseView;
import com.lzy.okgo.model.Response;

/**
 * Created by xuenhao on 2018/3/19.
 * 主页
 */

public class CoursePresenter {
    CourseModel model;
    CourseView mView;

    public CoursePresenter(CourseView mView) {
        this.mView = mView;
        model = new CourseModel();
    }


    public void getData() {
        mView.showLoading();
        model.getHomeData(new JsonCallback<CourseEntity>() {
            @Override
            public void onSuccess(Response<CourseEntity> response) {
                mView.showData(response.body());
                mView.hideLoading();
            }
        });
    }

    public void getNewsData(String page,String limit){
        model.getNews(page, limit, new JsonCallback<NewsEntity>() {
            @Override
            public void onSuccess(Response<NewsEntity> response) {
                mView.showNewsData(response.body());
            }
        });
    }
}
