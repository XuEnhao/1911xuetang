package com.yijiuyiyiedu.xuetang.module.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by xuenhao on 2018/3/20.
 */

public class StudyEntity implements Serializable {


    /**
     * status : 1
     * msg : 成功
     * data : {"userCurriculum":[{"id":"9","user_id":"用户id","curriculum_id":"2 课程id","type":"1 类型  1 个人用户  2 企业","enterprise_user_id":"2 企业id","order_id":"1 对应的订单id","status":"1 状态","create_time":" 创建时间","title":"课程标题","picture":"封面","study_picture":"学习封面","see_title":"第一小节 观看的小节名称"}],"userInfo":{"id":"2","user_name":"用户账号","head_img":"头像","openid":"","password":"","nick_name":"昵称","real_name":"姓名","staff_position":"员工职位","sex":" 性别","status":"1","create_time":"创建时间"}}
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
         * userCurriculum : [{"id":"9","user_id":"用户id","curriculum_id":"2 课程id","type":"1 类型  1 个人用户  2 企业","enterprise_user_id":"2 企业id","order_id":"1 对应的订单id","status":"1 状态","create_time":" 创建时间","title":"课程标题","picture":"封面","study_picture":"学习封面","see_title":"第一小节 观看的小节名称"}]
         * userInfo : {"id":"2","user_name":"用户账号","head_img":"头像","openid":"","password":"","nick_name":"昵称","real_name":"姓名","staff_position":"员工职位","sex":" 性别","status":"1","create_time":"创建时间"}
         */

        private UserInfoBean userInfo;
        private List<UserCurriculumBean> userCurriculum;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<UserCurriculumBean> getUserCurriculum() {
            return userCurriculum;
        }

        public void setUserCurriculum(List<UserCurriculumBean> userCurriculum) {
            this.userCurriculum = userCurriculum;
        }

        public static class UserInfoBean {
            /**
             * id : 2
             * user_name : 用户账号
             * head_img : 头像
             * openid :
             * password :
             * nick_name : 昵称
             * real_name : 姓名
             * staff_position : 员工职位
             * sex :  性别
             * status : 1
             * create_time : 创建时间
             */

            private String id;
            private String user_name;
            private String head_img;
            private String openid;
            private String password;
            private String nick_name;
            private String real_name;
            private String staff_position;
            private String sex;
            private String status;
            private String create_time;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_name() {
                return user_name;
            }

            public void setUser_name(String user_name) {
                this.user_name = user_name;
            }

            public String getHead_img() {
                return head_img;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public String getOpenid() {
                return openid;
            }

            public void setOpenid(String openid) {
                this.openid = openid;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getReal_name() {
                return real_name;
            }

            public void setReal_name(String real_name) {
                this.real_name = real_name;
            }

            public String getStaff_position() {
                return staff_position;
            }

            public void setStaff_position(String staff_position) {
                this.staff_position = staff_position;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
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

        public static class UserCurriculumBean {
            /**
             * id : 9
             * user_id : 用户id
             * curriculum_id : 2 课程id
             * type : 1 类型  1 个人用户  2 企业
             * enterprise_user_id : 2 企业id
             * order_id : 1 对应的订单id
             * status : 1 状态
             * create_time :  创建时间
             * title : 课程标题
             * picture : 封面
             * study_picture : 学习封面
             * see_title : 第一小节 观看的小节名称
             */

            private String id;
            private String user_id;
            private String curriculum_id;
            private String type;
            private String enterprise_user_id;
            private String order_id;
            private String status;
            private String create_time;
            private String title;
            private String picture;
            private String study_picture;
            private String see_title;
            private String lecturer;

            public String getLecturer() {
                return lecturer;
            }

            public void setLecturer(String lecturer) {
                this.lecturer = lecturer;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getCurriculum_id() {
                return curriculum_id;
            }

            public void setCurriculum_id(String curriculum_id) {
                this.curriculum_id = curriculum_id;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getEnterprise_user_id() {
                return enterprise_user_id;
            }

            public void setEnterprise_user_id(String enterprise_user_id) {
                this.enterprise_user_id = enterprise_user_id;
            }

            public String getOrder_id() {
                return order_id;
            }

            public void setOrder_id(String order_id) {
                this.order_id = order_id;
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

            public String getStudy_picture() {
                return study_picture;
            }

            public void setStudy_picture(String study_picture) {
                this.study_picture = study_picture;
            }

            public String getSee_title() {
                return see_title;
            }

            public void setSee_title(String see_title) {
                this.see_title = see_title;
            }
        }
    }
}
