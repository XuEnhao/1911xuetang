package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/9.
 * 项目列表
 */

public class ProjectListEntity {


    /**
     * status : 1
     * msg : 成功
     * data : {"projectList":[{"id":"3","project_name":"项目综合3 名称","project_category_id":"5","project_price":"21.00 价钱","project_introduction":"232132132131 简介","project_content":"撒大三大四的撒  详情介绍","project_cover":"/uploads/Curriculum/2018/03/30/1522400819.png 封面图","status":"1","create_time":"222323243"}],"project_category_id":"5"}
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
        /**
         * projectList : [{"id":"3","project_name":"项目综合3 名称","project_category_id":"5","project_price":"21.00 价钱","project_introduction":"232132132131 简介","project_content":"撒大三大四的撒  详情介绍","project_cover":"/uploads/Curriculum/2018/03/30/1522400819.png 封面图","status":"1","create_time":"222323243"}]
         * project_category_id : 5
         */

        private String project_category_id;
        private List<ProjectListBean> projectList;

        public String getProject_category_id() {
            return project_category_id;
        }

        public void setProject_category_id(String project_category_id) {
            this.project_category_id = project_category_id;
        }

        public List<ProjectListBean> getProjectList() {
            return projectList;
        }

        public void setProjectList(List<ProjectListBean> projectList) {
            this.projectList = projectList;
        }

        public static class ProjectListBean {
            /**
             * id : 3
             * project_name : 项目综合3 名称
             * project_category_id : 5
             * project_price : 21.00 价钱
             * project_introduction : 232132132131 简介
             * project_content : 撒大三大四的撒  详情介绍
             * project_cover : /uploads/Curriculum/2018/03/30/1522400819.png 封面图
             * status : 1
             * create_time : 222323243
             */

            private String id;
            private String project_name;
            private String project_category_id;
            private String project_price;
            private String project_introduction;
            private String project_content;
            private String project_cover;
            private String status;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProject_name() {
                return project_name;
            }

            public void setProject_name(String project_name) {
                this.project_name = project_name;
            }

            public String getProject_category_id() {
                return project_category_id;
            }

            public void setProject_category_id(String project_category_id) {
                this.project_category_id = project_category_id;
            }

            public String getProject_price() {
                return project_price;
            }

            public void setProject_price(String project_price) {
                this.project_price = project_price;
            }

            public String getProject_introduction() {
                return project_introduction;
            }

            public void setProject_introduction(String project_introduction) {
                this.project_introduction = project_introduction;
            }

            public String getProject_content() {
                return project_content;
            }

            public void setProject_content(String project_content) {
                this.project_content = project_content;
            }

            public String getProject_cover() {
                return project_cover;
            }

            public void setProject_cover(String project_cover) {
                this.project_cover = project_cover;
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
