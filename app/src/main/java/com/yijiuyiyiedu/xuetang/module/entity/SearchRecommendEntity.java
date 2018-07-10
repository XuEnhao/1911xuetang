package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/4/4.
 */

public class SearchRecommendEntity {

    /**
     * id : 1 课程id
     * category_id : 1 课程分类id
     * title : 课程名称
     * lecturer : 讲师
     * introduction : 课程介绍
     * status_serial : 1 是否连载
     * study_number : 学习人数
     * class_number : 0 课时
     * original_price : 原价
     * present_price : 现价
     * score : 评分
     * content : 课程详情
     * picture : 封面图片
     * is_recommend : 1 是否推荐
     * status : 1
     * create_time : 创建时间
     */

    private String id;
    private String category_id;
    private String title;
    private String lecturer;
    private String introduction;
    private String status_serial;
    private String study_number;
    private String class_number;
    private String original_price;
    private String present_price;
    private String score;
    private String content;
    private String picture;
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
