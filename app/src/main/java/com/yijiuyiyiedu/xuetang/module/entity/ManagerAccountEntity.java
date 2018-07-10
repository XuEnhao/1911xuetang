package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/3/22.
 */

public class ManagerAccountEntity {


    /**
     * status : 1
     * msg : ok
     * data : {"userInfo":{"id":"1","user_name":"13661327734","head_img":"/uploads/had/2018/03/15/1521080253.jpg","openid":"","password":"fcea920f7412b5da7be0cf42b8c93759","nick_name":"u6708u9ed1u98ceu9ad8","real_name":"u9ad8u5c71","staff_position":"1","sex":"1","user_type":"2","enterprise_id":"0","child_account":"","status":"1","create_time":"1520998348"},"enterpriseInfo":{"id":"1","user_id":"1","company_name":"u6d4bu8bd511","company_telephone":"01022334455","company_address":"u660cu5e73u65f6u5c81u7684u4ed8u6b3eu8fdbu5ea6u987au5229u5f00u53d1u53ca","contact_name":"u5f20u4e09","company_phone":"13661327734","number_employees":"30","company_logo":"/uploads/logo/2018/03/14/1521012523.jpg","business_license":"/uploads/license/2018/03/14/1521012525.jpg","status":"1","create_time":"1521012529"},"childUser":[{"id":"5","user_name":"13881328834","head_img":"/public/image/touxiang.png","openid":"","password":"123456","nick_name":"","real_name":"u674eu56db","staff_position":"HE","sex":"1","user_type":"0","enterprise_id":"1","child_account":"","status":"1","create_time":"1521110304"},{"id":"4","user_name":"13771327734","head_img":"/public/image/touxiang.png","openid":"","password":"123456","nick_name":"","real_name":"u5f20u4e09","staff_position":"u6d4bu8bd5u673a","sex":"1","user_type":"0","enterprise_id":"1","child_account":"","status":"1","create_time":"1521107944"},{"id":"3","user_name":"17611101091","head_img":"/uploads/had/2018/03/15/1521083054.png","openid":"","password":"045b0170b2c88757e7083632eb90a376","nick_name":"u5927u5e05","real_name":"u5f20u5e05","staff_position":"1","sex":"1","user_type":"1","enterprise_id":"1","child_account":"","status":"1","create_time":"1521080378"}]}
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
         * userInfo : {"id":"1","user_name":"13661327734","head_img":"/uploads/had/2018/03/15/1521080253.jpg","openid":"","password":"fcea920f7412b5da7be0cf42b8c93759","nick_name":"u6708u9ed1u98ceu9ad8","real_name":"u9ad8u5c71","staff_position":"1","sex":"1","user_type":"2","enterprise_id":"0","child_account":"","status":"1","create_time":"1520998348"}
         * enterpriseInfo : {"id":"1","user_id":"1","company_name":"u6d4bu8bd511","company_telephone":"01022334455","company_address":"u660cu5e73u65f6u5c81u7684u4ed8u6b3eu8fdbu5ea6u987au5229u5f00u53d1u53ca","contact_name":"u5f20u4e09","company_phone":"13661327734","number_employees":"30","company_logo":"/uploads/logo/2018/03/14/1521012523.jpg","business_license":"/uploads/license/2018/03/14/1521012525.jpg","status":"1","create_time":"1521012529"}
         * childUser : [{"id":"5","user_name":"13881328834","head_img":"/public/image/touxiang.png","openid":"","password":"123456","nick_name":"","real_name":"u674eu56db","staff_position":"HE","sex":"1","user_type":"0","enterprise_id":"1","child_account":"","status":"1","create_time":"1521110304"},{"id":"4","user_name":"13771327734","head_img":"/public/image/touxiang.png","openid":"","password":"123456","nick_name":"","real_name":"u5f20u4e09","staff_position":"u6d4bu8bd5u673a","sex":"1","user_type":"0","enterprise_id":"1","child_account":"","status":"1","create_time":"1521107944"},{"id":"3","user_name":"17611101091","head_img":"/uploads/had/2018/03/15/1521083054.png","openid":"","password":"045b0170b2c88757e7083632eb90a376","nick_name":"u5927u5e05","real_name":"u5f20u5e05","staff_position":"1","sex":"1","user_type":"1","enterprise_id":"1","child_account":"","status":"1","create_time":"1521080378"}]
         */

        private UserInfoBean userInfo;
        private EnterpriseInfoBean enterpriseInfo;
        private List<ChildUserBean> childUser;

        public UserInfoBean getUserInfo() {
            return userInfo;
        }

        public void setUserInfo(UserInfoBean userInfo) {
            this.userInfo = userInfo;
        }

        public EnterpriseInfoBean getEnterpriseInfo() {
            return enterpriseInfo;
        }

        public void setEnterpriseInfo(EnterpriseInfoBean enterpriseInfo) {
            this.enterpriseInfo = enterpriseInfo;
        }

        public List<ChildUserBean> getChildUser() {
            return childUser;
        }

        public void setChildUser(List<ChildUserBean> childUser) {
            this.childUser = childUser;
        }

        public static class UserInfoBean {
            /**
             * id : 1
             * user_name : 13661327734
             * head_img : /uploads/had/2018/03/15/1521080253.jpg
             * openid :
             * password : fcea920f7412b5da7be0cf42b8c93759
             * nick_name : u6708u9ed1u98ceu9ad8
             * real_name : u9ad8u5c71
             * staff_position : 1
             * sex : 1
             * user_type : 2
             * enterprise_id : 0
             * child_account :
             * status : 1
             * create_time : 1520998348
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
            private String user_type;
            private String enterprise_id;
            private String child_account;
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

        public static class EnterpriseInfoBean {
            /**
             * id : 1
             * user_id : 1
             * company_name : u6d4bu8bd511
             * company_telephone : 01022334455
             * company_address : u660cu5e73u65f6u5c81u7684u4ed8u6b3eu8fdbu5ea6u987au5229u5f00u53d1u53ca
             * contact_name : u5f20u4e09
             * company_phone : 13661327734
             * number_employees : 30
             * company_logo : /uploads/logo/2018/03/14/1521012523.jpg
             * business_license : /uploads/license/2018/03/14/1521012525.jpg
             * status : 1
             * create_time : 1521012529
             */

            private String id;
            private String user_id;
            private String company_name;
            private String company_telephone;
            private String company_address;
            private String contact_name;
            private String company_phone;
            private String number_employees;
            private String company_logo;
            private String business_license;
            private String status;
            private String create_time;

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

            public String getCompany_name() {
                return company_name;
            }

            public void setCompany_name(String company_name) {
                this.company_name = company_name;
            }

            public String getCompany_telephone() {
                return company_telephone;
            }

            public void setCompany_telephone(String company_telephone) {
                this.company_telephone = company_telephone;
            }

            public String getCompany_address() {
                return company_address;
            }

            public void setCompany_address(String company_address) {
                this.company_address = company_address;
            }

            public String getContact_name() {
                return contact_name;
            }

            public void setContact_name(String contact_name) {
                this.contact_name = contact_name;
            }

            public String getCompany_phone() {
                return company_phone;
            }

            public void setCompany_phone(String company_phone) {
                this.company_phone = company_phone;
            }

            public String getNumber_employees() {
                return number_employees;
            }

            public void setNumber_employees(String number_employees) {
                this.number_employees = number_employees;
            }

            public String getCompany_logo() {
                return company_logo;
            }

            public void setCompany_logo(String company_logo) {
                this.company_logo = company_logo;
            }

            public String getBusiness_license() {
                return business_license;
            }

            public void setBusiness_license(String business_license) {
                this.business_license = business_license;
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

        public static class ChildUserBean {
            /**
             * id : 5
             * user_name : 13881328834
             * head_img : /public/image/touxiang.png
             * openid :
             * password : 123456
             * nick_name :
             * real_name : u674eu56db
             * staff_position : HE
             * sex : 1
             * user_type : 0
             * enterprise_id : 1
             * child_account :
             * status : 1
             * create_time : 1521110304
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
            private String user_type;
            private String enterprise_id;
            private String child_account;
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
