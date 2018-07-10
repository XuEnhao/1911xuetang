package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/8.
 */

public class SearchNoneEntity {

    /**
     * status : 0
     * msg : ok
     * data : [{"id":"5","category_id":"54","title":" 抓住用户心理，赋能精准运营","teacher_id":"1","introduction":"在用户研究的领域，定性分析与定量分析相辅相成、不可或缺。本次课程将通过案例，讲解人的通识性需求，通过哪些途径和方法快速定位用户特殊需求。同时，本次课程将会从数据分析的角度出发，结合具体案例，解读用户研","status_serial":"1","curriculum_number":"x_5","study_number":"100","class_number":"5","curriculum_time":"2000","original_price":"3000.00","present_price":"0.01","score":"0.0","content":"","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956671","study_picture":"http://p8p47jzeo.bkt.clouddn.com/1526956675","tag":"时政,新闻宣传","is_recommend":"2","is_member_see":"1","is_category":"0","status":"1","create_time":"1526016684"},{"id":"2","category_id":"54","title":" Frank 365天陪伴式英语私教课","teacher_id":"5","introduction":"学英语是一场修行，我讲我学到最好的知识，你看这世界最美的风景。不求太多虚名，只求做到 \u201c渡人、渡己\u201d。","status_serial":"1","curriculum_number":"x_2","study_number":"0","class_number":"10","curriculum_time":"60/节","original_price":"100.00","present_price":"0.01","score":"0.0","content":"<p>\t\t\t\t\t\t<\/p><p><br/><\/p><p>场修行，我讲我学到最好的知识，你看这世界最美的风景。不求太多虚名，只求做到 \u201c渡人、渡己\u201d。<\/p><p>场修行，我讲我学到最好的知识，你看这世界最美的风景。不求太多虚名，只求做到 \u201c渡人、渡己\u201d。<\/p><p>场修行，我讲我学到最好的知识，你看这世界最美的风景。不求太多虚名，只求做到 \u201c渡人、渡己\u201d。<\/p><p>场修行，我讲我学到最好的知识，你看这世界最美的风景。不求太多虚名，只求做到 \u201c渡人、渡己\u201d。<\/p><p>&nbsp; &nbsp;\t\t\t\t<\/p><p>&nbsp; &nbsp;\t\t\t\t<\/p>","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956723","study_picture":"http://p8p47jzeo.bkt.clouddn.com/1526956727","tag":"","is_recommend":"1","is_member_see":"1","is_category":"0","status":"1","create_time":"1526010111"}]
     */

    private int status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 5
         * category_id : 54
         * title :  抓住用户心理，赋能精准运营
         * teacher_id : 1
         * introduction : 在用户研究的领域，定性分析与定量分析相辅相成、不可或缺。本次课程将通过案例，讲解人的通识性需求，通过哪些途径和方法快速定位用户特殊需求。同时，本次课程将会从数据分析的角度出发，结合具体案例，解读用户研
         * status_serial : 1
         * curriculum_number : x_5
         * study_number : 100
         * class_number : 5
         * curriculum_time : 2000
         * original_price : 3000.00
         * present_price : 0.01
         * score : 0.0
         * content :
         * picture : http://p8p47jzeo.bkt.clouddn.com/1526956671
         * study_picture : http://p8p47jzeo.bkt.clouddn.com/1526956675
         * tag : 时政,新闻宣传
         * is_recommend : 2
         * is_member_see : 1
         * is_category : 0
         * status : 1
         * create_time : 1526016684
         */

        private String id;
        private String category_id;
        private String title;
        private String teacher_id;
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
        private String tag;
        private String is_recommend;
        private String is_member_see;
        private String is_category;
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

        public String getTag() {
            return tag;
        }

        public void setTag(String tag) {
            this.tag = tag;
        }

        public String getIs_recommend() {
            return is_recommend;
        }

        public void setIs_recommend(String is_recommend) {
            this.is_recommend = is_recommend;
        }

        public String getIs_member_see() {
            return is_member_see;
        }

        public void setIs_member_see(String is_member_see) {
            this.is_member_see = is_member_see;
        }

        public String getIs_category() {
            return is_category;
        }

        public void setIs_category(String is_category) {
            this.is_category = is_category;
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
