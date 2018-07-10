package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/3/28.
 */

public class BuyCourseEntity {


    /**
     * status : 1
     * msg : ok
     * data : {"info":{"id":"69","user_name":"17600273459","head_img":"/public/image/touxiang.png","openid":"","password":"e10adc3949ba59abbe56e057f20f883e","nick_name":"17600273459","real_name":"","birthday":"","email":"","staff_position":"","sex":"1","user_type":"1","enterprise_id":"0","child_account":"","login_auth":"tkGWbNw6M5djXIpnHCaYFrelvKZJDQR3","status":"1","create_time":"1523348007"},"enterpriseInfo":{},"curridata":{"id":"16","category_id":"142","title":"u548cu79cbu53f6u4e00u8d77u5b66PPT","lecturer":"u79cbu53f6","status_serial":"1","curriculum_number":"qy77876562","study_number":"13","class_number":"200","curriculum_time":"50","original_price":"199.00","present_price":"0.01","score":"0.0","picture":"/uploads/Curriculum/2018/04/12/1523501175.jpg","study_picture":"/uploads/Curriculum/2018/04/12/1523501178.jpg","is_recommend":"1","status":"1","create_time":"1523501184"},"cont":0,"total":0,"id":"16","picre":"0.01"}
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
         * info : {"id":"69","user_name":"17600273459","head_img":"/public/image/touxiang.png","openid":"","password":"e10adc3949ba59abbe56e057f20f883e","nick_name":"17600273459","real_name":"","birthday":"","email":"","staff_position":"","sex":"1","user_type":"1","enterprise_id":"0","child_account":"","login_auth":"tkGWbNw6M5djXIpnHCaYFrelvKZJDQR3","status":"1","create_time":"1523348007"}
         * enterpriseInfo : {}
         * curridata : {"id":"16","category_id":"142","title":"u548cu79cbu53f6u4e00u8d77u5b66PPT","lecturer":"u79cbu53f6","status_serial":"1","curriculum_number":"qy77876562","study_number":"13","class_number":"200","curriculum_time":"50","original_price":"199.00","present_price":"0.01","score":"0.0","picture":"/uploads/Curriculum/2018/04/12/1523501175.jpg","study_picture":"/uploads/Curriculum/2018/04/12/1523501178.jpg","is_recommend":"1","status":"1","create_time":"1523501184"}
         * cont : 0
         * total : 0
         * id : 16
         * picre : 0.01
         */

        private InfoBean info;
        private EnterpriseInfoBean enterpriseInfo;
        private CurridataBean curridata;
        private int cont;
        private int total;
        private String id;
        private String picre;

        public InfoBean getInfo() {
            return info;
        }

        public void setInfo(InfoBean info) {
            this.info = info;
        }

        public EnterpriseInfoBean getEnterpriseInfo() {
            return enterpriseInfo;
        }

        public void setEnterpriseInfo(EnterpriseInfoBean enterpriseInfo) {
            this.enterpriseInfo = enterpriseInfo;
        }

        public CurridataBean getCurridata() {
            return curridata;
        }

        public void setCurridata(CurridataBean curridata) {
            this.curridata = curridata;
        }

        public int getCont() {
            return cont;
        }

        public void setCont(int cont) {
            this.cont = cont;
        }

        public int getTotal() {
            return total;
        }

        public void setTotal(int total) {
            this.total = total;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPicre() {
            return picre;
        }

        public void setPicre(String picre) {
            this.picre = picre;
        }

        public static class InfoBean {
            /**
             * id : 69
             * user_name : 17600273459
             * head_img : /public/image/touxiang.png
             * openid :
             * password : e10adc3949ba59abbe56e057f20f883e
             * nick_name : 17600273459
             * real_name :
             * birthday :
             * email :
             * staff_position :
             * sex : 1
             * user_type : 1
             * enterprise_id : 0
             * child_account :
             * login_auth : tkGWbNw6M5djXIpnHCaYFrelvKZJDQR3
             * status : 1
             * create_time : 1523348007
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
            private String staff_position;
            private String sex;
            private String user_type;
            private String enterprise_id;
            private String child_account;
            private String login_auth;
            private String status;
            private String create_time;

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

            public String getUser_type() {
                return user_type;
            }

            public void setUser_type(String user_type) {
                this.user_type = user_type;
            }

            public String getEnterprise_id() {
                return enterprise_id;
            }

            public void setEnterprise_id(String enterprise_id) {
                this.enterprise_id = enterprise_id;
            }

            public String getChild_account() {
                return child_account;
            }

            public void setChild_account(String child_account) {
                this.child_account = child_account;
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

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public static class EnterpriseInfoBean {
        }

        public static class CurridataBean {
            /**
             * id : 16
             * category_id : 142
             * title : u548cu79cbu53f6u4e00u8d77u5b66PPT
             * lecturer : u79cbu53f6
             * status_serial : 1
             * curriculum_number : qy77876562
             * study_number : 13
             * class_number : 200
             * curriculum_time : 50
             * original_price : 199.00
             * present_price : 0.01
             * score : 0.0
             * picture : /uploads/Curriculum/2018/04/12/1523501175.jpg
             * study_picture : /uploads/Curriculum/2018/04/12/1523501178.jpg
             * is_recommend : 1
             * status : 1
             * create_time : 1523501184
             */

            private String id;
            private String category_id;
            private String title;
            private String lecturer;
            private String status_serial;
            private String curriculum_number;
            private String study_number;
            private String class_number;
            private String curriculum_time;
            private String original_price;
            private String present_price;
            private String score;
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
