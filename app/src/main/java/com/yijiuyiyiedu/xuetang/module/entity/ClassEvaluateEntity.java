package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/9.
 */

public class ClassEvaluateEntity {

    /**
     * status : 1
     * msg : ok
     * data : {"evaluateList":[{"id":"5","user_id":"65","evaluate_content":"xxxxx","score":"4","create_time":"2018-06-07","nick_name":"刘道宁","head_img":"http://p8p47jzeo.bkt.clouddn.com/1527596152","position":""},{"id":"2","user_id":"46","evaluate_content":"互联网是一个快速迭代的行业，技术知识和产品创新瞬息万变，产品经理更要紧跟时代潮流。在开课吧学习后，我拥有了领先多数同龄人的技能，也改变了自己的生活和对待生活的态度","score":"4","create_time":"1970-01-01","nick_name":"微尘","head_img":"http://p8p47jzeo.bkt.clouddn.com/1528271318","position":"php开发"}]}
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
        private List<EvaluateListBean> evaluateList;

        public List<EvaluateListBean> getEvaluateList() {
            return evaluateList;
        }

        public void setEvaluateList(List<EvaluateListBean> evaluateList) {
            this.evaluateList = evaluateList;
        }

        public static class EvaluateListBean {
            /**
             * id : 5
             * user_id : 65
             * evaluate_content : xxxxx
             * score : 4
             * create_time : 2018-06-07
             * nick_name : 刘道宁
             * head_img : http://p8p47jzeo.bkt.clouddn.com/1527596152
             * position :
             */

            private String id;
            private String user_id;
            private String evaluate_content;
            private String score;
            private String create_time;
            private String nick_name;
            private String head_img;
            private String position;

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

            public String getEvaluate_content() {
                return evaluate_content;
            }

            public void setEvaluate_content(String evaluate_content) {
                this.evaluate_content = evaluate_content;
            }

            public String getScore() {
                return score;
            }

            public void setScore(String score) {
                this.score = score;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }

            public String getNick_name() {
                return nick_name;
            }

            public void setNick_name(String nick_name) {
                this.nick_name = nick_name;
            }

            public String getHead_img() {
                return head_img;
            }

            public void setHead_img(String head_img) {
                this.head_img = head_img;
            }

            public String getPosition() {
                return position;
            }

            public void setPosition(String position) {
                this.position = position;
            }
        }
    }
}
