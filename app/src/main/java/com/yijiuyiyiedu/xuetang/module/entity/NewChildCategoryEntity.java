package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/4/3.
 */

public class NewChildCategoryEntity {

    /**
     * status : 1
     * msg : 成功
     * data : {"categoryList":[{"id":"2","category_name":"近代史","picture":"/uploads/Category/2018/03/12/1520846218.jpg","parent_id":"1","sort_order":"6","status":"1","create_time":"1520846219"}]}
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
        private List<CategoryListBean> categoryList;

        public List<CategoryListBean> getCategoryList() {
            return categoryList;
        }

        public void setCategoryList(List<CategoryListBean> categoryList) {
            this.categoryList = categoryList;
        }

        public static class CategoryListBean {
            /**
             * id : 2
             * category_name : 近代史
             * picture : /uploads/Category/2018/03/12/1520846218.jpg
             * parent_id : 1
             * sort_order : 6
             * status : 1
             * create_time : 1520846219
             */

            private String id;
            private String category_name;
            private String picture;
            private String parent_id;
            private String sort_order;
            private String status;
            private String create_time;

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

            public String getSort_order() {
                return sort_order;
            }

            public void setSort_order(String sort_order) {
                this.sort_order = sort_order;
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
