package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/4/8.
 * 添加评价
 */

public class AddEvaluateEntity {


    /**
     * status : 1
     * msg : u6210u529f
     * data : {"id":"25","curriculum_id":"4","user_id":"1","evaluate_content":"dsfdsf","score":"5","status":"1","create_time":"2018-04-08","nick_name":"13661327734","head_img":"/public/image/touxiang.png"}
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
         * id : 25
         * curriculum_id : 4
         * user_id : 1
         * evaluate_content : dsfdsf
         * score : 5
         * status : 1
         * create_time : 2018-04-08
         * nick_name : 13661327734
         * head_img : /public/image/touxiang.png
         */

        private String id;
        private String curriculum_id;
        private String user_id;
        private String evaluate_content;
        private String score;
        private String status;
        private String create_time;
        private String nick_name;
        private String head_img;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getCurriculum_id() {
            return curriculum_id;
        }

        public void setCurriculum_id(String curriculum_id) {
            this.curriculum_id = curriculum_id;
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
    }
}
