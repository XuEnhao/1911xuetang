package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class CategoryEntity {


    /**
     * status : 0
     * msg : 成功
     * data : {"curriculumList":[{"id":"5","title":" 抓住用户心理，赋能精准运营","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956671","introduction":"","curriculum_time":"2000","teacher_id":"1","tag":"时政,新闻宣传","score":"0.0","study_number":"100","teacher_name":"1911教师","head_img":"/public/image/touxiang.png","graduate":"某某大学教授","teacher_content":"1911教育。","is_checked":true}],"pageCount":5}
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
         * curriculumList : [{"id":"5","title":" 抓住用户心理，赋能精准运营","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956671","introduction":"","curriculum_time":"2000","teacher_id":"1","tag":"时政,新闻宣传","score":"0.0","study_number":"100","teacher_name":"1911教师","head_img":"/public/image/touxiang.png","graduate":"某某大学教授","teacher_content":"1911教育。","is_checked":true}]
         * pageCount : 5
         */

        private int pageCount;
        private List<CurriculumListBean> curriculumList;

        public int getPageCount() {
            return pageCount;
        }

        public void setPageCount(int pageCount) {
            this.pageCount = pageCount;
        }

        public List<CurriculumListBean> getCurriculumList() {
            return curriculumList;
        }

        public void setCurriculumList(List<CurriculumListBean> curriculumList) {
            this.curriculumList = curriculumList;
        }

        public static class CurriculumListBean {
            /**
             * id : 5
             * title :  抓住用户心理，赋能精准运营
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526956671
             * introduction :
             * curriculum_time : 2000
             * teacher_id : 1
             * tag : 时政,新闻宣传
             * score : 0.0
             * study_number : 100
             * teacher_name : 1911教师
             * head_img : /public/image/touxiang.png
             * graduate : 某某大学教授
             * teacher_content : 1911教育。
             * is_checked : true
             */

            private String id;
            private String title;
            private String picture;
            private String introduction;
            private String curriculum_time;
            private String teacher_id;
//            private String tag;
            private String score;
            private String study_number;
            private String teacher_name;
            private String head_img;
            private String graduate;
            private String teacher_content;
            private String present_price;
            private boolean is_checked;

            public String getPresent_price() {
                return present_price;
            }

            public void setPresent_price(String present_price) {
                this.present_price = present_price;
            }

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

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getCurriculum_time() {
                return curriculum_time;
            }

            public void setCurriculum_time(String curriculum_time) {
                this.curriculum_time = curriculum_time;
            }

            public String getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(String teacher_id) {
                this.teacher_id = teacher_id;
            }

//            public String getTag() {
//                return tag;
//            }
//
//            public void setTag(String tag) {
//                this.tag = tag;
//            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getStudy_number() {
                return study_number;
            }

            public void setStudy_number(String study_number) {
                this.study_number = study_number;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

            public String getHead_img() {
                return head_img;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public String getGraduate() {
                return graduate;
            }

            public void setGraduate(String graduate) {
                this.graduate = graduate;
            }

            public String getTeacher_content() {
                return teacher_content;
            }

            public void setTeacher_content(String teacher_content) {
                this.teacher_content = teacher_content;
            }

            public boolean isIs_checked() {
                return is_checked;
            }

            public void setIs_checked(boolean is_checked) {
                this.is_checked = is_checked;
            }
        }
    }
}
