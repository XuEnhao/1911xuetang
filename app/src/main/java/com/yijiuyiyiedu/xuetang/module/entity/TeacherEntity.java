package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/11.
 */

public class TeacherEntity {

    /**
     * status : 0
     * msg : 成功
     * data : {"teacherList":[{"id":"1","teacher_name":"1911教师","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956734","content":"一九一一集团有限公司"}]}
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
        private List<TeacherListBean> teacherList;

        public List<TeacherListBean> getTeacherList() {
            return teacherList;
        }

        public void setTeacherList(List<TeacherListBean> teacherList) {
            this.teacherList = teacherList;
        }

        public static class TeacherListBean {
            /**
             * id : 1
             * teacher_name : 1911教师
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526956734
             * content : 一九一一集团有限公司
             */

            private String id;
            private String teacher_name;
            private String picture;
            private String content;

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
        }
    }
}
