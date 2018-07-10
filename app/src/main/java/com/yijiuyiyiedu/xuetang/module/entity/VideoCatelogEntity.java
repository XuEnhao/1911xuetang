package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/22.
 */

public class VideoCatelogEntity {

    /**
     * status : 1
     * msg : 成功
     * data : [{"id":"72","curriculum_id":"18","title":"1.导言-课程简介","parent_id":"68","look_at":"2","video_address":"","video_pic":"","video_id":"1cb865adfa7942aca3103d529cd2ed95","video_number":"1","video_time":"","voice_id":"","sort":"1","status":"1","create_time":"1523503384"},{"id":"73","curriculum_id":"18","title":"2.知识力等于安全度【","parent_id":"68","look_at":"2","video_address":"","video_pic":"","video_id":"20a883e85792421384dacfd9c1acb942","video_number":"2","video_time":"","voice_id":"","sort":"2","status":"1","create_time":"1523503408"},{"id":"71","curriculum_id":"18","title":"3.如何让知识融会贯通？【核心章节】","parent_id":"68","look_at":"1","video_address":"","video_pic":"","video_id":"4300cbb1c5ca4664a5e29641064e01da","video_number":"3","video_time":"","voice_id":"","sort":"11","status":"1","create_time":"1523503361"},{"id":"75","curriculum_id":"18","title":"4.课程学习导图","parent_id":"69","look_at":"1","video_address":"","video_pic":"","video_id":"09fa22a566ae4b69853e1a8d953ff9a6","video_number":"4","video_time":"","voice_id":"","sort":"4","status":"1","create_time":"1523503465"},{"id":"76","curriculum_id":"18","title":"5.\u201c知识体系\u201d长什么样","parent_id":"69","look_at":"1","video_address":"","video_pic":"","video_id":"dd4e1f1df6d542759ff7bcf953c7786d","video_number":"5","video_time":"","voice_id":"","sort":"5","status":"1","create_time":"1523503483"},{"id":"77","curriculum_id":"18","title":"6.知识管理四象限","parent_id":"70","look_at":"1","video_address":"","video_pic":"","video_id":"30355ac726d84753a7f9bcb148bc3655","video_number":"6","video_time":"","voice_id":"","sort":"6","status":"1","create_time":"1523503506"},{"id":"78","curriculum_id":"18","title":"7.知识管理四象限","parent_id":"70","look_at":"1","video_address":"","video_pic":"","video_id":"4931e9913d3346508f2c5903eeed6d62","video_number":"7","video_time":"","voice_id":"","sort":"7","status":"1","create_time":"1523503506"}]
     */

    private String status;
    private String msg;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 72
         * curriculum_id : 18
         * title : 1.导言-课程简介
         * parent_id : 68
         * look_at : 2
         * video_address :
         * video_pic :
         * video_id : 1cb865adfa7942aca3103d529cd2ed95
         * video_number : 1
         * video_time :
         * voice_id :
         * sort : 1
         * status : 1
         * create_time : 1523503384
         */

        private String id;
        private String curriculum_id;
        private String title;
        private String parent_id;
        private String look_at;
        private String video_address;
        private String video_pic;
        private String video_id;
        private String video_number;
        private String video_time;
        private String voice_id;
        private String sort;
        private String status;
        private String create_time;
        private String color;

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

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

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getParent_id() {
            return parent_id;
        }

        public void setParent_id(String parent_id) {
            this.parent_id = parent_id;
        }

        public String getLook_at() {
            return look_at;
        }

        public void setLook_at(String look_at) {
            this.look_at = look_at;
        }

        public String getVideo_address() {
            return video_address;
        }

        public void setVideo_address(String video_address) {
            this.video_address = video_address;
        }

        public String getVideo_pic() {
            return video_pic;
        }

        public void setVideo_pic(String video_pic) {
            this.video_pic = video_pic;
        }

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getVideo_number() {
            return video_number;
        }

        public void setVideo_number(String video_number) {
            this.video_number = video_number;
        }

        public String getVideo_time() {
            return video_time;
        }

        public void setVideo_time(String video_time) {
            this.video_time = video_time;
        }

        public String getVoice_id() {
            return voice_id;
        }

        public void setVoice_id(String voice_id) {
            this.voice_id = voice_id;
        }

        public String getSort() {
            return sort;
        }

        public void setSort(String sort) {
            this.sort = sort;
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
