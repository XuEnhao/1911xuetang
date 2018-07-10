package com.yijiuyiyiedu.xuetang.module.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by ${xeh} on 2017/4/21 0021.
 */

public class CourseEntity implements Serializable {


    /**
     * status : 0
     * msg : 成功
     * data : {"bannerList":[{"id":"13","title":"测试","picture":"http://p8p47jzeo.bkt.clouddn.com/1526346455","jump_url":"http://www.1911edu.com/Web/Curriculum/curriculumDetail?id=16","curriculum_id":"17","color":""}],"newsCurriculumList":[{"id":"4","title":"日本式人际沟通术【日语中字】","present_price":"0.01","study_number":"50","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956685"}],"recommendCurriculumList":[{"id":"3","title":"策略性商务谈判中的谋略运用","present_price":"0.01","study_number":"0","picture":"http://p8p47jzeo.bkt.clouddn.com/1526956711"}],"teacherList":[{"id":"6","teacher_name":"御老师","picture":"http://p8p47jzeo.bkt.clouddn.com/1527647643","content":"源自清华、面向世界，以传播清华文化、"}],"collaborationEnterpriseList":[{"id":"2","enterprise_name":"合作企业二","logo":"http://p8p47jzeo.bkt.clouddn.com/1526956711"}],"newsList":[{"id":"38","title":"1911广场旗下1911咖啡获香港文利国际餐饮300万天使轮投资","picture":"http://p8p47jzeo.bkt.clouddn.com/1526957017","introduce":"1911广场旗下1911咖啡获香港文利国际餐饮300万天使轮投资","create_time":"1522632996"}],"otherNewsList":[{"id":"43","title":"1911未来教育上线了","picture":"http://p8p47jzeo.bkt.clouddn.com/1526369010","introduce":"1911集团1911集团1911集团1911集团","create_time":"1526369019"}]}
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
        private List<BannerListBean> bannerList;
        private List<NewsCurriculumListBean> newsCurriculumList;
        private List<RecommendCurriculumListBean> recommendCurriculumList;
        private List<TeacherListBean> teacherList;
        private List<CollaborationEnterpriseListBean> collaborationEnterpriseList;
        private List<NewsListBean> newsList;
        private List<OtherNewsListBean> otherNewsList;

        public List<BannerListBean> getBannerList() {
            return bannerList;
        }

        public void setBannerList(List<BannerListBean> bannerList) {
            this.bannerList = bannerList;
        }

        public List<NewsCurriculumListBean> getNewsCurriculumList() {
            return newsCurriculumList;
        }

        public void setNewsCurriculumList(List<NewsCurriculumListBean> newsCurriculumList) {
            this.newsCurriculumList = newsCurriculumList;
        }

        public List<RecommendCurriculumListBean> getRecommendCurriculumList() {
            return recommendCurriculumList;
        }

        public void setRecommendCurriculumList(List<RecommendCurriculumListBean> recommendCurriculumList) {
            this.recommendCurriculumList = recommendCurriculumList;
        }

        public List<TeacherListBean> getTeacherList() {
            return teacherList;
        }

        public void setTeacherList(List<TeacherListBean> teacherList) {
            this.teacherList = teacherList;
        }

        public List<CollaborationEnterpriseListBean> getCollaborationEnterpriseList() {
            return collaborationEnterpriseList;
        }

        public void setCollaborationEnterpriseList(List<CollaborationEnterpriseListBean> collaborationEnterpriseList) {
            this.collaborationEnterpriseList = collaborationEnterpriseList;
        }

        public List<NewsListBean> getNewsList() {
            return newsList;
        }

        public void setNewsList(List<NewsListBean> newsList) {
            this.newsList = newsList;
        }

        public List<OtherNewsListBean> getOtherNewsList() {
            return otherNewsList;
        }

        public void setOtherNewsList(List<OtherNewsListBean> otherNewsList) {
            this.otherNewsList = otherNewsList;
        }

        public static class BannerListBean {
            /**
             * id : 13
             * title : 测试
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526346455
             * jump_url : http://www.1911edu.com/Web/Curriculum/curriculumDetail?id=16
             * curriculum_id : 17
             * color :
             */

            private String id;
            private String title;
            private String picture;
            private String jump_url;
            private String curriculum_id;
            private String color;

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getJump_url() {
                return jump_url;
            }

            public void setJump_url(String jump_url) {
                this.jump_url = jump_url;
            }

            public String getCurriculum_id() {
                return curriculum_id;
            }

            public void setCurriculum_id(String curriculum_id) {
                this.curriculum_id = curriculum_id;
            }

            public String getColor() {
                return color;
            }

            public void setColor(String color) {
                this.color = color;
            }
        }

        public static class NewsCurriculumListBean {
            /**
             * id : 4
             * title : 日本式人际沟通术【日语中字】
             * present_price : 0.01
             * study_number : 50
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526956685
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

        public static class RecommendCurriculumListBean {
            /**
             * id : 3
             * title : 策略性商务谈判中的谋略运用
             * present_price : 0.01
             * study_number : 0
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526956711
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

        public static class TeacherListBean {
            /**
             * id : 6
             * teacher_name : 御老师
             * picture : http://p8p47jzeo.bkt.clouddn.com/1527647643
             * content : 源自清华、面向世界，以传播清华文化、
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

        public static class CollaborationEnterpriseListBean {
            public CollaborationEnterpriseListBean(String id, String enterprise_name, String logo) {
                this.id = id;
                this.enterprise_name = enterprise_name;
                this.logo = logo;
            }

            /**
             * id : 2
             * enterprise_name : 合作企业二
             * logo : http://p8p47jzeo.bkt.clouddn.com/1526956711
             */


            private String id;
            private String enterprise_name;
            private String logo;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getEnterprise_name() {
                return enterprise_name;
            }

            public void setEnterprise_name(String enterprise_name) {
                this.enterprise_name = enterprise_name;
            }

            public String getLogo() {
                return logo;
            }

            public void setLogo(String logo) {
                this.logo = logo;
            }
        }

        public static class NewsListBean {
            /**
             * id : 38
             * title : 1911广场旗下1911咖啡获香港文利国际餐饮300万天使轮投资
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526957017
             * introduce : 1911广场旗下1911咖啡获香港文利国际餐饮300万天使轮投资
             * create_time : 1522632996
             */

            private String id;
            private String title;
            private String picture;
            private String introduce;
            private String create_time;

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getCreate_time() {
                return create_time;
            }

            public void setCreate_time(String create_time) {
                this.create_time = create_time;
            }
        }

        public static class OtherNewsListBean {
            /**
             * id : 43
             * title : 1911未来教育上线了
             * picture : http://p8p47jzeo.bkt.clouddn.com/1526369010
             * introduce : 1911集团1911集团1911集团1911集团
             * create_time : 1526369019
             */

            private String id;
            private String title;
            private String picture;
            private String introduce;
            private String create_time;

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

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
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
