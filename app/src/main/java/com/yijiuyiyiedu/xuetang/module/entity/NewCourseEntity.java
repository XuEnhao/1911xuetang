package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class NewCourseEntity {

    /**
     * status : 0
     * msg : 成功
     * data : {"curriculumList":[{"id":"1","title":"数据挖掘：理论与算法","present_price":"0.1","study_number":"0","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956734"}]}
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
        private List<CurriculumListBean> curriculumList;

        public List<CurriculumListBean> getCurriculumList() {
            return curriculumList;
        }

        public void setCurriculumList(List<CurriculumListBean> curriculumList) {
            this.curriculumList = curriculumList;
        }

        public static class CurriculumListBean {
            /**
             * id : 1
             * title : 数据挖掘：理论与算法
             * present_price : 0.1
             * study_number : 0
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526956734
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
