package com.yijiuyiyiedu.xuetang.module.entity;

/**
 * Created by xuenhao on 2018/3/22.
 */

public class CourseDetailsEntity {


    /**
     * status : 0
     * msg : 成功
     * data : {"curriculumDetail":{"id":"18","title":"PHP进阶篇","teacher_id":"6","introduction":"通过PHP学习的进阶篇的学习，你可以对PHP的理论知识由浅入深有更深一步的掌握，这些知识能够使您更加全面的掌握PHP，从而助您在实际工作中使用PHP快速开发网站程序。","curriculum_number":"x_18","study_number":"7","curriculum_time":"200","score":3,"picture":"http://p8p47jzeo.bkt.clouddn.com/1529993287","study_picture":"http://p8p47jzeo.bkt.clouddn.com/1528652872","tag":"体育,文化","present_price":"0.01","teacher_name":"闫宁","head_img":"http://p8p47jzeo.bkt.clouddn.com/1529758647","graduate":"清华大学教授","teacher_content":"源自清华、面向世界，以传播清华文化、服务创新创业、推动健康品质生活为己任，旗下布局四大业务板块：1911创客空间、1911资本、1911商城及1911教育","is_study":1,"is_evaluate":0,"is_cart":0,"percent":"0.04","study_curriculum_time":0,"is_collection":0,"defaultCurriculumCatalog":{"id":"87","curriculum_id":"18","title":"小专家","look_at":"2","free_time":"60","video_number":"1","video_id":"7447398156866732264","video_address":"http://1256678727.vod2.myqcloud.com/19d7e632vodgzp1256678727/fee3ec177447398156866732264/AGqtjMQnJrQA.mp4","video_time":"183","size":"36318055"}},"curriculumPrivilege":true}
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
         * curriculumDetail : {"id":"18","title":"PHP进阶篇","teacher_id":"6","introduction":"通过PHP学习的进阶篇的学习，你可以对PHP的理论知识由浅入深有更深一步的掌握，这些知识能够使您更加全面的掌握PHP，从而助您在实际工作中使用PHP快速开发网站程序。","curriculum_number":"x_18","study_number":"7","curriculum_time":"200","score":3,"picture":"http://p8p47jzeo.bkt.clouddn.com/1529993287","study_picture":"http://p8p47jzeo.bkt.clouddn.com/1528652872","tag":"体育,文化","present_price":"0.01","teacher_name":"闫宁","head_img":"http://p8p47jzeo.bkt.clouddn.com/1529758647","graduate":"清华大学教授","teacher_content":"源自清华、面向世界，以传播清华文化、服务创新创业、推动健康品质生活为己任，旗下布局四大业务板块：1911创客空间、1911资本、1911商城及1911教育","is_study":1,"is_evaluate":0,"is_cart":0,"percent":"0.04","study_curriculum_time":0,"is_collection":0,"defaultCurriculumCatalog":{"id":"87","curriculum_id":"18","title":"小专家","look_at":"2","free_time":"60","video_number":"1","video_id":"7447398156866732264","video_address":"http://1256678727.vod2.myqcloud.com/19d7e632vodgzp1256678727/fee3ec177447398156866732264/AGqtjMQnJrQA.mp4","video_time":"183","size":"36318055"}}
         * curriculumPrivilege : true
         */

        private CurriculumDetailBean curriculumDetail;
        private boolean curriculumPrivilege;

        public CurriculumDetailBean getCurriculumDetail() {
            return curriculumDetail;
        }

        public void setCurriculumDetail(CurriculumDetailBean curriculumDetail) {
            this.curriculumDetail = curriculumDetail;
        }

        public boolean isCurriculumPrivilege() {
            return curriculumPrivilege;
        }

        public void setCurriculumPrivilege(boolean curriculumPrivilege) {
            this.curriculumPrivilege = curriculumPrivilege;
        }

        public static class CurriculumDetailBean {
            /**
             * id : 18
             * title : PHP进阶篇
             * teacher_id : 6
             * introduction : 通过PHP学习的进阶篇的学习，你可以对PHP的理论知识由浅入深有更深一步的掌握，这些知识能够使您更加全面的掌握PHP，从而助您在实际工作中使用PHP快速开发网站程序。
             * curriculum_number : x_18
             * study_number : 7
             * curriculum_time : 200
             * score : 3
             * picture : http://p8p47jzeo.bkt.clouddn.com/1529993287
             * study_picture : http://p8p47jzeo.bkt.clouddn.com/1528652872
             * tag : 体育,文化
             * present_price : 0.01
             * teacher_name : 闫宁
             * head_img : http://p8p47jzeo.bkt.clouddn.com/1529758647
             * graduate : 清华大学教授
             * teacher_content : 源自清华、面向世界，以传播清华文化、服务创新创业、推动健康品质生活为己任，旗下布局四大业务板块：1911创客空间、1911资本、1911商城及1911教育
             * is_study : 1
             * is_evaluate : 0
             * is_cart : 0
             * percent : 0.04
             * study_curriculum_time : 0
             * is_collection : 0
             * defaultCurriculumCatalog : {"id":"87","curriculum_id":"18","title":"小专家","look_at":"2","free_time":"60","video_number":"1","video_id":"7447398156866732264","video_address":"http://1256678727.vod2.myqcloud.com/19d7e632vodgzp1256678727/fee3ec177447398156866732264/AGqtjMQnJrQA.mp4","video_time":"183","size":"36318055"}
             */

            private String id;
            private String title;
            private String teacher_id;
            private String introduction;
            private String curriculum_number;
            private String study_number;
            private String curriculum_time;
            private int score;
            private String picture;
            private String study_picture;
            private String tag;
            private String present_price;
            private String teacher_name;
            private String head_img;
            private String graduate;
            private String teacher_content;
            private int is_study;
            private int is_evaluate;
            private int is_cart;
            private String percent;
            private int study_curriculum_time;
            private int is_collection;
            private DefaultCurriculumCatalogBean defaultCurriculumCatalog;

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

            public String getTeacher_id() {
                return teacher_id;
            }

            public void setTeacher_id(String teacher_id) {
                this.teacher_id = teacher_id;
            }

            public String getIntroduction() {
                return introduction;
            }

            public void setIntroduction(String introduction) {
                this.introduction = introduction;
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

            public String getCurriculum_time() {
                return curriculum_time;
            }

            public void setCurriculum_time(String curriculum_time) {
                this.curriculum_time = curriculum_time;
            }

            public int getScore() {
                return score;
            }

            public void setScore(int score) {
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

            public String getTag() {
                return tag;
            }

            public void setTag(String tag) {
                this.tag = tag;
            }

            public String getPresent_price() {
                return present_price;
            }

            public void setPresent_price(String present_price) {
                this.present_price = present_price;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

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

            public String getTeacher_content() {
                return teacher_content;
            }

            public void setTeacher_content(String teacher_content) {
                this.teacher_content = teacher_content;
            }

            public int getIs_study() {
                return is_study;
            }

            public void setIs_study(int is_study) {
                this.is_study = is_study;
            }

            public int getIs_evaluate() {
                return is_evaluate;
            }

            public void setIs_evaluate(int is_evaluate) {
                this.is_evaluate = is_evaluate;
            }

            public int getIs_cart() {
                return is_cart;
            }

            public void setIs_cart(int is_cart) {
                this.is_cart = is_cart;
            }

            public String getPercent() {
                return percent;
            }

            public void setPercent(String percent) {
                this.percent = percent;
            }

            public int getStudy_curriculum_time() {
                return study_curriculum_time;
            }

            public void setStudy_curriculum_time(int study_curriculum_time) {
                this.study_curriculum_time = study_curriculum_time;
            }

            public int getIs_collection() {
                return is_collection;
            }

            public void setIs_collection(int is_collection) {
                this.is_collection = is_collection;
            }

            public DefaultCurriculumCatalogBean getDefaultCurriculumCatalog() {
                return defaultCurriculumCatalog;
            }

            public void setDefaultCurriculumCatalog(DefaultCurriculumCatalogBean defaultCurriculumCatalog) {
                this.defaultCurriculumCatalog = defaultCurriculumCatalog;
            }

            public static class DefaultCurriculumCatalogBean {
                /**
                 * id : 87
                 * curriculum_id : 18
                 * title : 小专家
                 * look_at : 2
                 * free_time : 60
                 * video_number : 1
                 * video_id : 7447398156866732264
                 * video_address : http://1256678727.vod2.myqcloud.com/19d7e632vodgzp1256678727/fee3ec177447398156866732264/AGqtjMQnJrQA.mp4
                 * video_time : 183
                 * size : 36318055
                 */

                private String id;
                private String curriculum_id;
                private String title;
                private String look_at;
                private String free_time;
                private String video_number;
                private String video_id;
                private String video_address;
                private String video_time;
                private String size;

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

                public String getLook_at() {
                    return look_at;
                }

                public void setLook_at(String look_at) {
                    this.look_at = look_at;
                }

                public String getFree_time() {
                    return free_time;
                }

                public void setFree_time(String free_time) {
                    this.free_time = free_time;
                }

                public String getVideo_number() {
                    return video_number;
                }

                public void setVideo_number(String video_number) {
                    this.video_number = video_number;
                }

                public String getVideo_id() {
                    return video_id;
                }

                public void setVideo_id(String video_id) {
                    this.video_id = video_id;
                }

                public String getVideo_address() {
                    return video_address;
                }

                public void setVideo_address(String video_address) {
                    this.video_address = video_address;
                }

                public String getVideo_time() {
                    return video_time;
                }

                public void setVideo_time(String video_time) {
                    this.video_time = video_time;
                }

                public String getSize() {
                    return size;
                }

                public void setSize(String size) {
                    this.size = size;
                }
            }
        }
    }
}
