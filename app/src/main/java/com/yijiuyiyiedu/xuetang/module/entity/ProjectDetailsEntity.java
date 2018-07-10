package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/9.
 */

public class ProjectDetailsEntity {

    /**
     * status : 1
     * msg : 成功
     * data : {"projectDetail":{"id":"1","project_name":"修改测试测试测试测试测试","project_category_id":"1","project_price":"40.00","project_introduction":"使得房贷首付多少份","project_content":"","project_cover":"/uploads/Curriculum/2018/04/03/1522742585.jpg","status":"1","create_time":"1522743695"},"projectCurriculumList":[{"id":"4","category_id":"26","title":"论微积分的易学性","lecturer":"讲师二","introduction":"新建课程一新建课程一新建课程一新建课程一新建课程一","status_serial":"1","curriculum_number":"60453198","study_number":"1","class_number":"100","curriculum_time":"","original_price":"100.00","present_price":"0.01","score":"0.0","content":"","picture":"/uploads/Curriculum/2018/03/30/1522400778.png","study_picture":"/uploads/Curriculum/2018/03/30/1522401153.png","is_recommend":"2","status":"1","create_time":"1522146657"}]}
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
         * projectDetail : {"id":"1","project_name":"修改测试测试测试测试测试","project_category_id":"1","project_price":"40.00","project_introduction":"使得房贷首付多少份","project_content":"","project_cover":"/uploads/Curriculum/2018/04/03/1522742585.jpg","status":"1","create_time":"1522743695"}
         * projectCurriculumList : [{"id":"4","category_id":"26","title":"论微积分的易学性","lecturer":"讲师二","introduction":"新建课程一新建课程一新建课程一新建课程一新建课程一","status_serial":"1","curriculum_number":"60453198","study_number":"1","class_number":"100","curriculum_time":"","original_price":"100.00","present_price":"0.01","score":"0.0","content":"","picture":"/uploads/Curriculum/2018/03/30/1522400778.png","study_picture":"/uploads/Curriculum/2018/03/30/1522401153.png","is_recommend":"2","status":"1","create_time":"1522146657"}]
         */

        private ProjectDetailBean projectDetail;
        private List<ProjectCurriculumListBean> projectCurriculumList;

        public ProjectDetailBean getProjectDetail() {
            return projectDetail;
        }

        public void setProjectDetail(ProjectDetailBean projectDetail) {
            this.projectDetail = projectDetail;
        }

        public List<ProjectCurriculumListBean> getProjectCurriculumList() {
            return projectCurriculumList;
        }

        public void setProjectCurriculumList(List<ProjectCurriculumListBean> projectCurriculumList) {
            this.projectCurriculumList = projectCurriculumList;
        }

        public static class ProjectDetailBean {
            /**
             * id : 1
             * project_name : 修改测试测试测试测试测试
             * project_category_id : 1
             * project_price : 40.00
             * project_introduction : 使得房贷首付多少份
             * project_content :
             * project_cover : /uploads/Curriculum/2018/04/03/1522742585.jpg
             * status : 1
             * create_time : 1522743695
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

        public static class ProjectCurriculumListBean {
            /**
             * id : 4
             * category_id : 26
             * title : 论微积分的易学性
             * lecturer : 讲师二
             * introduction : 新建课程一新建课程一新建课程一新建课程一新建课程一
             * status_serial : 1
             * curriculum_number : 60453198
             * study_number : 1
             * class_number : 100
             * curriculum_time :
             * original_price : 100.00
             * present_price : 0.01
             * score : 0.0
             * content :
             * picture : /uploads/Curriculum/2018/03/30/1522400778.png
             * study_picture : /uploads/Curriculum/2018/03/30/1522401153.png
             * is_recommend : 2
             * status : 1
             * create_time : 1522146657
             */

            private String id;
            private String category_id;
            private String title;
            private String lecturer;
            private String introduction;
            private String status_serial;
            private String curriculum_number;
            private String study_number;
            private String class_number;
            private String curriculum_time;
            private String original_price;
            private String present_price;
            private String score;
            private String content;
            private String picture;
            private String study_picture;
            private String is_recommend;
            private String status;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_id() {
                return category_id;
            }

            public void setCategory_id(String category_id) {
                this.category_id = category_id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getLecturer() {
                return lecturer;
            }

            public void setLecturer(String lecturer) {
                this.lecturer = lecturer;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getStatus_serial() {
                return status_serial;
            }

            public void setStatus_serial(String status_serial) {
                this.status_serial = status_serial;
            }

            public String getCurriculum_number() {
                return curriculum_number;
            }

            public void setCurriculum_number(String curriculum_number) {
                this.curriculum_number = curriculum_number;
            }

            public String getStudy_number() {
                return study_number;
            }

            public void setStudy_number(String study_number) {
                this.study_number = study_number;
            }

            public String getClass_number() {
                return class_number;
            }

            public void setClass_number(String class_number) {
                this.class_number = class_number;
            }

            public String getCurriculum_time() {
                return curriculum_time;
            }

            public void setCurriculum_time(String curriculum_time) {
                this.curriculum_time = curriculum_time;
            }

            public String getOriginal_price() {
                return original_price;
            }

            public void setOriginal_price(String original_price) {
                this.original_price = original_price;
            }

            public String getPresent_price() {
                return present_price;
            }

            public void setPresent_price(String present_price) {
                this.present_price = present_price;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
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

            public String getStudy_picture() {
                return study_picture;
            }

            public void setStudy_picture(String study_picture) {
                this.study_picture = study_picture;
            }

            public String getIs_recommend() {
                return is_recommend;
            }

            public void setIs_recommend(String is_recommend) {
                this.is_recommend = is_recommend;
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
