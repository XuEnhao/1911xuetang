package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/3/21.
 */

public class MineEntity {


    /**
     * status : 0
     * msg :
     * data : {"user":{"user_id":"52","nick_name":"18701519716","user_name":"18701519711","head_img":"/public/image/touxiang.png","province":"10","city":"1000","area":"1001","position":"1","company_id":"5","email":"","sex":"1","province_name":"北京市","city_name":"北京市","area_name":"东城区","position_name":"程序猿鼓励师","company_name":"清华大学"}}
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
        /**
         * user : {"user_id":"52","nick_name":"18701519716","user_name":"18701519711","head_img":"/public/image/touxiang.png","province":"10","city":"1000","area":"1001","position":"1","company_id":"5","email":"","sex":"1","province_name":"北京市","city_name":"北京市","area_name":"东城区","position_name":"程序猿鼓励师","company_name":"清华大学"}
         */

        private UserBean user;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public static class UserBean {
            /**
             * user_id : 52
             * nick_name : 18701519716
             * user_name : 18701519711
             * head_img : /public/image/touxiang.png
             * province : 10
             * city : 1000
             * area : 1001
             * position : 1
             * company_id : 5
             * email :
             * sex : 1
             * province_name : 北京市
             * city_name : 北京市
             * area_name : 东城区
             * position_name : 程序猿鼓励师
             * company_name : 清华大学
             */

            private String user_id;
            private String nick_name;
            private String user_name;
            private String head_img;
            private String province;
            private String city;
            private String area;
            private String position;
            private String company_id;
            private String email;
            private String sex;
            private String province_name;
            private String city_name;
            private String area_name;
            private String position_name;
            private String company_name;
            private String birthday;
            private String study_curriculum_time;

            public String getStudy_curriculum_time() {
                return study_curriculum_time;
            }

            public void setStudy_curriculum_time(String study_curriculum_time) {
                this.study_curriculum_time = study_curriculum_time;
            }

            public String getBirthday() {
                return birthday;
            }

            public void setBirthday(String birthday) {
                this.birthday = birthday;
            }

            public String getUser_id() {
                return user_id;
            }

            public void setUser_id(String user_id) {
                this.user_id = user_id;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
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

            public String getProvince() {
                return province;
            }

            public void setProvince(String province) {
                this.province = province;
            }

            public String getCity() {
                return city;
            }

            public void setCity(String city) {
                this.city = city;
            }

            public String getArea() {
                return area;
            }

            public void setArea(String area) {
                this.area = area;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }

            public String getCompany_id() {
                return company_id;
            }

            public void setCompany_id(String company_id) {
                this.company_id = company_id;
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

            public String getProvince_name() {
                return province_name;
            }

            public void setProvince_name(String province_name) {
                this.province_name = province_name;
            }

            public String getCity_name() {
                return city_name;
            }

            public void setCity_name(String city_name) {
                this.city_name = city_name;
            }

            public String getArea_name() {
                return area_name;
            }

            public void setArea_name(String area_name) {
                this.area_name = area_name;
            }

            public String getPosition_name() {
                return position_name;
            }

            public void setPosition_name(String position_name) {
                this.position_name = position_name;
            }

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }
        }
    }
}
