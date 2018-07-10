package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/20.
 * 用户已购项目
 */

public class BoughtProjectEntity {

    /**
     * status : 1
     * msg : 成功
     * data : {"userProject":[{"id":"6","project_id":"16","project_cover":"/uploads/Curriculum/2018/04/12/1523527992.png","project_name":"事业单位党性知识学习项目"}],"userInfo":{"id":"1","user_name":"18701513650","head_img":"/uploads/abc/2018/04/20/1524205495.png","openid":"","password":"e10adc3949ba59abbe56e057f20f883e","nick_name":"微尘","real_name":"","birthday":"2018-04-18","email":"123221212121","sex":"1","login_auth":"cyWSjOtpQnEe2zsKRI4Y5abh8vwJglHu","status":"1","create_time":"1523968944"}}
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
         * userProject : [{"id":"6","project_id":"16","project_cover":"/uploads/Curriculum/2018/04/12/1523527992.png","project_name":"事业单位党性知识学习项目"}]
         * userInfo : {"id":"1","user_name":"18701513650","head_img":"/uploads/abc/2018/04/20/1524205495.png","openid":"","password":"e10adc3949ba59abbe56e057f20f883e","nick_name":"微尘","real_name":"","birthday":"2018-04-18","email":"123221212121","sex":"1","login_auth":"cyWSjOtpQnEe2zsKRI4Y5abh8vwJglHu","status":"1","create_time":"1523968944"}
         */

        private UserInfoBean userInfo;
        private List<UserProjectBean> userProject;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public List<UserProjectBean> getUserProject() {
            return userProject;
        }

        public void setUserProject(List<UserProjectBean> userProject) {
            this.userProject = userProject;
        }

        public static class UserInfoBean {
            /**
             * id : 1
             * user_name : 18701513650
             * head_img : /uploads/abc/2018/04/20/1524205495.png
             * openid :
             * password : e10adc3949ba59abbe56e057f20f883e
             * nick_name : 微尘
             * real_name :
             * birthday : 2018-04-18
             * email : 123221212121
             * sex : 1
             * login_auth : cyWSjOtpQnEe2zsKRI4Y5abh8vwJglHu
             * status : 1
             * create_time : 1523968944
             */

            private String id;
            private String user_name;
            private String head_img;
            private String openid;
            private String password;
            private String nick_name;
            private String real_name;
            private String birthday;
            private String email;
            private String sex;
            private String login_auth;
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

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getEmail() {
                return email;
            }

            public void setEmail(String email) {
                this.email = email;
            }

            public String getSex() {
                return sex;
            }

            public void setSex(String sex) {
                this.sex = sex;
            }

            public String getLogin_auth() {
                return login_auth;
            }

            public void setLogin_auth(String login_auth) {
                this.login_auth = login_auth;
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

        public static class UserProjectBean {
            /**
             * id : 6
             * project_id : 16
             * project_cover : /uploads/Curriculum/2018/04/12/1523527992.png
             * project_name : 事业单位党性知识学习项目
             */

            private String id;
            private String project_id;
            private String project_cover;
            private String project_name;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getProject_id() {
                return project_id;
            }

            public void setProject_id(String project_id) {
                this.project_id = project_id;
            }

            public String getProject_cover() {
                return project_cover;
            }

            public void setProject_cover(String project_cover) {
                this.project_cover = project_cover;
            }

            public String getProject_name() {
                return project_name;
            }

            public void setProject_name(String project_name) {
                this.project_name = project_name;
            }
        }
    }
}
