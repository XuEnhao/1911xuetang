package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/20.
 */

public class NewsEntity {


    /**
     * status : 1
     * msg : 成功
     * data : {"newsList":[{"id":"37","title":"从区块链到生命，许知远和王小川在1911主题餐厅都聊了什么？","picture":"http://p8p47jzeo.bkt.clouddn.com/1526957004","introduce":"从区块链到生命，许知远和王小川在1911主题餐厅都聊了什么？","create_time":"1522632996"}]}
     */

    private String status;
    private String msg;
    private DataBean data;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        private List<NewsListBean> newsList;

        public List<NewsListBean> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<NewsListBean> newsList) {
            this.newsList = newsList;
        }

        public static class NewsListBean {
            /**
             * id : 37
             * title : 从区块链到生命，许知远和王小川在1911主题餐厅都聊了什么？
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526957004
             * introduce : 从区块链到生命，许知远和王小川在1911主题餐厅都聊了什么？
             * create_time : 1522632996
             */

            private String id;
            private String title;
            private String picture;
            private String introduce;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }
    }
}
