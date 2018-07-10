package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/23.
 */

public class TeacherDetailsEntity {

    /**
     * status : 0
     * msg : 成功
     * data : {"id":"5","teacher_name":"莎良朋","picture":"http://p8p47jzeo.bkt.clouddn.com/1528643990","content":"源自清华、面向世界，以传播清华文化、服务创新创业、推动健康品质生活为己任，旗下布局四大业务板块：1911创客空间、1911资本、1911商城及1911教育。","curriculumList":[{"id":"16","title":"IT互联网的发展","present_price":"100.00","study_number":"56","picture":"http://p8p47jzeo.bkt.clouddn.com/1528624840"},{"id":"10","title":"中国银行业的改革与创新实践","present_price":"20.00","study_number":"56","picture":"http://p8p47jzeo.bkt.clouddn.com/1528624919"},{"id":"6","title":"现代公司治理与国企改革","present_price":"99.00","study_number":"100","picture":"http://p8p47jzeo.bkt.clouddn.com/1528624955"},{"id":"2","title":" Frank 365天陪伴式英语私教课","present_price":"0.01","study_number":"0","picture":"http://p8p47jzeo.bkt.clouddn.com/1528448773"}]}
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
         * id : 5
         * teacher_name : 莎良朋
         * picture : http://p8p47jzeo.bkt.clouddn.com/1528643990
         * content : 源自清华、面向世界，以传播清华文化、服务创新创业、推动健康品质生活为己任，旗下布局四大业务板块：1911创客空间、1911资本、1911商城及1911教育。
         * curriculumList : [{"id":"16","title":"IT互联网的发展","present_price":"100.00","study_number":"56","picture":"http://p8p47jzeo.bkt.clouddn.com/1528624840"},{"id":"10","title":"中国银行业的改革与创新实践","present_price":"20.00","study_number":"56","picture":"http://p8p47jzeo.bkt.clouddn.com/1528624919"},{"id":"6","title":"现代公司治理与国企改革","present_price":"99.00","study_number":"100","picture":"http://p8p47jzeo.bkt.clouddn.com/1528624955"},{"id":"2","title":" Frank 365天陪伴式英语私教课","present_price":"0.01","study_number":"0","picture":"http://p8p47jzeo.bkt.clouddn.com/1528448773"}]
         */

        private String id;
        private String teacher_name;
        private String picture;
        private String head_img;
        private String content;
        private String study_number;
        private String graduate;
        private List<CurriculumListBean> curriculumList;

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

        public String getStudy_number() {
            return study_number;
        }

        public void setStudy_number(String study_number) {
            this.study_number = study_number;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public List<CurriculumListBean> getCurriculumList() {
            return curriculumList;
        }

        public void setCurriculumList(List<CurriculumListBean> curriculumList) {
            this.curriculumList = curriculumList;
        }

        public static class CurriculumListBean {
            /**
             * id : 16
             * title : IT互联网的发展
             * present_price : 100.00
             * study_number : 56
             * picture : http://p8p47jzeo.bkt.clouddn.com/1528624840
             */

            private String id;
            private String title;
            private String present_price;
            private String study_number;
            private String picture;

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }
    }
}
