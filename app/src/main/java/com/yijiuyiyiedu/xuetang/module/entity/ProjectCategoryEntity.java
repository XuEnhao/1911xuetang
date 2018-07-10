package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/9.
 */

public class ProjectCategoryEntity {

    /**
     * status : 1
     * msg : 成功
     * data : {"projectCategoryList":[{"id":"5","category_name":"党政干部定制研修项目 分类名称","content":"分类介绍","picture":"/uploads/Category/2018/04/08/1523167072.png  //web端图片","app_picture":"//App端图片","parent_id":"0","sort_order":"1","status":"1","create_time":"1522769176"}]}
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
        private List<ProjectCategoryListBean> projectCategoryList;

        public List<ProjectCategoryListBean> getProjectCategoryList() {
            return projectCategoryList;
        }

        public void setProjectCategoryList(List<ProjectCategoryListBean> projectCategoryList) {
            this.projectCategoryList = projectCategoryList;
        }

        public static class ProjectCategoryListBean {
            /**
             * id : 5
             * category_name : 党政干部定制研修项目 分类名称
             * content : 分类介绍
             * picture : /uploads/Category/2018/04/08/1523167072.png  //web端图片
             * app_picture : //App端图片
             * parent_id : 0
             * sort_order : 1
             * status : 1
             * create_time : 1522769176
             */

            private String id;
            private String category_name;
            private String content;
            private String picture;
            private String app_picture;
            private String parent_id;
            private String sort_order;
            private String status;
            private String create_time;
            private String isColor;

            public String getIsColor() {
                return isColor;
            }

            public void setIsColor(String isColor) {
                this.isColor = isColor;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getApp_picture() {
                return app_picture;
            }

            public void setApp_picture(String app_picture) {
                this.app_picture = app_picture;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public String getSort_order() {
                return sort_order;
            }

            public void setSort_order(String sort_order) {
                this.sort_order = sort_order;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
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
