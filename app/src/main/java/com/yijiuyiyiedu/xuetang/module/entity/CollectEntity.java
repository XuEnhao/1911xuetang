package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/26.
 */

public class CollectEntity {


    /**
     * status : 0
     * msg : 成功
     * data : {"userCollection":[{"id":"13","title":"领导风格","introduction":"本课程从基础的 Vue源码目录设计、源码构建开始讲起，包括数据驱动，响应式原理，让同学们深入全面理解Vue的实现原理，掌握源码分析技巧，牢固对Vue的使用，斩断BAT进阶拦路虎，快人一步进名企。 ","curriculum_time":"100","present_price":"9.00","study_number":"21","teacher_id":"4","tag":[],"picture":"http://p8p47jzeo.bkt.clouddn.com/1528624884","teacher_name":"李洪山","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528463389","graduate":"贵州大学教师","percent":0},{"id":"15","title":"职场礼仪、商务礼仪","introduction":"本课程从基础的 Vue源码目录设计、源码构建开始讲起，包括数据驱动，响应式原理，让同学们深入全面理解Vue的实现原理，掌握源码分析技巧，牢固对Vue的使用，斩断BAT进阶拦路虎，快人一步进名企。 ","curriculum_time":"12","present_price":"1.00","study_number":"13","teacher_id":"3","tag":[],"picture":"http://p8p47jzeo.bkt.clouddn.com/1528624851","teacher_name":"王刚","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528625089","graduate":"北京大学教授","percent":0},{"id":"14","title":"新能源与可持续发展","introduction":"本课程从基础的 Vue源码目录设计、源码构建开始讲起，包括数据驱动，响应式原理，让同学们深入全面理解Vue的实现原理，掌握源码分析技巧，牢固对Vue的使用，斩断BAT进阶拦路虎，快人一步进名企。 ","curriculum_time":"1000","present_price":"20.00","study_number":"45","teacher_id":"6","tag":[],"picture":"http://p8p47jzeo.bkt.clouddn.com/1528624865","teacher_name":"闫宁","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528463404","graduate":"清华大学教授","percent":0},{"id":"18","title":"PHP进阶篇","introduction":"通过PHP学习的进阶篇的学习，你可以对PHP的理论知识由浅入深有更深一步的掌握，这些知识能够使您更加全面的掌握PHP，从而助您在实际工作中使用PHP快速开发网站程序。","curriculum_time":"200","present_price":"50.00","study_number":"0","teacher_id":"6","tag":["法律","金融"],"picture":"http://p8p47jzeo.bkt.clouddn.com/1528652869","teacher_name":"闫宁","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528463404","graduate":"清华大学教授","percent":0},{"id":"3","title":"策略性商务谈判中的谋略运用","introduction":"谈判无处不在，无论是采购、销售、合作，还是日常的沟通，都会涉及到谈判，这是职业人士必备的一种职业素质，也是各种商务活动中频繁使用的一种重要能力。","curriculum_time":"100","present_price":"0.01","study_number":"0","teacher_id":"3","tag":[],"picture":"http://p8p47jzeo.bkt.clouddn.com/1528448752","teacher_name":"王刚","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528625089","graduate":"北京大学教授","percent":0},{"id":"2","title":" Frank 365天陪伴式英语私教课","introduction":"本课程从基础的 Vue源码目录设计、源码构建开始讲起，包括数据驱动，响应式原理，让同学们深入全面理解Vue的实现原理，掌握源码分析技巧，牢固对Vue的使用，斩断BAT进阶拦路虎，快人一步进名企。 ","curriculum_time":"30","present_price":"0.01","study_number":"0","teacher_id":"5","tag":[],"picture":"http://p8p47jzeo.bkt.clouddn.com/1528448773","teacher_name":"莎良朋","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528643986","graduate":"华中科技大学博士","percent":13},{"id":"1","title":"数据挖掘：理论与算法","introduction":"最有趣的理论+最有用的算法=不得不学的数据科学。","curriculum_time":"100","present_price":"0.01","study_number":"0","teacher_id":"5","tag":[],"picture":"http://p8p47jzeo.bkt.clouddn.com/1528448788","teacher_name":"莎良朋","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528643986","graduate":"华中科技大学博士","percent":0},{"id":"4","title":"日本式人际沟通术【日语中字】","introduction":"本课程从基础的 Vue源码目录设计、源码构建开始讲起，包括数据驱动，响应式原理，让同学们深入全面理解Vue的实现原理，掌握源码分析技巧，牢固对Vue的使用，斩断BAT进阶拦路虎，快人一步进名企。 ","curriculum_time":"1000","present_price":"0.01","study_number":"50","teacher_id":"3","tag":[],"picture":"http://p8p47jzeo.bkt.clouddn.com/1528448726","teacher_name":"王刚","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528625089","graduate":"北京大学教授","percent":0}]}
     */

    private int status;
    private String msg;
    private DataBean data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
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
        private List<UserCollectionBean> userCollection;

        public List<UserCollectionBean> getUserCollection() {
            return userCollection;
        }

        public void setUserCollection(List<UserCollectionBean> userCollection) {
            this.userCollection = userCollection;
        }

        public static class UserCollectionBean {
            /**
             * id : 13
             * title : 领导风格
             * introduction : 本课程从基础的 Vue源码目录设计、源码构建开始讲起，包括数据驱动，响应式原理，让同学们深入全面理解Vue的实现原理，掌握源码分析技巧，牢固对Vue的使用，斩断BAT进阶拦路虎，快人一步进名企。
             * curriculum_time : 100
             * present_price : 9.00
             * study_number : 21
             * teacher_id : 4
             * tag : []
             * picture : http://p8p47jzeo.bkt.clouddn.com/1528624884
             * teacher_name : 李洪山
             * head_img : http://p8p47jzeo.bkt.clouddn.com/1528463389
             * graduate : 贵州大学教师
             * percent : 0
             */

            private String id;
            private String title;
            private String introduction;
            private String curriculum_time;
            private String present_price;
            private String study_number;
            private String teacher_id;
            private String picture;
            private String teacher_name;
            private String head_img;
            private String graduate;
            private int percent;
            private List<?> tag;

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

            public String getPresent_price() {
                return present_price;
            }

            public void setPresent_price(String present_price) {
                this.present_price = present_price;
            }

            public String getStudy_number() {
                return study_number;
            }

            public void setStudy_number(String study_number) {
                this.study_number = study_number;
            }

            public String getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(String teacher_id) {
                this.teacher_id = teacher_id;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
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

            public int getPercent() {
                return percent;
            }

            public void setPercent(int percent) {
                this.percent = percent;
            }

            public List<?> getTag() {
                return tag;
            }

            public void setTag(List<?> tag) {
                this.tag = tag;
            }
        }
    }
}
