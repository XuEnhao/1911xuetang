package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 */

public class NewCategoryEntity {


    /**
     * status : 0
     * msg : 成功
     * data : {"categoryList":[{"id":"1","category_name":"干部通用学院","picture":"","parent_id":"0","childList":[{"id":"2","category_name":"公共管理/履职能力","picture":"","parent_id":"1"},{"id":"3","category_name":"时政解读","picture":"","parent_id":"1"},{"id":"4","category_name":"法律法规","picture":"","parent_id":"1"},{"id":"5","category_name":"政府绩效管理","picture":"","parent_id":"1"},{"id":"6","category_name":"经济治理与城市规划","picture":"","parent_id":"1"},{"id":"7","category_name":"城市治理","picture":"","parent_id":"1"},{"id":"8","category_name":"应急管理","picture":"","parent_id":"1"},{"id":"9","category_name":"国际形势及安全治理","picture":"","parent_id":"1"},{"id":"10","category_name":"创新驱动发展","picture":"","parent_id":"1"},{"id":"11","category_name":"社会治理","picture":"","parent_id":"1"},{"id":"12","category_name":"一带一路与国际合作","picture":"","parent_id":"1"},{"id":"13","category_name":"乡村振兴","picture":"","parent_id":"1"},{"id":"14","category_name":"新闻宣传","picture":"","parent_id":"1"},{"id":"15","category_name":"人文素养","picture":"","parent_id":"1"}]},{"id":"16","category_name":"党政系统学院","picture":"","parent_id":"0","childList":[{"id":"21","category_name":"国企系统","picture":"","parent_id":"16"},{"id":"22","category_name":"体育系统","picture":"","parent_id":"16"},{"id":"24","category_name":"银行","picture":"","parent_id":"16"},{"id":"23","category_name":"金融系统","picture":"","parent_id":"16"},{"id":"25","category_name":"金融科技创新","picture":"","parent_id":"16"},{"id":"26","category_name":"互联网金融","picture":"","parent_id":"16"},{"id":"27","category_name":"金融服务实体经济","picture":"","parent_id":"16"},{"id":"28","category_name":"教育系统","picture":"","parent_id":"16"},{"id":"29","category_name":"团委系统","picture":"","parent_id":"16"},{"id":"30","category_name":"妇联系统","picture":"","parent_id":"16"},{"id":"31","category_name":"财政税务系统","picture":"","parent_id":"16"}]},{"id":"17","category_name":"在线商学院","picture":"","parent_id":"0","childList":[{"id":"32","category_name":"宏观经济","picture":"","parent_id":"17"},{"id":"33","category_name":"战略管理","picture":"","parent_id":"17"},{"id":"34","category_name":"领导力","picture":"","parent_id":"17"},{"id":"35","category_name":"人力资源管理","picture":"","parent_id":"17"},{"id":"36","category_name":"投融资与股权","picture":"","parent_id":"17"},{"id":"37","category_name":"财务税务","picture":"","parent_id":"17"},{"id":"38","category_name":"公司治理","picture":"","parent_id":"17"},{"id":"39","category_name":"法律实务与风险防范","picture":"","parent_id":"17"},{"id":"40","category_name":"市场营销","picture":"","parent_id":"17"},{"id":"41","category_name":"项目管理","picture":"","parent_id":"17"},{"id":"42","category_name":"国学经典","picture":"","parent_id":"17"},{"id":"43","category_name":"商业模式与创新","picture":"","parent_id":"17"}]},{"id":"18","category_name":"行业学院","picture":"","parent_id":"0","childList":[{"id":"44","category_name":"农业","picture":"","parent_id":"18"},{"id":"45","category_name":"能源业","picture":"","parent_id":"18"},{"id":"46","category_name":"零售业","picture":"","parent_id":"18"},{"id":"47","category_name":"房地产","picture":"","parent_id":"18"},{"id":"48","category_name":"医药行业","picture":"","parent_id":"18"},{"id":"49","category_name":"建筑施工","picture":"","parent_id":"18"},{"id":"50","category_name":"餐饮酒店","picture":"","parent_id":"18"},{"id":"51","category_name":"文化产业","picture":"","parent_id":"18"},{"id":"52","category_name":"汽车业","picture":"","parent_id":"18"},{"id":"53","category_name":"交通业","picture":"","parent_id":"18"},{"id":"54","category_name":"物联网","picture":"","parent_id":"18"}]},{"id":"19","category_name":"职场学院","picture":"","parent_id":"0","childList":[{"id":"55","category_name":"公务员考试","picture":"","parent_id":"19"},{"id":"56","category_name":"职业资格证书考试","picture":"","parent_id":"19"},{"id":"57","category_name":"职业发展能力","picture":"","parent_id":"19"},{"id":"58","category_name":"创业","picture":"","parent_id":"19"},{"id":"59","category_name":"生活","picture":"","parent_id":"19"}]},{"id":"20","category_name":"直播/热点课程","picture":"","parent_id":"0","childList":[{"id":"67","category_name":"特色小镇","picture":"","parent_id":"20"},{"id":"60","category_name":"区块链","picture":"","parent_id":"20"},{"id":"61","category_name":"互联网+","picture":"","parent_id":"20"},{"id":"62","category_name":"智能制造","picture":"","parent_id":"20"},{"id":"63","category_name":"PPP","picture":"","parent_id":"20"},{"id":"64","category_name":"大数据","picture":"","parent_id":"20"},{"id":"65","category_name":"财富传承","picture":"","parent_id":"20"},{"id":"66","category_name":"共享经济","picture":"","parent_id":"20"},{"id":"68","category_name":"机器人","picture":"","parent_id":"20"},{"id":"69","category_name":"3D打印","picture":"","parent_id":"20"}]}]}
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
        private List<CategoryListBean> categoryList;

        public List<CategoryListBean> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<CategoryListBean> categoryList) {
            this.categoryList = categoryList;
        }

        public static class CategoryListBean {
            /**
             * id : 1
             * category_name : 干部通用学院
             * picture :
             * parent_id : 0
             * childList : [{"id":"2","category_name":"公共管理/履职能力","picture":"","parent_id":"1"},{"id":"3","category_name":"时政解读","picture":"","parent_id":"1"},{"id":"4","category_name":"法律法规","picture":"","parent_id":"1"},{"id":"5","category_name":"政府绩效管理","picture":"","parent_id":"1"},{"id":"6","category_name":"经济治理与城市规划","picture":"","parent_id":"1"},{"id":"7","category_name":"城市治理","picture":"","parent_id":"1"},{"id":"8","category_name":"应急管理","picture":"","parent_id":"1"},{"id":"9","category_name":"国际形势及安全治理","picture":"","parent_id":"1"},{"id":"10","category_name":"创新驱动发展","picture":"","parent_id":"1"},{"id":"11","category_name":"社会治理","picture":"","parent_id":"1"},{"id":"12","category_name":"一带一路与国际合作","picture":"","parent_id":"1"},{"id":"13","category_name":"乡村振兴","picture":"","parent_id":"1"},{"id":"14","category_name":"新闻宣传","picture":"","parent_id":"1"},{"id":"15","category_name":"人文素养","picture":"","parent_id":"1"}]
             */

            private String id;
            private String category_name;
            private String picture;
            private String parent_id;
            private boolean check;
            private List<ChildListBean> childList;

            public boolean isCheck() {
                return check;
            }

            public void setCheck(boolean check) {
                this.check = check;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getCategory_name() {
                return category_name;
            }

            public void setCategory_name(String category_name) {
                this.category_name = category_name;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public String getParent_id() {
                return parent_id;
            }

            public void setParent_id(String parent_id) {
                this.parent_id = parent_id;
            }

            public List<ChildListBean> getChildList() {
                return childList;
            }

            public void setChildList(List<ChildListBean> childList) {
                this.childList = childList;
            }

            public static class ChildListBean {
                /**
                 * id : 2
                 * category_name : 公共管理/履职能力
                 * picture :
                 * parent_id : 1
                 */

                private String id;
                private String category_name;
                private String picture;
                private String parent_id;

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getCategory_name() {
                    return category_name;
                }

                public void setCategory_name(String category_name) {
                    this.category_name = category_name;
                }

                public String getPicture() {
                    return picture;
                }

                public void setPicture(String picture) {
                    this.picture = picture;
                }

                public String getParent_id() {
                    return parent_id;
                }

                public void setParent_id(String parent_id) {
                    this.parent_id = parent_id;
                }
            }
        }
    }
}
