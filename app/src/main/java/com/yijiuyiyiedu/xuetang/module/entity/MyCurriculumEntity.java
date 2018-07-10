package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/13.
 */

public class MyCurriculumEntity {

    /**
     * status : 0
     * msg : 成功
     * data : {"curriculumList":[{"id":"2","title":" Frank 365天陪伴式英语私教课","teacher_id":"2","introduction":"","curriculum_number":"x_2","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956723","tag":"","teacher_name":"1911教师2","head_img":"/public/image/touxiang.png","graduate":"某某大学教授","percent":100}],"pageCount":1}
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
         * curriculumList : [{"id":"2","title":" Frank 365天陪伴式英语私教课","teacher_id":"2","introduction":"","curriculum_number":"x_2","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956723","tag":"","teacher_name":"1911教师2","head_img":"/public/image/touxiang.png","graduate":"某某大学教授","percent":100}]
         * pageCount : 1
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
             * id : 2
             * title :  Frank 365天陪伴式英语私教课
             * teacher_id : 2
             * introduction :
             * curriculum_number : x_2
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526956723
             * tag :
             * teacher_name : 1911教师2
             * head_img : /public/image/touxiang.png
             * graduate : 某某大学教授
             * percent : 100
             */

            private String id;
            private String title;
            private String teacher_id;
            private String introduction;
            private String curriculum_number;
            private String picture;
//            private String tag;
            private String teacher_name;
            private String head_img;
            private String graduate;
            private int percent;

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

            public String getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(String teacher_id) {
                this.teacher_id = teacher_id;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
            }

            public String getCurriculum_number() {
                return curriculum_number;
            }

            public void setCurriculum_number(String curriculum_number) {
                this.curriculum_number = curriculum_number;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

//            public String getTag() {
//                return tag;
//            }
//
//            public void setTag(String tag) {
//                this.tag = tag;
//            }

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

            public int getPercent() {
                return percent;
            }

            public void setPercent(int percent) {
                this.percent = percent;
            }
        }
    }
}
