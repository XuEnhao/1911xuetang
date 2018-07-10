package com.yijiuyiyiedu.xuetang.module.entity;

import java.util.List;

/**
 * Created by xuenhao on 2018/6/15.
 * 合作伙伴
 */

public class FriendEntity {

    /**
     * status : 0
     * msg : 成功
     * data : {"collaborationEnterpriseList":[{"id":"1","enterprise_name":"合作企业一","logo":"http://p8p47jzeo.bkt.clouddn.com/1526956734"}]}
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
        private List<CollaborationEnterpriseListBean> collaborationEnterpriseList;

        public List<CollaborationEnterpriseListBean> getCollaborationEnterpriseList() {
            return collaborationEnterpriseList;
        }

        public void setCollaborationEnterpriseList(List<CollaborationEnterpriseListBean> collaborationEnterpriseList) {
            this.collaborationEnterpriseList = collaborationEnterpriseList;
        }

        public static class CollaborationEnterpriseListBean {
            /**
             * id : 1
             * enterprise_name : 合作企业一
             * logo : http://p8p47jzeo.bkt.clouddn.com/1526956734
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
    }
}
